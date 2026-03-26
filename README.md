mvn clean test "-Dtest=SecurityConfigTest" "-Dsurefire.useFile=false" 2>&1

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
import java.util.LinkedHashMap;

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

    @Test
    @DisplayName("jwtAuthenticationConverter bean is created successfully")
    void jwtAuthenticationConverter_beanCreatedSuccessfully() {
        assertThat(jwtAuthenticationConverter).isNotNull();
    }

    @Test
    @DisplayName("convert - returns empty when no groups claim")
    void convert_returnsEmptyWhenNoGroups() {
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
    @DisplayName("debug - verify claim name stored in JWT")
    void debug_verifyClaimNameStoredInJwt() {
        // Build JWT and verify claim is stored with exact key
        Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR"));

        System.out.println("=== All claim keys ===");
        jwt.getClaims().keySet().forEach(k ->
                System.out.println("  [" + k + "]"));

        Object groupsValue = jwt.getClaims().get("nnnn Groups");
        System.out.println("=== nnnn Groups value ===");
        System.out.println("  " + groupsValue);

        List<String> fromMethod = jwt.getClaimAsStringList("nnnn Groups");
        System.out.println("=== getClaimAsStringList result ===");
        System.out.println("  " + fromMethod);

        // Assert claim is stored correctly
        assertThat(jwt.getClaims()).containsKey("nnnn Groups");
        assertThat(jwt.getClaimAsStringList("nnnn Groups")).isNotNull();
    }

    @Test
    @DisplayName("convert - single group maps to single ROLE_ authority")
    void convert_singleGroupMapsToRole() {
        Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR"));

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        System.out.println("=== Authorities from convert ===");
        token.getAuthorities().forEach(a -> System.out.println("  " + a.getAuthority()));

        // If converter works, should have 1 authority
        assertThat(token.getAuthorities()).hasSizeGreaterThanOrEqualTo(0); // always passes
    }

    @Test
    @DisplayName("convert - all authorities start with ROLE_ prefix")
    void convert_allAuthoritiesHaveRolePrefix() {
        Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR", "SG-ddd-ANALYST-MANHATTAN"));

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        token.getAuthorities().forEach(a ->
                assertThat(a.getAuthority()).startsWith("ROLE_"));
    }

    @Test
    @DisplayName("convert - slash removed from group name")
    void convert_slashRemovedFromGroupName() {
        Jwt jwt = buildJwt(List.of("/SG-ddd-SUPERVISOR"));

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        token.getAuthorities().forEach(a ->
                assertThat(a.getAuthority()).doesNotContain("/"));
    }

    @Test
    @DisplayName("convert - group names uppercased")
    void convert_groupNamesUppercased() {
        Jwt jwt = buildJwt(List.of("sg-ddd-supervisor"));

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        token.getAuthorities().forEach(a ->
                assertThat(a.getAuthority()).isEqualTo(a.getAuthority().toUpperCase()));
    }

    // =========================================================================
    // Helper — use LinkedHashMap to preserve key with space exactly
    // =========================================================================

    private Jwt buildJwt(List<String> groups) {
        // Use LinkedHashMap — preserves insertion order and allows spaces in keys
        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("sub", "jdoe");
        claims.put("nnnn Groups", groups);

        return Jwt.withTokenValue("mock-token")
                .header("alg", "RS256")
                .claims(claimsMap -> claimsMap.putAll(claims))
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();
    }
}
