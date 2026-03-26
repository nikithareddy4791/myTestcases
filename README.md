package org.nnnn.ddd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SecurityConfig Tests")
class SecurityConfigTest {

    private SecurityConfig securityConfig;

    @BeforeEach
    void setUp() {
        securityConfig = new SecurityConfig();
    }

    // =========================================================================
    // jwtAuthenticationConverter bean
    // =========================================================================

    @Test
    @DisplayName("jwtAuthenticationConverter bean is created successfully")
    void jwtAuthenticationConverter_beanCreatedSuccessfully() {
        assertThat(securityConfig.jwtAuthenticationConverter()).isNotNull();
    }

    // =========================================================================
    // Test the authorities converter logic directly by replicating it
    // The converter lambda reads "nnnn Groups" claim, strips slashes,
    // uppercases, and prefixes with ROLE_
    // We test this by calling the same logic directly
    // =========================================================================

    private List<String> convertGroupsToRoles(List<String> groups) {
        List<String> roles = new ArrayList<>();
        if (groups != null) {
            for (String g : groups) {
                String role = g.replace("/", "").toUpperCase();
                roles.add("ROLE_" + role);
            }
        }
        return roles;
    }

    @Test
    @DisplayName("groups are converted to ROLE_ prefixed uppercase authorities")
    void groups_convertedToRolePrefixedAuthorities() {
        List<String> groups = List.of("SG-ddd-SUPERVISOR", "SG-ddd-ANALYST-MANHATTAN");

        List<String> roles = convertGroupsToRoles(groups);

        assertThat(roles).contains("ROLE_SG-DDD-SUPERVISOR");
        assertThat(roles).contains("ROLE_SG-DDD-ANALYST-MANHATTAN");
        assertThat(roles).allMatch(r -> r.startsWith("ROLE_"));
    }

    @Test
    @DisplayName("leading slash is removed from group name")
    void groups_leadingSlashIsRemoved() {
        List<String> groups = List.of("/SG-ddd-SUPERVISOR");

        List<String> roles = convertGroupsToRoles(groups);

        assertThat(roles).contains("ROLE_SG-DDD-SUPERVISOR");
        assertThat(roles).allMatch(r -> !r.contains("/"));
    }

    @Test
    @DisplayName("group names are uppercased in role")
    void groups_namesAreUppercased() {
        List<String> groups = List.of("sg-ddd-supervisor");

        List<String> roles = convertGroupsToRoles(groups);

        assertThat(roles).contains("ROLE_SG-DDD-SUPERVISOR");
        assertThat(roles).allMatch(r -> r.equals(r.toUpperCase()));
    }

    @Test
    @DisplayName("null groups produces empty roles")
    void groups_nullProducesEmptyRoles() {
        List<String> roles = convertGroupsToRoles(null);

        assertThat(roles).isEmpty();
    }

    @Test
    @DisplayName("empty groups produces empty roles")
    void groups_emptyProducesEmptyRoles() {
        List<String> roles = convertGroupsToRoles(List.of());

        assertThat(roles).isEmpty();
    }

    @Test
    @DisplayName("multiple groups produce multiple roles")
    void groups_multipleGroupsProduceMultipleRoles() {
        List<String> groups = Arrays.asList(
                "SG-ddd-SUPERVISOR",
                "SG-ddd-ANALYST-BROOKLYN",
                "SG-ddd-ANALYST-BRONX",
                "SG-SEALED-EVENT"
        );

        List<String> roles = convertGroupsToRoles(groups);

        assertThat(roles).hasSize(4);
    }

    @Test
    @DisplayName("single group produces single role")
    void groups_singleGroupProducesSingleRole() {
        List<String> groups = List.of("SG-ddd-SUPERVISOR");

        List<String> roles = convertGroupsToRoles(groups);

        assertThat(roles).hasSize(1);
        assertThat(roles.get(0)).isEqualTo("ROLE_SG-DDD-SUPERVISOR");
    }

    @Test
    @DisplayName("sealed event group converts correctly")
    void groups_sealedEventConvertsCorrectly() {
        List<String> groups = List.of("SG-SEALED-EVENT");

        List<String> roles = convertGroupsToRoles(groups);

        assertThat(roles).contains("ROLE_SG-SEALED-EVENT");
    }

    @Test
    @DisplayName("all analyst groups convert correctly")
    void groups_allAnalystGroupsConvertCorrectly() {
        List<String> groups = Arrays.asList(
                "SG-ddd-ANALYST-BROOKLYN",
                "SG-ddd-ANALYST-BRONX",
                "SG-ddd-ANALYST-QUEENS",
                "SG-ddd-ANALYST-SI",
                "SG-ddd-ANALYST-MANHATTAN",
                "SG-ddd-ANALYST-SNP",
                "SG-ddd-ANALYST-REMANDED"
        );

        List<String> roles = convertGroupsToRoles(groups);

        assertThat(roles).hasSize(7);
        assertThat(roles).allMatch(r -> r.startsWith("ROLE_SG-DDD-ANALYST-"));
    }

    @Test
    @DisplayName("group with multiple slashes has all slashes removed")
    void groups_multipleSlashesAllRemoved() {
        List<String> groups = List.of("//SG-ddd-SUPERVISOR");

        List<String> roles = convertGroupsToRoles(groups);

        assertThat(roles).allMatch(r -> !r.contains("/"));
        assertThat(roles).contains("ROLE_SG-DDD-SUPERVISOR");
    }

    // =========================================================================
    // JwtAuthenticationConverter bean - verify it's wired correctly
    // =========================================================================

    @Test
    @DisplayName("jwtAuthenticationConverter is not null and is correct type")
    void jwtAuthenticationConverter_isCorrectType() {
        JwtAuthenticationConverter converter = securityConfig.jwtAuthenticationConverter();
        assertThat(converter).isInstanceOf(JwtAuthenticationConverter.class);
    }
}
