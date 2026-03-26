package org.nnnn.ddd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SecurityConfig Tests")
class SecurityConfigTest {

    private SecurityConfig securityConfig;
    private JwtAuthenticationConverter jwtAuthenticationConverter;

    @BeforeEach
    void setUp() {
        securityConfig = new SecurityConfig();
        jwtAuthenticationConverter = securityConfig.jwtAuthenticationConverter();
    }

    // =========================================================================
    // Bean creation
    // =========================================================================

    @Test
    @DisplayName("jwtAuthenticationConverter bean is created successfully")
    void jwtAuthenticationConverter_beanCreatedSuccessfully() {
        assertThat(jwtAuthenticationConverter).isNotNull();
        assertThat(jwtAuthenticationConverter).isInstanceOf(JwtAuthenticationConverter.class);
    }

    // =========================================================================
    // Use Jwt.withTokenValue().claim() one at a time to ensure
    // the "nnnn Groups" key with space is stored correctly
    // =========================================================================

    @Test
    @DisplayName("convert - maps nnnn Groups to ROLE_ prefixed authorities")
    void convert_mapsGroupsToRoleAuthorities() {
        Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR", "SG-ddd-ANALYST-MANHATTAN"));

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        assertThat(token).isNotNull();
        List<String> roles = token.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        assertThat(roles).anyMatch(r -> r.contains("SUPERVISOR"));
        assertThat(roles).anyMatch(r -> r.contains("MANHATTAN"));
        assertThat(roles).allMatch(r -> r.startsWith("ROLE_"));
    }

    @Test
    @DisplayName("convert - removes slash from group name")
    void convert_removesSlashFromGroupName() {
        Jwt jwt = buildJwt(List.of("/SG-ddd-SUPERVISOR"));

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        assertThat(token.getAuthorities())
                .allMatch(a -> !a.getAuthority().contains("/"));
    }

    @Test
    @DisplayName("convert - uppercases group names")
    void convert_uppercasesGroupNames() {
        Jwt jwt = buildJwt(List.of("sg-ddd-supervisor"));

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        assertThat(token.getAuthorities())
                .allMatch(a -> a.getAuthority().equals(a.getAuthority().toUpperCase()));
    }

    @Test
    @DisplayName("convert - returns empty authorities when groups claim absent")
    void convert_returnsEmptyWhenGroupsAbsent() {
        Jwt jwt = Jwt.withTokenValue("mock-token")
                .header("alg", "RS256")
                .claim("sub", "jdoe")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        assertThat(token.getAuthorities()).isEmpty();
    }

    @Test
    @DisplayName("convert - maps multiple groups to multiple roles")
    void convert_mapsMultipleGroupsToMultipleRoles() {
        Jwt jwt = buildJwt(Arrays.asList(
                "SG-ddd-SUPERVISOR",
                "SG-ddd-ANALYST-BROOKLYN",
                "SG-ddd-ANALYST-BRONX",
                "SG-SEALED-EVENT"
        ));

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        assertThat(token.getAuthorities()).hasSize(4);
    }

    @Test
    @DisplayName("convert - all authorities have ROLE_ prefix")
    void convert_allAuthoritiesHaveRolePrefix() {
        Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR", "SG-ddd-ANALYST-MANHATTAN"));

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        assertThat(token.getAuthorities())
                .allMatch(a -> a.getAuthority().startsWith("ROLE_"));
    }

    @Test
    @DisplayName("convert - single group produces single role")
    void convert_singleGroupProducesSingleRole() {
        Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR"));

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        assertThat(token.getAuthorities()).hasSize(1);
        assertThat(token.getAuthorities().iterator().next().getAuthority())
                .startsWith("ROLE_")
                .contains("SUPERVISOR");
    }

    @Test
    @DisplayName("convert - sealed event group converts correctly")
    void convert_sealedEventGroupConvertsCorrectly() {
        Jwt jwt = buildJwt(List.of("SG-SEALED-EVENT"));

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        assertThat(token.getAuthorities())
                .anyMatch(a -> a.getAuthority().contains("SEALED"));
    }

    @Test
    @DisplayName("convert - all analyst groups convert correctly")
    void convert_allAnalystGroupsConvertCorrectly() {
        Jwt jwt = buildJwt(Arrays.asList(
                "SG-ddd-ANALYST-BROOKLYN",
                "SG-ddd-ANALYST-BRONX",
                "SG-ddd-ANALYST-QUEENS",
                "SG-ddd-ANALYST-SI",
                "SG-ddd-ANALYST-MANHATTAN",
                "SG-ddd-ANALYST-SNP",
                "SG-ddd-ANALYST-REMANDED"
        ));

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        assertThat(token.getAuthorities()).hasSize(7);
        assertThat(token.getAuthorities())
                .allMatch(a -> a.getAuthority().startsWith("ROLE_"));
    }

    // =========================================================================
    // Helper — build JWT using individual .claim() calls
    // Using Map with claims() can silently drop keys with spaces
    // Use individual .claim() call per entry instead
    // =========================================================================

    private Jwt buildJwt(List<String> groups) {
        return Jwt.withTokenValue("mock-token")
                .header("alg", "RS256")
                .claim("sub", "jdoe")
                .claim("nnnn Groups", groups)   // individual claim() call
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();
    }
}
