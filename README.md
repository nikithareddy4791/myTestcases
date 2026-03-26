package org.nnnn.ddd.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nnnn.ddd.AppConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthenticationService Tests")
class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    private Jwt mockJwt;

    @BeforeEach
    void setUp() {
        mockJwt = mock(Jwt.class);
    }

    @AfterEach
    void tearDown() {
        // Always clear SecurityContext after each test
        SecurityContextHolder.clearContext();
    }

    // =========================================================================
    // Helper — sets up SecurityContext with a JWT token and given roles
    // =========================================================================

    private void setUpSecurityContext(String username, String... roles) {
        when(mockJwt.getClaimAsString("preferred_username")).thenReturn(username);

        List<SimpleGrantedAuthority> authorities = Arrays.stream(roles)
                .map(SimpleGrantedAuthority::new)
                .toList();

        JwtAuthenticationToken token = new JwtAuthenticationToken(mockJwt, authorities);
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    private void setUpSecurityContextNoRoles(String username) {
        setUpSecurityContext(username);
    }

    // =========================================================================
    // authenticatedUser()
    // =========================================================================

    @Test
    @DisplayName("authenticatedUser - returns authenticated message")
    void authenticatedUser_returnsMessage() {
        assertThat(authenticationService.authenticatedUser())
                .isEqualTo("You are authenticated");
    }

    // =========================================================================
    // getUsername()
    // =========================================================================

    @Test
    @DisplayName("getUsername - returns uppercase username from JWT claim")
    void getUsername_jwtPrincipal_returnsUpperCaseUsername() {
        setUpSecurityContext("jdoe");

        String result = authenticationService.getUsername();

        assertThat(result).isEqualTo("JDOE");
    }

    @Test
    @DisplayName("getUsername - returns username in uppercase regardless of input case")
    void getUsername_lowercaseInput_returnsUpperCase() {
        setUpSecurityContext("john.doe");

        assertThat(authenticationService.getUsername()).isEqualTo("JOHN.DOE");
    }

    @Test
    @DisplayName("getUsername - returns null when principal is not a JWT")
    void getUsername_nonJwtPrincipal_returnsNull() {
        // Set up with a non-JWT authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn("simpleStringPrincipal");
        when(auth.getAuthorities()).thenReturn(Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(auth);

        String result = authenticationService.getUsername();

        assertThat(result).isNull();
    }

    // =========================================================================
    // hasRole()
    // =========================================================================

    @Test
    @DisplayName("hasRole - returns true when user has the specified role")
    void hasRole_userHasRole_returnsTrue() {
        setUpSecurityContext("jdoe", "ROLE_SG-ddd-SUPERVISOR");

        assertThat(authenticationService.hasRole("ROLE_SG-ddd-SUPERVISOR")).isTrue();
    }

    @Test
    @DisplayName("hasRole - returns false when user does not have the specified role")
    void hasRole_userDoesNotHaveRole_returnsFalse() {
        setUpSecurityContext("jdoe", "ROLE_SG-ddd-ANALYST-MANHATTAN");

        assertThat(authenticationService.hasRole("ROLE_SG-ddd-SUPERVISOR")).isFalse();
    }

    @Test
    @DisplayName("hasRole - returns false when user has no roles")
    void hasRole_noRoles_returnsFalse() {
        setUpSecurityContextNoRoles("jdoe");

        assertThat(authenticationService.hasRole("ROLE_SG-ddd-SUPERVISOR")).isFalse();
    }

    @Test
    @DisplayName("hasRole - returns false when role name does not match exactly")
    void hasRole_partialRoleMatch_returnsFalse() {
        setUpSecurityContext("jdoe", "ROLE_SG-ddd-SUPERVISOR");

        // Partial match should not return true
        assertThat(authenticationService.hasRole("ROLE_SG-ddd")).isFalse();
    }

    @Test
    @DisplayName("hasRole - returns true when user has multiple roles including target")
    void hasRole_multipleRoles_returnsTrue() {
        setUpSecurityContext("jdoe",
                "ROLE_SG-ddd-ANALYST-MANHATTAN",
                "ROLE_SG-ddd-SUPERVISOR",
                "ROLE_SG-ddd-SEALED-EVENT");

        assertThat(authenticationService.hasRole("ROLE_SG-ddd-SUPERVISOR")).isTrue();
    }

    // =========================================================================
    // isSupervisor()
    // =========================================================================

    @Test
    @DisplayName("isSupervisor - returns true when user has supervisor role")
    void isSupervisor_hasSupervisorRole_returnsTrue() {
        setUpSecurityContext("jdoe",
                AppConstants.ROLE_PREFIX + AppConstants.SG_SUPERVISOR);

        assertThat(authenticationService.isSupervisor()).isTrue();
    }

    @Test
    @DisplayName("isSupervisor - returns false when user does not have supervisor role")
    void isSupervisor_noSupervisorRole_returnsFalse() {
        setUpSecurityContext("jdoe", "ROLE_SG-ddd-ANALYST-MANHATTAN");

        assertThat(authenticationService.isSupervisor()).isFalse();
    }

    @Test
    @DisplayName("isSupervisor - returns false when user has no roles")
    void isSupervisor_noRoles_returnsFalse() {
        setUpSecurityContextNoRoles("jdoe");

        assertThat(authenticationService.isSupervisor()).isFalse();
    }

    // =========================================================================
    // hasSealedAccess()
    // =========================================================================

    @Test
    @DisplayName("hasSealedAccess - returns true when user has sealed event role")
    void hasSealedAccess_hasSealedRole_returnsTrue() {
        setUpSecurityContext("jdoe",
                AppConstants.ROLE_PREFIX + AppConstants.SG_SEALED_EVENT);

        assertThat(authenticationService.hasSealedAccess()).isTrue();
    }

    @Test
    @DisplayName("hasSealedAccess - returns false when user does not have sealed role")
    void hasSealedAccess_noSealedRole_returnsFalse() {
        setUpSecurityContext("jdoe", "ROLE_SG-ddd-SUPERVISOR");

        assertThat(authenticationService.hasSealedAccess()).isFalse();
    }

    @Test
    @DisplayName("hasSealedAccess - returns false when user has no roles")
    void hasSealedAccess_noRoles_returnsFalse() {
        setUpSecurityContextNoRoles("jdoe");

        assertThat(authenticationService.hasSealedAccess()).isFalse();
    }

    // =========================================================================
    // Combined role scenarios
    // =========================================================================

    @Test
    @DisplayName("supervisor with sealed access - both return true")
    void supervisorWithSealedAccess_bothReturnTrue() {
        setUpSecurityContext("jdoe",
                AppConstants.ROLE_PREFIX + AppConstants.SG_SUPERVISOR,
                AppConstants.ROLE_PREFIX + AppConstants.SG_SEALED_EVENT);

        assertThat(authenticationService.isSupervisor()).isTrue();
        assertThat(authenticationService.hasSealedAccess()).isTrue();
    }

    @Test
    @DisplayName("analyst without supervisor or sealed - both return false")
    void analyst_notSupervisorNotSealed_bothReturnFalse() {
        setUpSecurityContext("jdoe", "ROLE_SG-ddd-ANALYST-MANHATTAN");

        assertThat(authenticationService.isSupervisor()).isFalse();
        assertThat(authenticationService.hasSealedAccess()).isFalse();
    }

    // =========================================================================
    // debug() — just verify it runs without exception
    // =========================================================================

    @Test
    @DisplayName("debug - runs without throwing exception")
    void debug_withJwtPrincipal_runsWithoutException() {
        setUpSecurityContext("jdoe",
                AppConstants.ROLE_PREFIX + AppConstants.SG_SUPERVISOR);

        // debug() just logs — verify it doesn't throw
        authenticationService.debug();
    }
}
