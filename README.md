package org.nnnn.ddd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
    private ADSearchServiceProd service;

    // =========================================================================
    // Helpers
    // =========================================================================

    private User buildUser(String username, String first, String last) {
        User u = new User();
        u.setUsername(username);
        u.setFirstName(first);
        u.setLastName(last);
        u.setEmail(username + "@nnnn.finest");
        u.setMobile("555-1234");
        u.setRank("Detective");
        u.setTitle("Analyst");
        u.setTax("TAX001");
        u.setCmdCode("CMD01");
        return u;
    }

    // =========================================================================
    // findUser()
    // =========================================================================

    @Nested
    @DisplayName("findUser()")
    class FindUserTests {

        @Test
        @DisplayName("returns user when LDAP finds a match")
        void findUser_matchFound_returnsUser() {
            User expected = buildUser("jdoe", "Jane", "Doe");
            when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                    .thenReturn(List.of(expected));

            User result = service.findUser("jdoe");

            assertThat(result).isNotNull();
            assertThat(result.getUsername()).isEqualTo("jdoe");
            assertThat(result.getFirstName()).isEqualTo("Jane");
            assertThat(result.getLastName()).isEqualTo("Doe");
        }

        @Test
        @DisplayName("returns null when LDAP finds no match")
        void findUser_noMatch_returnsNull() {
            when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                    .thenReturn(List.of());

            User result = service.findUser("unknownUser");

            assertThat(result).isNull();
        }

        @Test
        @DisplayName("returns first user when LDAP finds multiple matches")
        void findUser_multipleMatches_returnsFirst() {
            User first  = buildUser("jdoe",  "Jane",  "Doe");
            User second = buildUser("jdoe2", "Janet", "Doe");
            when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                    .thenReturn(List.of(first, second));

            User result = service.findUser("jdoe");

            assertThat(result.getUsername()).isEqualTo("jdoe");
        }

        @Test
        @DisplayName("calls LdapTemplate exactly once")
        void findUser_callsLdapTemplateOnce() {
            when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                    .thenReturn(List.of());

            service.findUser("anyUser");

            verify(ldapTemplate, times(1)).search(any(LdapQuery.class), any(AttributesMapper.class));
        }
    }

    // =========================================================================
    // findAllUsers()
    // =========================================================================

    @Nested
    @DisplayName("findAllUsers()")
    class FindAllUsersTests {

        @Test
        @DisplayName("aggregates users from all 8 security groups")
        void findAllUsers_aggregatesAllGroups() {
            // Return 1 distinct user per group call to verify all 8 are queried
            when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                    .thenReturn(List.of(buildUser("user1", "A", "B")))
                    .thenReturn(List.of(buildUser("user2", "C", "D")))
                    .thenReturn(List.of(buildUser("user3", "E", "F")))
                    .thenReturn(List.of(buildUser("user4", "G", "H")))
                    .thenReturn(List.of(buildUser("user5", "I", "J")))
                    .thenReturn(List.of(buildUser("user6", "K", "L")))
                    .thenReturn(List.of(buildUser("user7", "M", "N")))
                    .thenReturn(List.of(buildUser("user8", "O", "P")));

            List<User> result = service.findAllUsers();

            assertThat(result).hasSize(8);
            verify(ldapTemplate, times(8)).search(any(LdapQuery.class), any(AttributesMapper.class));
        }

        @Test
        @DisplayName("returns empty list when all groups are empty")
        void findAllUsers_allGroupsEmpty_returnsEmptyList() {
            when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                    .thenReturn(List.of());

            List<User> result = service.findAllUsers();

            assertThat(result).isEmpty();
        }

        @Test
        @DisplayName("includes users from groups that partially return results")
        void findAllUsers_someGroupsEmpty_includesNonEmptyGroups() {
            User supervisor = buildUser("sup1", "Super", "Visor");
            // Only the first group (supervisor) has members; all others are empty
            when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                    .thenReturn(List.of(supervisor))
                    .thenReturn(List.of())
                    .thenReturn(List.of())
                    .thenReturn(List.of())
                    .thenReturn(List.of())
                    .thenReturn(List.of())
                    .thenReturn(List.of())
                    .thenReturn(List.of());

            List<User> result = service.findAllUsers();

            assertThat(result).hasSize(1);
            assertThat(result.get(0).getUsername()).isEqualTo("sup1");
        }

        @Test
        @DisplayName("returns combined total from multiple groups")
        void findAllUsers_multipleGroupsWithUsers_returnsCombinedTotal() {
            List<User> group1 = List.of(
                    buildUser("bk1", "Brooklyn", "One"),
                    buildUser("bk2", "Brooklyn", "Two")
            );
            List<User> group2 = List.of(
                    buildUser("bx1", "Bronx", "One")
            );
            when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                    .thenReturn(List.of())   // supervisor
                    .thenReturn(group1)      // BK
                    .thenReturn(group2)      // BX
                    .thenReturn(List.of())   // MN
                    .thenReturn(List.of())   // QN
                    .thenReturn(List.of())   // SI
                    .thenReturn(List.of())   // SNP
                    .thenReturn(List.of());  // RMD

            List<User> result = service.findAllUsers();

            assertThat(result).hasSize(3);
        }
    }

    // =========================================================================
    // findAllUsersWithSealedAccess()
    // =========================================================================

    @Nested
    @DisplayName("findAllUsersWithSealedAccess()")
    class FindAllUsersWithSealedAccessTests {

        @Test
        @DisplayName("queries all 8 groups for sealed access")
        void findAllUsersWithSealedAccess_queriesAllGroups() {
            when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                    .thenReturn(List.of());

            service.findAllUsersWithSealedAccess();

            verify(ldapTemplate, times(8)).search(any(LdapQuery.class), any(AttributesMapper.class));
        }

        @Test
        @DisplayName("returns only users with sealed event access")
        void findAllUsersWithSealedAccess_returnsMatchingUsers() {
            User sealedUser = buildUser("sealed1", "Sealed", "User");
            when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                    .thenReturn(List.of(sealedUser))
                    .thenReturn(List.of())
                    .thenReturn(List.of())
                    .thenReturn(List.of())
                    .thenReturn(List.of())
                    .thenReturn(List.of())
                    .thenReturn(List.of())
                    .thenReturn(List.of());

            List<User> result = service.findAllUsersWithSealedAccess();

            assertThat(result).hasSize(1);
            assertThat(result.get(0).getUsername()).isEqualTo("sealed1");
        }

        @Test
        @DisplayName("returns empty list when no users have sealed access")
        void findAllUsersWithSealedAccess_noSealedUsers_returnsEmpty() {
            when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                    .thenReturn(List.of());

            List<User> result = service.findAllUsersWithSealedAccess();

            assertThat(result).isEmpty();
        }
    }

    // =========================================================================
    // findMembersOfGroup()
    // =========================================================================

    @Nested
    @DisplayName("findMembersOfGroup()")
    class FindMembersOfGroupTests {

        @Test
        @DisplayName("returns empty list when called with no group DNs")
        void findMembersOfGroup_noGroupDns_returnsEmptyWithoutCallingLdap() {
            List<User> result = service.findMembersOfGroup();

            assertThat(result).isEmpty();
            verifyNoInteractions(ldapTemplate);
        }

        @Test
        @DisplayName("queries LDAP when a single group DN is provided")
        void findMembersOfGroup_singleDn_queriesLdap() {
            User member = buildUser("mbr1", "Member", "One");
            when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                    .thenReturn(List.of(member));

            List<User> result = service.findMembersOfGroup(
                    "CN=SG-DDD-SUPERVISOR,OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest"
            );

            assertThat(result).hasSize(1);
            assertThat(result.get(0).getUsername()).isEqualTo("mbr1");
        }

        @Test
        @DisplayName("queries LDAP with intersection (AND) when multiple group DNs are provided")
        void findMembersOfGroup_multipleDns_queriesLdapOnce() {
            // Multiple DNs = a single AND query, not one query per DN
            User member = buildUser("dual1", "Dual", "Member");
            when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                    .thenReturn(List.of(member));

            List<User> result = service.findMembersOfGroup(
                    "CN=SG-DDD-SUPERVISOR,OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest",
                    "CN=SG-SEALED-EVENT,OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest"
            );

            assertThat(result).hasSize(1);
            // One LDAP call — the multiple DNs are combined into a single AND-query
            verify(ldapTemplate, times(1)).search(any(LdapQuery.class), any(AttributesMapper.class));
        }

        @Test
        @DisplayName("returns empty list when LDAP finds no members")
        void findMembersOfGroup_noMembers_returnsEmpty() {
            when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                    .thenReturn(List.of());

            List<User> result = service.findMembersOfGroup(
                    "CN=SG-DDD-ANALYST-BK,OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest"
            );

            assertThat(result).isEmpty();
        }

        @Test
        @DisplayName("returns all members when LDAP returns multiple users")
        void findMembersOfGroup_multipleMembers_returnsAll() {
            List<User> members = List.of(
                    buildUser("a1", "Alice", "Smith"),
                    buildUser("b2", "Bob",   "Jones"),
                    buildUser("c3", "Carol", "Lee")
            );
            when(ldapTemplate.search(any(LdapQuery.class), any(AttributesMapper.class)))
                    .thenReturn(members);

            List<User> result = service.findMembersOfGroup(
                    "CN=SG-DDD-ANALYST-BK,OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest"
            );

            assertThat(result).hasSize(3);
            assertThat(result).extracting(User::getUsername).containsExactly("a1", "b2", "c3");
        }
    }
}
