package org.nnnn.ddd.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("KeycloakAuthorityConverter Tests")
class KeycloakAuthorityConverterTest {

    private KeycloakAuthorityConverter converter;

    @BeforeEach
    void setUp() {
        converter = new KeycloakAuthorityConverter();
    }

    // =========================================================================
    // convert() — realm_access.roles path
    // =========================================================================

    @Test
    @DisplayName("convert - extracts roles from realm_access.roles and prefixes with ROLE_")
    void convert_extractsRolesFromRealmAccess() {
        Map<String, Object> realmAccess = new HashMap<>();
        realmAccess.put("roles", List.of("ANALYST", "SUPERVISOR"));

        Jwt jwt = buildJwt(realmAccess, null, null);

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        assertThat(roles).contains("ROLE_ANALYST", "ROLE_SUPERVISOR");
    }

    @Test
    @DisplayName("convert - uppercases realm_access roles")
    void convert_uppercasesRealmAccessRoles() {
        Map<String, Object> realmAccess = new HashMap<>();
        realmAccess.put("roles", List.of("analyst", "supervisor"));

        Jwt jwt = buildJwt(realmAccess, null, null);

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities)
                .allMatch(a -> a.getAuthority().equals(a.getAuthority().toUpperCase()));
        assertThat(authorities).anyMatch(a -> a.getAuthority().equals("ROLE_ANALYST"));
        assertThat(authorities).anyMatch(a -> a.getAuthority().equals("ROLE_SUPERVISOR"));
    }

    @Test
    @DisplayName("convert - returns empty when realm_access has no roles key")
    void convert_returnsEmptyWhenRealmAccessHasNoRoles() {
        Map<String, Object> realmAccess = new HashMap<>();
        // no "roles" key

        Jwt jwt = buildJwt(realmAccess, null, null);

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).isEmpty();
    }

    @Test
    @DisplayName("convert - returns empty when realm_access is null")
    void convert_returnsEmptyWhenRealmAccessIsNull() {
        Jwt jwt = buildJwt(null, null, null);

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).isEmpty();
    }

    @Test
    @DisplayName("convert - returns empty when realm_access is not a Map")
    void convert_returnsEmptyWhenRealmAccessIsNotMap() {
        // realm_access is a String instead of a Map — falls through to roles claim
        Jwt jwt = Jwt.withTokenValue("mock-token")
                .header("alg", "RS256")
                .claim("sub", "jdoe")
                .claim("realm_access", "not-a-map")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        // No roles claim either, so empty
        assertThat(authorities).isEmpty();
    }

    // =========================================================================
    // convert() — fallback to direct "roles" claim
    // =========================================================================

    @Test
    @DisplayName("convert - falls back to direct roles claim when realm_access absent")
    void convert_fallsBackToDirectRolesClaim() {
        Jwt jwt = Jwt.withTokenValue("mock-token")
                .header("alg", "RS256")
                .claim("sub", "jdoe")
                .claim("roles", List.of("ANALYST"))
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).anyMatch(a -> a.getAuthority().equals("ROLE_ANALYST"));
    }

    @Test
    @DisplayName("convert - falls back to direct roles claim when realm_access is not a Map")
    void convert_fallsBackToDirectRolesClaimWhenRealmAccessNotMap() {
        Jwt jwt = Jwt.withTokenValue("mock-token")
                .header("alg", "RS256")
                .claim("sub", "jdoe")
                .claim("realm_access", "invalid")
                .claim("roles", List.of("SUPERVISOR"))
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).anyMatch(a -> a.getAuthority().equals("ROLE_SUPERVISOR"));
    }

    @Test
    @DisplayName("convert - falls back to direct roles when realm_access roles is not a List")
    void convert_fallsBackWhenRealmAccessRolesNotList() {
        Map<String, Object> realmAccess = new HashMap<>();
        realmAccess.put("roles", "not-a-list"); // String instead of List

        Jwt jwt = buildJwt(realmAccess, null, List.of("ANALYST"));

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        // Falls back to direct roles claim
        assertThat(authorities).anyMatch(a -> a.getAuthority().equals("ROLE_ANALYST"));
    }

    // =========================================================================
    // convert() — boroughs claim
    // =========================================================================

    @Test
    @DisplayName("convert - extracts boroughs and prefixes with BOROUGH_")
    void convert_extractsBoroughsWithBoroughPrefix() {
        Jwt jwt = buildJwt(null, List.of("BRONX", "QUEENS"), null);

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        List<String> boroughAuthorities = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        assertThat(boroughAuthorities).contains("BOROUGH_BRONX", "BOROUGH_QUEENS");
    }

    @Test
    @DisplayName("convert - uppercases borough names")
    void convert_uppercasesBoroughNames() {
        Jwt jwt = buildJwt(null, List.of("bronx", "queens"), null);

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).anyMatch(a -> a.getAuthority().equals("BOROUGH_BRONX"));
        assertThat(authorities).anyMatch(a -> a.getAuthority().equals("BOROUGH_QUEENS"));
    }

    @Test
    @DisplayName("convert - returns no borough authorities when boroughs claim absent")
    void convert_returnsNoBoroughsWhenClaimAbsent() {
        Jwt jwt = buildJwt(null, null, null);

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).noneMatch(a -> a.getAuthority().startsWith("BOROUGH_"));
    }

    @Test
    @DisplayName("convert - handles all borough names")
    void convert_handlesAllBoroughNames() {
        Jwt jwt = buildJwt(null,
                Arrays.asList("BRONX", "BROOKLYN", "QUEENS", "MANHATTAN", "STATEN_ISLAND"),
                null);

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        List<String> boroughAuthorities = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        assertThat(boroughAuthorities).contains(
                "BOROUGH_BRONX",
                "BOROUGH_BROOKLYN",
                "BOROUGH_QUEENS",
                "BOROUGH_MANHATTAN",
                "BOROUGH_STATEN_ISLAND"
        );
    }

    // =========================================================================
    // convert() — combined roles and boroughs
    // =========================================================================

    @Test
    @DisplayName("convert - combines roles and boroughs into single authority list")
    void convert_combinesRolesAndBoroughs() {
        Map<String, Object> realmAccess = new HashMap<>();
        realmAccess.put("roles", List.of("ANALYST"));

        Jwt jwt = buildJwt(realmAccess, List.of("BRONX", "QUEENS"), null);

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        List<String> authorityList = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        assertThat(authorityList).contains("ROLE_ANALYST");
        assertThat(authorityList).contains("BOROUGH_BRONX", "BOROUGH_QUEENS");
        assertThat(authorities).hasSize(3);
    }

    @Test
    @DisplayName("convert - returns all ROLE_ authorities when multiple roles present")
    void convert_returnsAllRoleAuthorities() {
        Map<String, Object> realmAccess = new HashMap<>();
        realmAccess.put("roles", List.of("ANALYST", "SUPERVISOR", "ADMIN"));

        Jwt jwt = buildJwt(realmAccess, null, null);

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).hasSize(3);
        assertThat(authorities).allMatch(a -> a.getAuthority().startsWith("ROLE_"));
    }

    @Test
    @DisplayName("convert - returns empty collection when no claims present")
    void convert_returnsEmptyWhenNoClaimsPresent() {
        Jwt jwt = Jwt.withTokenValue("mock-token")
                .header("alg", "RS256")
                .claim("sub", "jdoe")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).isEmpty();
    }

    // =========================================================================
    // Helper — build JWT with realm_access, boroughs, and direct roles claims
    // =========================================================================

    private Jwt buildJwt(Map<String, Object> realmAccess,
                          List<String> boroughs,
                          List<String> directRoles) {
        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("sub", "jdoe");

        if (realmAccess != null) {
            claims.put("realm_access", realmAccess);
        }
        if (boroughs != null) {
            claims.put("boroughs", boroughs);
        }
        if (directRoles != null) {
            claims.put("roles", directRoles);
        }

        return Jwt.withTokenValue("mock-token")
                .header("alg", "RS256")
                .claims(c -> c.putAll(claims))
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();
    }
}
