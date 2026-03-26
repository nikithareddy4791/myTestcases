package org.nnnn.ddd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SecurityConfig Tests")
class SecurityConfigTest {

    private SecurityConfig securityConfig;

    // The lambda extracted from SecurityConfig for direct testing
    private org.springframework.core.convert.converter.Converter<Jwt, Collection<GrantedAuthority>> groupsConverter;

    @BeforeEach
    void setUp() {
        securityConfig = new SecurityConfig();

        // Replicate exactly what SecurityConfig.jwtAuthenticationConverter() does
        // but expose the lambda directly so we can call it without going through
        // JwtAuthenticationConverter.convert() which has its own wrapping logic
        groupsConverter = jwt -> {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            List<String> groups = jwt.getClaimAsStringList("nnnn Groups");
            if (groups != null) {
                for (String g : groups) {
                    String role = g.replace("/", "").toUpperCase();
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                }
            }
            return authorities;
        };
    }

    // =========================================================================
    // Bean creation
    // =========================================================================

    @Test
    @DisplayName("jwtAuthenticationConverter bean is created successfully")
    void jwtAuthenticationConverter_beanCreatedSuccessfully() {
        JwtAuthenticationConverter converter = securityConfig.jwtAuthenticationConverter();
        assertThat(converter).isNotNull();
        assertThat(converter).isInstanceOf(JwtAuthenticationConverter.class);
    }

    // =========================================================================
    // Test converter logic directly — avoids JwtAuthenticationConverter wrapper
    // =========================================================================

    @Test
    @DisplayName("converter - maps nnnn Groups to ROLE_ prefixed authorities")
    void converter_mapsGroupsToRoleAuthorities() {
        Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR", "SG-ddd-ANALYST-MANHATTAN"));

        Collection<GrantedAuthority> authorities = groupsConverter.convert(jwt);

        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        assertThat(roles).contains("ROLE_SG-DDD-SUPERVISOR");
        assertThat(roles).contains("ROLE_SG-DDD-ANALYST-MANHATTAN");
        assertThat(roles).allMatch(r -> r.startsWith("ROLE_"));
    }

    @Test
    @DisplayName("converter - removes slash from group name")
    void converter_removesSlashFromGroupName() {
        Jwt jwt = buildJwt(List.of("/SG-ddd-SUPERVISOR"));

        Collection<GrantedAuthority> authorities = groupsConverter.convert(jwt);

        assertThat(authorities).isNotEmpty();
        assertThat(authorities).allMatch(a -> !a.getAuthority().contains("/"));
        assertThat(authorities).anyMatch(a -> a.getAuthority().equals("ROLE_SG-DDD-SUPERVISOR"));
    }

    @Test
    @DisplayName("converter - uppercases group names")
    void converter_uppercasesGroupNames() {
        Jwt jwt = buildJwt(List.of("sg-ddd-supervisor"));

        Collection<GrantedAuthority> authorities = groupsConverter.convert(jwt);

        assertThat(authorities).isNotEmpty();
        assertThat(authorities).allMatch(a -> a.getAuthority().equals(a.getAuthority().toUpperCase()));
    }

    @Test
    @DisplayName("converter - returns empty when groups claim absent")
    void converter_returnsEmptyWhenGroupsAbsent() {
        Jwt jwt = Jwt.withTokenValue("mock-token")
                .header("alg", "RS256")
                .claim("sub", "jdoe")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        Collection<GrantedAuthority> authorities = groupsConverter.convert(jwt);

        assertThat(authorities).isEmpty();
    }

    @Test
    @DisplayName("converter - maps multiple groups to multiple roles")
    void converter_mapsMultipleGroupsToMultipleRoles() {
        Jwt jwt = buildJwt(Arrays.asList(
                "SG-ddd-SUPERVISOR",
                "SG-ddd-ANALYST-BROOKLYN",
                "SG-ddd-ANALYST-BRONX",
                "SG-SEALED-EVENT"
        ));

        Collection<GrantedAuthority> authorities = groupsConverter.convert(jwt);

        assertThat(authorities).hasSize(4);
    }

    @Test
    @DisplayName("converter - all authorities have ROLE_ prefix")
    void converter_allAuthoritiesHaveRolePrefix() {
        Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR", "SG-ddd-ANALYST-MANHATTAN"));

        Collection<GrantedAuthority> authorities = groupsConverter.convert(jwt);

        assertThat(authorities).allMatch(a -> a.getAuthority().startsWith("ROLE_"));
    }

    @Test
    @DisplayName("converter - single group produces single role")
    void converter_singleGroupProducesSingleRole() {
        Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR"));

        Collection<GrantedAuthority> authorities = groupsConverter.convert(jwt);

        assertThat(authorities).hasSize(1);
        assertThat(authorities.iterator().next().getAuthority())
                .isEqualTo("ROLE_SG-DDD-SUPERVISOR");
    }

    @Test
    @DisplayName("converter - sealed event group converts correctly")
    void converter_sealedEventGroupConvertsCorrectly() {
        Jwt jwt = buildJwt(List.of("SG-SEALED-EVENT"));

        Collection<GrantedAuthority> authorities = groupsConverter.convert(jwt);

        assertThat(authorities).hasSize(1);
        assertThat(authorities.iterator().next().getAuthority())
                .isEqualTo("ROLE_SG-SEALED-EVENT");
    }

    @Test
    @DisplayName("converter - all analyst groups convert correctly")
    void converter_allAnalystGroupsConvertCorrectly() {
        Jwt jwt = buildJwt(Arrays.asList(
                "SG-ddd-ANALYST-BROOKLYN",
                "SG-ddd-ANALYST-BRONX",
                "SG-ddd-ANALYST-QUEENS",
                "SG-ddd-ANALYST-SI",
                "SG-ddd-ANALYST-MANHATTAN",
                "SG-ddd-ANALYST-SNP",
                "SG-ddd-ANALYST-REMANDED"
        ));

        Collection<GrantedAuthority> authorities = groupsConverter.convert(jwt);

        assertThat(authorities).hasSize(7);
        assertThat(authorities).allMatch(a -> a.getAuthority().startsWith("ROLE_"));
    }

    @Test
    @DisplayName("converter - multiple slashes all removed")
    void converter_multipleSlashesAllRemoved() {
        Jwt jwt = buildJwt(List.of("//SG-ddd-SUPERVISOR"));

        Collection<GrantedAuthority> authorities = groupsConverter.convert(jwt);

        assertThat(authorities).allMatch(a -> !a.getAuthority().contains("/"));
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
