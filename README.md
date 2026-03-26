package org.nnnn.ddd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.nnnn.ddd.model.User;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("ADSearchServiceProd Tests")
class ADSearchServiceProdTest {

    @Mock
    private LdapTemplate ldapTemplate;

    @InjectMocks
    private ADSearchServiceProd adSearchServiceProd;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(adSearchServiceProd, "ldapTemplate", ldapTemplate);

        sampleUser = new User();
        sampleUser.setUsername("jdoe");
        sampleUser.setFirstName("John");
        sampleUser.setLastName("Doe");
        sampleUser.setEmail("jdoe@nnnn.org");
        sampleUser.setRank("Detective");

        // Use doAnswer to match ANY search() overload regardless of parameter types
        // This avoids ALL ambiguity issues with LdapTemplate.search() overloads
        doAnswer(invocation -> List.of(sampleUser))
                .when(ldapTemplate)
                .search(any(), any());
    }

    // Helper to make ldapTemplate return empty for all searches
    private void mockLdapReturnsEmpty() {
        doAnswer(invocation -> Collections.emptyList())
                .when(ldapTemplate)
                .search(any(), any());
    }

    // Helper to make ldapTemplate return one user
    private void mockLdapReturnsUser() {
        doAnswer(invocation -> List.of(sampleUser))
                .when(ldapTemplate)
                .search(any(), any());
    }

    // =========================================================================
    // findUser()
    // =========================================================================

    @Test
    @DisplayName("findUser - returns user when found in LDAP")
    void findUser_userFound_returnsUser() {
        mockLdapReturnsUser();

        User result = adSearchServiceProd.findUser("jdoe");

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("jdoe");
    }

    @Test
    @DisplayName("findUser - returns null when user not found in LDAP")
    void findUser_userNotFound_returnsNull() {
        mockLdapReturnsEmpty();

        User result = adSearchServiceProd.findUser("unknown");

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("findUser - returns first result when multiple users returned")
    void findUser_multipleResults_returnsFirst() {
        User secondUser = new User();
        secondUser.setUsername("jdoe2");

        doAnswer(invocation -> List.of(sampleUser, secondUser))
                .when(ldapTemplate)
                .search(any(), any());

        User result = adSearchServiceProd.findUser("jdoe");

        assertThat(result.getUsername()).isEqualTo("jdoe");
    }

    @Test
    @DisplayName("findUser - calls ldapTemplate.search exactly once")
    void findUser_callsLdapSearchOnce() {
        mockLdapReturnsEmpty();

        adSearchServiceProd.findUser("jdoe");

        verify(ldapTemplate, times(1)).search(any(), any());
    }

    // =========================================================================
    // findAllUsers()
    // =========================================================================

    @Test
    @DisplayName("findAllUsers - queries exactly 8 AD groups")
    void findAllUsers_queriesAll8Groups() {
        mockLdapReturnsEmpty();

        adSearchServiceProd.findAllUsers();

        verify(ldapTemplate, times(8)).search(any(), any());
    }

    @Test
    @DisplayName("findAllUsers - returns combined users from all groups")
    void findAllUsers_returnsUsersFromAllGroups() {
        mockLdapReturnsUser();

        List<User> result = adSearchServiceProd.findAllUsers();

        // 8 groups each returning 1 user = 8
        assertThat(result).hasSize(8);
    }

    @Test
    @DisplayName("findAllUsers - returns empty list when LDAP returns no users")
    void findAllUsers_noUsersInLdap_returnsEmptyList() {
        mockLdapReturnsEmpty();

        List<User> result = adSearchServiceProd.findAllUsers();

        assertThat(result).isEmpty();
    }

    // =========================================================================
    // findAllUsersWithSealedAccess()
    // =========================================================================

    @Test
    @DisplayName("findAllUsersWithSealedAccess - queries exactly 8 AD groups")
    void findAllUsersWithSealedAccess_queriesAll8Groups() {
        mockLdapReturnsEmpty();

        adSearchServiceProd.findAllUsersWithSealedAccess();

        verify(ldapTemplate, times(8)).search(any(), any());
    }

    @Test
    @DisplayName("findAllUsersWithSealedAccess - returns users from all groups")
    void findAllUsersWithSealedAccess_returnsUsersFromAllGroups() {
        mockLdapReturnsUser();

        List<User> result = adSearchServiceProd.findAllUsersWithSealedAccess();

        assertThat(result).hasSize(8);
    }

    @Test
    @DisplayName("findAllUsersWithSealedAccess - returns empty when no sealed users")
    void findAllUsersWithSealedAccess_noSealedUsers_returnsEmpty() {
        mockLdapReturnsEmpty();

        List<User> result = adSearchServiceProd.findAllUsersWithSealedAccess();

        assertThat(result).isEmpty();
    }

    // =========================================================================
    // findMembersOfGroup()
    // =========================================================================

    @Test
    @DisplayName("findMembersOfGroup - returns users for single group")
    void findMembersOfGroup_singleGroup_returnsUsers() {
        mockLdapReturnsUser();

        List<User> result = adSearchServiceProd.findMembersOfGroup("CN=SG-ddd-SUPERVISOR,OU=ddd");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUsername()).isEqualTo("jdoe");
    }

    @Test
    @DisplayName("findMembersOfGroup - returns users for multiple groups")
    void findMembersOfGroup_multipleGroups_returnsUsers() {
        mockLdapReturnsUser();

        List<User> result = adSearchServiceProd.findMembersOfGroup(
                "CN=SG-ddd-SUPERVISOR,OU=ddd",
                "CN=SG-ddd-SEALED,OU=ddd"
        );

        assertThat(result).hasSize(1);
        verify(ldapTemplate, times(1)).search(any(), any());
    }

    @Test
    @DisplayName("findMembersOfGroup - returns empty list when no group DNs provided")
    void findMembersOfGroup_noGroupDns_returnsEmptyList() {
        List<User> result = adSearchServiceProd.findMembersOfGroup();

        assertThat(result).isEmpty();
        verifyNoInteractions(ldapTemplate);
    }

    @Test
    @DisplayName("findMembersOfGroup - returns empty list when LDAP returns no results")
    void findMembersOfGroup_noResults_returnsEmptyList() {
        mockLdapReturnsEmpty();

        List<User> result = adSearchServiceProd.findMembersOfGroup("CN=SG-ddd-SUPERVISOR,OU=ddd");

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("findMembersOfGroup - calls ldapTemplate.search exactly once")
    void findMembersOfGroup_callsLdapSearchOnce() {
        mockLdapReturnsEmpty();

        adSearchServiceProd.findMembersOfGroup("CN=SG-ddd-SUPERVISOR,OU=ddd");

        verify(ldapTemplate, times(1)).search(any(), any());
    }
}
