package org.nnnn.ddd.service;

import org.nnnn.ddd.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    public void debug() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getAuthorities().forEach(authority -> {
            System.out.println("Role: " + authority.getAuthority());
        });
        if (auth.getPrincipal() instanceof Jwt jwt) {
            log.info("preferred_username "+jwt.getClaimAsString("preferred_username"));
            
        }
        log.info("hasSealedAccess? "+hasSealedAccess());
        log.info("isSupervisor? "+isSupervisor());
    }

    public String authenticatedUser() {
        return "You are authenticated";
    }

    @PreAuthorize("hasAnyRole('ROLE_SG-ddd-SUPERVISOR', 'ROLE_SG-ddd-ANALYST-BROOKLYN', 'ROLE_SG-ddd-ANALYST-BRONX', 'ROLE_SG-ddd-ANALYST-QUEENS', 'ROLE_SG-ddd-ANALYST-SI', 'ROLE_SG-ddd-ANALYST-MANHATTAN', , 'ROLE_SG-ddd-ANALYST-SNP', 'ROLE_SG-ddd-ANALYST-REMANDED')")
    public String analystOrSupervisor() {
        return "You are authenticated AND in one of the required roles";
    }

    public String getUsername()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaimAsString("preferred_username").toUpperCase();
        }
        return null;
    }

    public boolean isSupervisor() {
        return hasRole(AppConstants.ROLE_PREFIX+AppConstants.SG_SUPERVISOR);
    }

    public boolean hasSealedAccess() {
        return hasRole(AppConstants.ROLE_PREFIX+AppConstants.SG_SEALED_EVENT);
    }

    public boolean hasRole(final String role) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isRole = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(role));

        if (isRole) {
            return true;
        }
        return false;
    }
}
