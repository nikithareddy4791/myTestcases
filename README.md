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
    }

    private void mockLdapReturnsUser() {
        doAnswer(invocation -> List.of(sampleUser))
                .when(ldapTemplate).search(any(), any());
    }

    private void mockLdapReturnsEmpty() {
        doAnswer(invocation -> Collections.emptyList())
                .when(ldapTemplate).search(any(), any());
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

        assertThat(adSearchServiceProd.findUser("unknown")).isNull();
    }

    @Test
    @DisplayName("findUser - returns first result when multiple users returned")
    void findUser_multipleResults_returnsFirst() {
        User secondUser = new User();
        secondUser.setUsername("jdoe2");
        doAnswer(invocation -> List.of(sampleUser, secondUser))
                .when(ldapTemplate).search(any(), any());

        assertThat(adSearchServiceProd.findUser("jdoe").getUsername()).isEqualTo("jdoe");
    }

    // =========================================================================
    // findAllUsers()
    // =========================================================================

    @Test
    @DisplayName("findAllUsers - returns combined users from all 8 groups")
    void findAllUsers_returnsUsersFromAllGroups() {
        mockLdapReturnsUser();

        List<User> result = adSearchServiceProd.findAllUsers();

        // 8 group queries each returning 1 user
        assertThat(result).hasSize(8);
    }

    @Test
    @DisplayName("findAllUsers - returns empty list when LDAP returns no users")
    void findAllUsers_noUsersInLdap_returnsEmptyList() {
        mockLdapReturnsEmpty();

        assertThat(adSearchServiceProd.findAllUsers()).isEmpty();
    }

    @Test
    @DisplayName("findAllUsers - result contains users from LDAP")
    void findAllUsers_containsLdapUsers() {
        mockLdapReturnsUser();

        List<User> result = adSearchServiceProd.findAllUsers();

        assertThat(result).allMatch(u -> u.getUsername().equals("jdoe"));
    }

    // =========================================================================
    // findAllUsersWithSealedAccess()
    // =========================================================================

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

        assertThat(adSearchServiceProd.findAllUsersWithSealedAccess()).isEmpty();
    }

    // =========================================================================
    // findMembersOfGroup()
    // =========================================================================

    @Test
    @DisplayName("findMembersOfGroup - returns empty list when no group DNs provided")
    void findMembersOfGroup_noGroupDns_returnsEmptyList() {
        List<User> result = adSearchServiceProd.findMembersOfGroup();

        assertThat(result).isEmpty();
        verifyNoInteractions(ldapTemplate);
    }

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

        assertThat(result).isNotEmpty();
    }

    @Test
    @DisplayName("findMembersOfGroup - returns empty list when LDAP returns no results")
    void findMembersOfGroup_noResults_returnsEmptyList() {
        mockLdapReturnsEmpty();

        assertThat(adSearchServiceProd
                .findMembersOfGroup("CN=SG-ddd-SUPERVISOR,OU=ddd"))
                .isEmpty();
    }
}
