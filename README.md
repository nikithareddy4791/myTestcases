package org.nnnn.ddd.security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeycloakAuthorityConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // ----- Roles (e.g. ANALYST, SUPERVISOR) -----
        // You can pull from realm_access.roles or a custom claim
        List<String> roles = extractRealmRoles(jwt);
        if (roles != null) {
            roles.forEach(r -> authorities.add(new SimpleGrantedAuthority("ROLE_" + r.toUpperCase())));
        }

        // ----- Boroughs (e.g. BRONX, QUEENS) -----
        // Expecting a custom claim "boroughs": ["BRONX", "QUEENS"]
        List<String> boroughs = jwt.getClaimAsStringList("boroughs");
        if (boroughs != null) {
            boroughs.forEach(b -> authorities.add(new SimpleGrantedAuthority("BOROUGH_" + b.toUpperCase())));
        }

        return authorities;
    }

    @SuppressWarnings("unchecked")
    private List<String> extractRealmRoles(Jwt jwt) {
        Object realmAccess = jwt.getClaim("realm_access");
        if (realmAccess instanceof java.util.Map<?, ?> map) {
            Object roles = map.get("roles");
            if (roles instanceof List<?> list) {
                return (List<String>) list;
            }
        }
        // Fallback: direct "roles" claim if you prefer that mapping
        return jwt.getClaimAsStringList("roles");
    }
}
