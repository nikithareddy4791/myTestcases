package org.nnnn.ddd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

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
    // jwtAuthenticationConverter() bean
    // =========================================================================

    @Nested
    @DisplayName("jwtAuthenticationConverter()")
    class JwtAuthenticationConverterTests {

        @Test
        @DisplayName("returns non-null JwtAuthenticationConverter bean")
        void jwtAuthenticationConverter_returnsNonNull() {
            assertThat(jwtAuthenticationConverter).isNotNull();
        }

        @Test
        @DisplayName("single group maps to ROLE_ prefixed authority")
        void convert_singleGroup_mapsToRoleAuthority() {
            Jwt jwt = buildJwt(List.of("SG-ddd-ANALYST-MANHATTAN"));

            Collection<GrantedAuthority> authorities = convertAndGetAuthorities(jwt);

            assertThat(authorities)
                    .extracting(GrantedAuthority::getAuthority)
                    .contains("ROLE_SG-DDD-ANALYST-MANHATTAN");
        }

        @Test
        @DisplayName("multiple groups map to multiple ROLE_ authorities")
        void convert_multipleGroups_mapsAllToRoleAuthorities() {
            Jwt jwt = buildJwt(List.of("SG-ddd-ANALYST-BROOKLYN", "SG-ddd-SUPERVISOR"));

            Collection<GrantedAuthority> authorities = convertAndGetAuthorities(jwt);

            assertThat(authorities)
                    .extracting(GrantedAuthority::getAuthority)
                    .contains("ROLE_SG-DDD-ANALYST-BROOKLYN", "ROLE_SG-DDD-SUPERVISOR");
        }

        @Test
        @DisplayName("group with leading slash has slash removed")
        void convert_groupWithLeadingSlash_removesSlash() {
            Jwt jwt = buildJwt(List.of("/SG-ddd-ANALYST-BRONX"));

            Collection<GrantedAuthority> authorities = convertAndGetAuthorities(jwt);

            assertThat(authorities)
                    .extracting(GrantedAuthority::getAuthority)
                    .contains("ROLE_SG-DDD-ANALYST-BRONX");
        }

        @Test
        @DisplayName("group is uppercased in authority")
        void convert_lowercaseGroup_isUppercased() {
            Jwt jwt = buildJwt(List.of("sg-ddd-analyst-queens"));

            Collection<GrantedAuthority> authorities = convertAndGetAuthorities(jwt);

            assertThat(authorities)
                    .extracting(GrantedAuthority::getAuthority)
                    .contains("ROLE_SG-DDD-ANALYST-QUEENS");
        }

        @Test
        @DisplayName("null groups claim returns empty authorities")
        void convert_nullGroupsClaim_returnsEmptyAuthorities() {
            Jwt jwt = buildJwtNoGroups();

            Collection<GrantedAuthority> authorities = convertAndGetAuthorities(jwt);

            assertThat(authorities).isEmpty();
        }

        @Test
        @DisplayName("empty groups list returns empty authorities")
        void convert_emptyGroupsList_returnsEmptyAuthorities() {
            Jwt jwt = buildJwt(List.of());

            Collection<GrantedAuthority> authorities = convertAndGetAuthorities(jwt);

            assertThat(authorities).isEmpty();
        }

        @Test
        @DisplayName("supervisor group maps correctly")
        void convert_supervisorGroup_mapsToSupervisorRole() {
            Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR"));

            Collection<GrantedAuthority> authorities = convertAndGetAuthorities(jwt);

            assertThat(authorities)
                    .extracting(GrantedAuthority::getAuthority)
                    .contains("ROLE_SG-DDD-SUPERVISOR");
        }

        @Test
        @DisplayName("sealed event group maps correctly")
        void convert_sealedEventGroup_mapsToSealedRole() {
            Jwt jwt = buildJwt(List.of("SG-SEALED-EVENT"));

            Collection<GrantedAuthority> authorities = convertAndGetAuthorities(jwt);

            assertThat(authorities)
                    .extracting(GrantedAuthority::getAuthority)
                    .contains("ROLE_SG-SEALED-EVENT");
        }

        @Test
        @DisplayName("all borough analyst groups map correctly")
        void convert_allBoroughGroups_mapCorrectly() {
            Jwt jwt = buildJwt(List.of(
                    "SG-ddd-ANALYST-BROOKLYN",
                    "SG-ddd-ANALYST-BRONX",
                    "SG-ddd-ANALYST-QUEENS",
                    "SG-ddd-ANALYST-SI",
                    "SG-ddd-ANALYST-MANHATTAN"
            ));

            Collection<GrantedAuthority> authorities = convertAndGetAuthorities(jwt);

            assertThat(authorities)
                    .extracting(GrantedAuthority::getAuthority)
                    .contains(
                            "ROLE_SG-DDD-ANALYST-BROOKLYN",
                            "ROLE_SG-DDD-ANALYST-BRONX",
                            "ROLE_SG-DDD-ANALYST-QUEENS",
                            "ROLE_SG-DDD-ANALYST-SI",
                            "ROLE_SG-DDD-ANALYST-MANHATTAN"
                    );
        }

        @Test
        @DisplayName("authority count matches group count")
        void convert_authorityCountMatchesGroupCount() {
            List<String> groups = List.of(
                    "SG-ddd-SUPERVISOR",
                    "SG-ddd-ANALYST-MANHATTAN",
                    "SG-SEALED-EVENT"
            );
            Jwt jwt = buildJwt(groups);

            Collection<GrantedAuthority> authorities = convertAndGetAuthorities(jwt);

            assertThat(authorities).hasSize(3);
        }
    }

    // =========================================================================
    // Helpers
    // =========================================================================

    /**
     * Converts JWT and extracts authorities from the returned JwtAuthenticationToken.
     * converter.convert() returns JwtAuthenticationToken — NOT a Collection.
     * We call .getAuthorities() on it to get the authorities.
     */
    private Collection<GrantedAuthority> convertAndGetAuthorities(Jwt jwt) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) jwtAuthenticationConverter.convert(jwt);
        assertThat(token).isNotNull();
        return (Collection<GrantedAuthority>) token.getAuthorities();
    }

    private Jwt buildJwt(List<String> groups) {
        return Jwt.withTokenValue("test-token")
                .header("alg", "RS256")
                .issuer("https://keycloak.test/auth")
                .subject("test-user")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .claim("nnnn Groups", groups)
                .build();
    }

    private Jwt buildJwtNoGroups() {
        return Jwt.withTokenValue("test-token")
                .header("alg", "RS256")
                .issuer("https://keycloak.test/auth")
                .subject("test-user")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();
    }
}
