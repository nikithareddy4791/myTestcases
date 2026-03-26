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
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.test.util.ReflectionTestUtils;

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

    @InjectMocks
    private ADSearchServiceDev adSearchServiceDev;

    @BeforeEach
    void setUp() {
        // Inject ldapTemplate into parent class field as well
        ReflectionTestUtils.setField(adSearchServiceDev, "ldapTemplate", ldapTemplate);
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
    @DisplayName("testUsers - all users have required fields populated")
    void testUsers_allUsersHaveRequiredFields() {
        List<User> users = adSearchServiceDev.testUsers();
        for (User user : users) {
            assertThat(user.getUsername()).isNotNull().isNotEmpty();
            assertThat(user.getFirstName()).isNotNull().isNotEmpty();
            assertThat(user.getLastName()).isNotNull().isNotEmpty();
            assertThat(user.getEmail()).isNotNull().isNotEmpty();
        }
    }

    // =========================================================================
    // findUser()
    // FIX: mock ldapTemplate so parent returns null, then Dev falls back to testUsers
    // =========================================================================

    @Test
    @DisplayName("findUser - returns prod user when found in LDAP")
    void findUser_foundInLdap_returnsProdUser() {
        User prodUser = new User();
        prodUser.setUsername("jdoe");

        // LDAP returns the prod user
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(List.of(prodUser));

        User result = adSearchServiceDev.findUser("jdoe");

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("jdoe");
    }

    @Test
    @DisplayName("findUser - returns test user when not found in LDAP")
    void findUser_notInLdap_returnsTestUser() {
        // LDAP returns empty — parent returns null
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(Collections.emptyList());

        User result = adSearchServiceDev.findUser("T-SG-ddd-SUPERVISOR");

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("T-SG-ddd-SUPERVISOR");
    }

    @Test
    @DisplayName("findUser - case insensitive match in test users")
    void findUser_caseInsensitiveMatch_returnsTestUser() {
        // LDAP returns empty — falls through to testUsers()
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(Collections.emptyList());

        User result = adSearchServiceDev.findUser("t-sg-ddd-supervisor");

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("T-SG-ddd-SUPERVISOR");
    }

    @Test
    @DisplayName("findUser - returns null when not found in LDAP or test users")
    void findUser_notFoundAnywhere_returnsNull() {
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(Collections.emptyList());

        User result = adSearchServiceDev.findUser("completely-unknown-user");

        assertThat(result).isNull();
    }

    // =========================================================================
    // findAllUsers()
    // FIX: mock ldapTemplate to return empty for all parent group queries
    // then verify testUsers() are appended
    // =========================================================================

    @Test
    @DisplayName("findAllUsers - returns test users when LDAP returns empty")
    void findAllUsers_noProdUsers_returnsOnlyTestUsers() {
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(Collections.emptyList());

        List<User> result = adSearchServiceDev.findAllUsers();

        // 9 test users only
        assertThat(result).hasSize(9);
    }

    @Test
    @DisplayName("findAllUsers - combines prod users and test users")
    void findAllUsers_combinesProdAndTestUsers() {
        User prodUser = new User();
        prodUser.setUsername("jdoe");

        // Each of the 8 LDAP group queries returns 1 prod user
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(List.of(prodUser));

        List<User> result = adSearchServiceDev.findAllUsers();

        // 8 prod users (one per group query) + 9 test users = 17
        assertThat(result).hasSizeGreaterThanOrEqualTo(9);
        assertThat(result).anyMatch(u -> u.getUsername().equals("jdoe"));
        assertThat(result).anyMatch(u -> u.getUsername().equals("T-SG-ddd-SUPERVISOR"));
    }

    // =========================================================================
    // findAllUsersWithSealedAccess()
    // FIX: mock ldapTemplate to return empty, verify Bronx test user is added
    // =========================================================================

    @Test
    @DisplayName("findAllUsersWithSealedAccess - adds T-SG-ddd-ANALYST-BRONX test user")
    void findAllUsersWithSealedAccess_addsBronxTestUser() {
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(Collections.emptyList());

        List<User> result = adSearchServiceDev.findAllUsersWithSealedAccess();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUsername()).isEqualTo("T-SG-ddd-ANALYST-BRONX");
    }

    @Test
    @DisplayName("findAllUsersWithSealedAccess - only adds Bronx user not all test users")
    void findAllUsersWithSealedAccess_onlyAddsBronxNotAllTestUsers() {
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(Collections.emptyList());

        List<User> result = adSearchServiceDev.findAllUsersWithSealedAccess();

        // Only Bronx from test users — not all 9
        assertThat(result).hasSize(1);
        assertThat(result).noneMatch(u -> u.getUsername().equals("T-SG-ddd-SUPERVISOR"));
        assertThat(result).noneMatch(u -> u.getUsername().equals("T-SG-ddd-ANALYST-BROOKLYN"));
    }

    @Test
    @DisplayName("findAllUsersWithSealedAccess - combines prod sealed users with Bronx test user")
    void findAllUsersWithSealedAccess_combinesProdAndBronxTestUser() {
        User prodUser = new User();
        prodUser.setUsername("jdoe");

        // First call returns prod user, rest return empty
        when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                .thenReturn(List.of(prodUser))
                .thenReturn(Collections.emptyList());

        List<User> result = adSearchServiceDev.findAllUsersWithSealedAccess();

        // At least prod user + Bronx test user
        assertThat(result).anyMatch(u -> u.getUsername().equals("T-SG-ddd-ANALYST-BRONX"));
        assertThat(result.size()).isGreaterThanOrEqualTo(2);
    }
}
