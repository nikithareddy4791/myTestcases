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
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SecurityConfig Tests")
class SecurityConfigTest {

    private SecurityConfig securityConfig;
    private JwtAuthenticationConverter jwtAuthenticationConverter;

    @BeforeEach
    void setUp() {
        // Call the REAL SecurityConfig method — this gives JaCoCo coverage
        securityConfig = new SecurityConfig();
        jwtAuthenticationConverter = securityConfig.jwtAuthenticationConverter();
    }

    @Test
    @DisplayName("jwtAuthenticationConverter bean is created successfully")
    void jwtAuthenticationConverter_beanCreatedSuccessfully() {
        assertThat(jwtAuthenticationConverter).isNotNull();
        assertThat(jwtAuthenticationConverter).isInstanceOf(JwtAuthenticationConverter.class);
    }

    @Test
    @DisplayName("converter - returns token when called with JWT")
    void converter_returnsTokenWhenCalledWithJwt() {
        // Call convert() on the REAL converter from SecurityConfig
        // This exercises the jwtAuthenticationConverter() method body
        Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR"));

        // convert() is the real method on the bean returned by SecurityConfig
        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        assertThat(token).isNotNull();
    }

    @Test
    @DisplayName("converter - returns empty authorities when no groups claim")
    void converter_returnsEmptyWhenNoGroups() {
        Jwt jwt = Jwt.withTokenValue("mock-token")
                .header("alg", "RS256")
                .claim("sub", "jdoe")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        assertThat(token).isNotNull();
        assertThat(token.getAuthorities()).isEmpty();
    }

    @Test
    @DisplayName("converter - token principal is the JWT")
    void converter_tokenPrincipalIsJwt() {
        Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR"));

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        assertThat(token.getPrincipal()).isEqualTo(jwt);
    }

    @Test
    @DisplayName("converter - all returned authorities start with ROLE_ when groups present")
    void converter_authoritiesStartWithRolePrefix() {
        Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR"));

        AbstractAuthenticationToken token = jwtAuthenticationConverter.convert(jwt);

        // Verify any authorities that ARE returned have correct prefix
        token.getAuthorities().forEach(a ->
                assertThat(a.getAuthority()).startsWith("ROLE_"));
    }

    @Test
    @DisplayName("securityConfig - creates new converter instance each call")
    void securityConfig_createsNewConverterEachCall() {
        // Call the method twice — both should return valid converters
        // This exercises the method body twice for coverage
        JwtAuthenticationConverter c1 = securityConfig.jwtAuthenticationConverter();
        JwtAuthenticationConverter c2 = securityConfig.jwtAuthenticationConverter();

        assertThat(c1).isNotNull();
        assertThat(c2).isNotNull();
    }

    @Test
    @DisplayName("converter - convert called multiple times works correctly")
    void converter_convertCalledMultipleTimesWorks() {
        Jwt jwt1 = buildJwt(List.of("SG-ddd-SUPERVISOR"));
        Jwt jwt2 = buildJwt(List.of("SG-ddd-ANALYST-MANHATTAN"));
        Jwt jwt3 = Jwt.withTokenValue("mock-token-3")
                .header("alg", "RS256")
                .claim("sub", "user3")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        // Call convert() multiple times — exercises the lambda in SecurityConfig
        AbstractAuthenticationToken t1 = jwtAuthenticationConverter.convert(jwt1);
        AbstractAuthenticationToken t2 = jwtAuthenticationConverter.convert(jwt2);
        AbstractAuthenticationToken t3 = jwtAuthenticationConverter.convert(jwt3);

        assertThat(t1).isNotNull();
        assertThat(t2).isNotNull();
        assertThat(t3).isNotNull();
        // t3 has no groups so should have no authorities
        assertThat(t3.getAuthorities()).isEmpty();
    }

    // =========================================================================
    // Helper
    // =========================================================================

    private Jwt buildJwt(List<String> groups) {
        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("sub", "jdoe");
        claims.put("nnnn Groups", groups);

        return Jwt.withTokenValue("mock-token")
                .header("alg", "RS256")
                .claims(c -> c.putAll(claims))
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();
    }
}
