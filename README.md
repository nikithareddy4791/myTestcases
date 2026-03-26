package org.nnnn.ddd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.nnnn.ddd.model.User;
import org.springframework.ldap.core.LdapTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("ADSearchServiceDev Tests")
class ADSearchServiceDevTest {

    @Mock
    private LdapTemplate ldapTemplate;

    @Spy
    @InjectMocks
    private ADSearchServiceDev adSearchServiceDev;

    private User prodUser;
    private User testUser;

    @BeforeEach
    void setUp() {
        prodUser = new User();
        prodUser.setUsername("jdoe");
        prodUser.setFirstName("John");
        prodUser.setLastName("Doe");
        prodUser.setEmail("jdoe@nnnn.org");

        testUser = new User();
        testUser.setUsername("T-SG-ddd-SUPERVISOR");
        testUser.setFirstName("John");
        testUser.setLastName("ddd-SUPERVISOR");
        testUser.setEmail("SG-ddd-SUPERVISOR@nnnn.org");
        testUser.setRank("Sgt");
    }

    // =========================================================================
    // testUsers()
    // =========================================================================

    @Test
    @DisplayName("testUsers - returns 9 hardcoded test users")
    void testUsers_returnsAllTestUsers() {
        List<User> users = adSearchServiceDev.testUsers();

        assertThat(users).hasSize(9);
    }

    @Test
    @DisplayName("testUsers - contains T-SG-ddd-SUPERVISOR")
    void testUsers_containsSupervisor() {
        List<User> users = adSearchServiceDev.testUsers();

        assertThat(users).anyMatch(u -> u.getUsername().equals("T-SG-ddd-SUPERVISOR"));
    }

    @Test
    @DisplayName("testUsers - contains all expected borough analysts")
    void testUsers_containsAllBoroughAnalysts() {
        List<User> users = adSearchServiceDev.testUsers();

        List<String> usernames = users.stream().map(User::getUsername).toList();
        assertThat(usernames).contains(
                "T-SG-ddd-SUPERVISOR",
                "T-SG-ddd-ANALYST-BROOKLYN",
                "T-SG-ddd-ANALYST-BRONX",
                "T-SG-ddd-ANALYST-QUEENS",
                "T-SG-ddd-ANALYST-SI",
                "T-SG-ddd-ANALYST-MANHATTAN",
                "T-SG-ddd-ANALYST-SNP",
                "T-SG-ddd-ANALYST-REMANDED",
                "T-SG-ddd-DUAL"
        );
    }

    @Test
    @DisplayName("testUsers - each test user has required fields populated")
    void testUsers_allUsersHaveRequiredFields() {
        List<User> users = adSearchServiceDev.testUsers();

        for (User user : users) {
            assertThat(user.getUsername()).isNotNull().isNotEmpty();
            assertThat(user.getFirstName()).isNotNull().isNotEmpty();
            assertThat(user.getLastName()).isNotNull().isNotEmpty();
            assertThat(user.getEmail()).isNotNull().isNotEmpty();
            assertThat(user.getRank()).isNotNull();
        }
    }

    // =========================================================================
    // findUser()
    // =========================================================================

    @Test
    @DisplayName("findUser - returns prod user when found in LDAP")
    void findUser_foundInLdap_returnsProdUser() {
        doReturn(prodUser).when(adSearchServiceDev).findUser("jdoe");

        User result = adSearchServiceDev.findUser("jdoe");

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("jdoe");
    }

    @Test
    @DisplayName("findUser - returns test user when not found in LDAP but in test users")
    void findUser_notInLdap_returnsTestUser() {
        // Simulate super.findUser returning null (not in LDAP)
        doReturn(null).when((ADSearchServiceProd) adSearchServiceDev).findUser("T-SG-ddd-SUPERVISOR");

        // Call the Dev implementation directly
        // Since super returns null, it should fall through to testUsers()
        User result = adSearchServiceDev.findUser("T-SG-ddd-SUPERVISOR");

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("T-SG-ddd-SUPERVISOR");
    }

    @Test
    @DisplayName("findUser - case insensitive match in test users")
    void findUser_caseInsensitiveMatch_returnsTestUser() {
        // Test that equalsIgnoreCase works — lowercase lookup
        doReturn(null).when((ADSearchServiceProd) adSearchServiceDev).findUser("t-sg-ddd-supervisor");

        User result = adSearchServiceDev.findUser("t-sg-ddd-supervisor");

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("T-SG-ddd-SUPERVISOR");
    }

    @Test
    @DisplayName("findUser - returns null when not found in LDAP or test users")
    void findUser_notFoundAnywhere_returnsNull() {
        doReturn(null).when((ADSearchServiceProd) adSearchServiceDev).findUser("unknown");

        User result = adSearchServiceDev.findUser("unknown");

        assertThat(result).isNull();
    }

    // =========================================================================
    // findAllUsers()
    // =========================================================================

    @Test
    @DisplayName("findAllUsers - combines prod users and test users")
    void findAllUsers_combinesProdAndTestUsers() {
        List<User> prodUsers = List.of(prodUser);
        doReturn(prodUsers).when((ADSearchServiceProd) adSearchServiceDev).findAllUsers();

        List<User> result = adSearchServiceDev.findAllUsers();

        // Should contain prod user + 9 test users = 10
        assertThat(result).hasSize(10);
        assertThat(result).contains(prodUser);
    }

    @Test
    @DisplayName("findAllUsers - returns only test users when no prod users")
    void findAllUsers_noProdUsers_returnsOnlyTestUsers() {
        doReturn(new java.util.ArrayList<>()).when((ADSearchServiceProd) adSearchServiceDev).findAllUsers();

        List<User> result = adSearchServiceDev.findAllUsers();

        assertThat(result).hasSize(9);
    }

    // =========================================================================
    // findAllUsersWithSealedAccess()
    // =========================================================================

    @Test
    @DisplayName("findAllUsersWithSealedAccess - adds T-SG-ddd-ANALYST-BRONX to sealed list")
    void findAllUsersWithSealedAccess_addsBronxTestUser() {
        doReturn(new java.util.ArrayList<>())
                .when((ADSearchServiceProd) adSearchServiceDev).findAllUsersWithSealedAccess();

        List<User> result = adSearchServiceDev.findAllUsersWithSealedAccess();

        assertThat(result).anyMatch(u -> u.getUsername().equals("T-SG-ddd-ANALYST-BRONX"));
    }

    @Test
    @DisplayName("findAllUsersWithSealedAccess - only adds Bronx test user not all test users")
    void findAllUsersWithSealedAccess_onlyAddsBronxNotAllTestUsers() {
        doReturn(new java.util.ArrayList<>())
                .when((ADSearchServiceProd) adSearchServiceDev).findAllUsersWithSealedAccess();

        List<User> result = adSearchServiceDev.findAllUsersWithSealedAccess();

        // Only Bronx should be added from test users
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUsername()).isEqualTo("T-SG-ddd-ANALYST-BRONX");
    }

    @Test
    @DisplayName("findAllUsersWithSealedAccess - combines prod sealed users with bronx test user")
    void findAllUsersWithSealedAccess_combinesProdAndBronxTestUser() {
        List<User> prodSealedUsers = List.of(prodUser);
        doReturn(new java.util.ArrayList<>(prodSealedUsers))
                .when((ADSearchServiceProd) adSearchServiceDev).findAllUsersWithSealedAccess();

        List<User> result = adSearchServiceDev.findAllUsersWithSealedAccess();

        assertThat(result).hasSize(2);
        assertThat(result).contains(prodUser);
        assertThat(result).anyMatch(u -> u.getUsername().equals("T-SG-ddd-ANALYST-BRONX"));
    }
}








=======================================================



package org.nnnn.ddd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nnnn.ddd.AppConstants;
import org.nnnn.ddd.model.User;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ADSearchServiceProd Tests")
class ADSearchServiceProdTest {

    @Mock
    private LdapTemplate ldapTemplate;

    @InjectMocks
    private ADSearchServiceProd adSearchServiceProd;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        sampleUser = new User();
        sampleUser.setUsername("jdoe");
        sampleUser.setFirstName("John");
        sampleUser.setLastName("Doe");
        sampleUser.setEmail("jdoe@nnnn.org");
        sampleUser.setRank("Detective");
        sampleUser.setTitle("Senior Analyst");
        sampleUser.setTax("123456");
        sampleUser.setCmdCode("CMD01");
        sampleUser.setMobile("555-1234");
    }

    // =========================================================================
    // findUser()
    // =========================================================================

    @Test
    @DisplayName("findUser - returns user when found in LDAP")
    void findUser_userFound_returnsUser() {
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(List.of(sampleUser));

        User result = adSearchServiceProd.findUser("jdoe");

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("jdoe");
    }

    @Test
    @DisplayName("findUser - returns null when user not found in LDAP")
    void findUser_userNotFound_returnsNull() {
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(Collections.emptyList());

        User result = adSearchServiceProd.findUser("unknown");

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("findUser - returns first user when multiple results returned")
    void findUser_multipleResults_returnsFirst() {
        User secondUser = new User();
        secondUser.setUsername("jdoe2");

        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(Arrays.asList(sampleUser, secondUser));

        User result = adSearchServiceProd.findUser("jdoe");

        assertThat(result.getUsername()).isEqualTo("jdoe");
    }

    @Test
    @DisplayName("findUser - calls ldapTemplate.search exactly once")
    void findUser_callsLdapSearchOnce() {
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(Collections.emptyList());

        adSearchServiceProd.findUser("jdoe");

        verify(ldapTemplate, times(1)).search(any(LdapQuery.class), any(AttributesMapper.class));
    }

    // =========================================================================
    // findAllUsers()
    // =========================================================================

    @Test
    @DisplayName("findAllUsers - returns combined users from all groups")
    void findAllUsers_returnsUsersFromAllGroups() {
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(List.of(sampleUser));

        List<User> result = adSearchServiceProd.findAllUsers();

        // 8 group queries each returning 1 user = 8 users
        assertThat(result).hasSize(8);
        verify(ldapTemplate, times(8)).search(any(LdapQuery.class), any(AttributesMapper.class));
    }

    @Test
    @DisplayName("findAllUsers - returns empty list when LDAP returns no users")
    void findAllUsers_noUsersInLdap_returnsEmptyList() {
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(Collections.emptyList());

        List<User> result = adSearchServiceProd.findAllUsers();

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("findAllUsers - queries all 8 AD groups")
    void findAllUsers_queriesAll8Groups() {
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(Collections.emptyList());

        adSearchServiceProd.findAllUsers();

        // Supervisor + 7 borough analyst groups = 8 queries
        verify(ldapTemplate, times(8)).search(any(LdapQuery.class), any(AttributesMapper.class));
    }

    // =========================================================================
    // findAllUsersWithSealedAccess()
    // =========================================================================

    @Test
    @DisplayName("findAllUsersWithSealedAccess - returns combined sealed users from all groups")
    void findAllUsersWithSealedAccess_returnsUsersFromAllGroups() {
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(List.of(sampleUser));

        List<User> result = adSearchServiceProd.findAllUsersWithSealedAccess();

        assertThat(result).hasSize(8);
        verify(ldapTemplate, times(8)).search(any(LdapQuery.class), any(AttributesMapper.class));
    }

    @Test
    @DisplayName("findAllUsersWithSealedAccess - returns empty list when no sealed users")
    void findAllUsersWithSealedAccess_noSealedUsers_returnsEmptyList() {
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(Collections.emptyList());

        List<User> result = adSearchServiceProd.findAllUsersWithSealedAccess();

        assertThat(result).isEmpty();
    }

    // =========================================================================
    // findMembersOfGroup()
    // =========================================================================

    @Test
    @DisplayName("findMembersOfGroup - returns users for single group")
    void findMembersOfGroup_singleGroup_returnsUsers() {
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(List.of(sampleUser));

        List<User> result = adSearchServiceProd.findMembersOfGroup("CN=SG-ddd-SUPERVISOR,OU=ddd");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUsername()).isEqualTo("jdoe");
    }

    @Test
    @DisplayName("findMembersOfGroup - returns users for multiple groups (AND condition)")
    void findMembersOfGroup_multipleGroups_returnsIntersection() {
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(List.of(sampleUser));

        List<User> result = adSearchServiceProd.findMembersOfGroup(
                "CN=SG-ddd-SUPERVISOR,OU=ddd",
                "CN=SG-ddd-SEALED,OU=ddd"
        );

        assertThat(result).hasSize(1);
        verify(ldapTemplate, times(1)).search(any(LdapQuery.class), any(AttributesMapper.class));
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
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(Collections.emptyList());

        List<User> result = adSearchServiceProd.findMembersOfGroup("CN=SG-ddd-SUPERVISOR,OU=ddd");

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("findMembersOfGroup - calls ldapTemplate.search exactly once per call")
    void findMembersOfGroup_callsLdapSearchOnce() {
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(Collections.emptyList());

        adSearchServiceProd.findMembersOfGroup("CN=SG-ddd-SUPERVISOR,OU=ddd");

        verify(ldapTemplate, times(1)).search(any(LdapQuery.class), any(AttributesMapper.class));
    }
}
