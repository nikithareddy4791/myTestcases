package org.nnnn.ddd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.time.Instant;
import java.util.Collection;
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

            Collection<GrantedAuthority> authorities = extractAuthorities(jwt);

            assertThat(authorities)
                    .extracting(GrantedAuthority::getAuthority)
                    .contains("ROLE_SG-DDD-ANALYST-MANHATTAN");
        }

        @Test
        @DisplayName("multiple groups map to multiple ROLE_ authorities")
        void convert_multipleGroups_mapsAllToRoleAuthorities() {
            Jwt jwt = buildJwt(List.of("SG-ddd-ANALYST-BROOKLYN", "SG-ddd-SUPERVISOR"));

            Collection<GrantedAuthority> authorities = extractAuthorities(jwt);

            assertThat(authorities)
                    .extracting(GrantedAuthority::getAuthority)
                    .contains("ROLE_SG-DDD-ANALYST-BROOKLYN", "ROLE_SG-DDD-SUPERVISOR");
        }

        @Test
        @DisplayName("group with leading slash has slash removed")
        void convert_groupWithLeadingSlash_removesSlash() {
            Jwt jwt = buildJwt(List.of("/SG-ddd-ANALYST-BRONX"));

            Collection<GrantedAuthority> authorities = extractAuthorities(jwt);

            assertThat(authorities)
                    .extracting(GrantedAuthority::getAuthority)
                    .contains("ROLE_SG-DDD-ANALYST-BRONX");
        }

        @Test
        @DisplayName("group is uppercased in authority")
        void convert_lowercaseGroup_isUppercased() {
            Jwt jwt = buildJwt(List.of("sg-ddd-analyst-queens"));

            Collection<GrantedAuthority> authorities = extractAuthorities(jwt);

            assertThat(authorities)
                    .extracting(GrantedAuthority::getAuthority)
                    .contains("ROLE_SG-DDD-ANALYST-QUEENS");
        }

        @Test
        @DisplayName("null groups claim returns empty authorities")
        void convert_nullGroupsClaim_returnsEmptyAuthorities() {
            Jwt jwt = buildJwtNoGroups();

            Collection<GrantedAuthority> authorities = extractAuthorities(jwt);

            assertThat(authorities).isEmpty();
        }

        @Test
        @DisplayName("empty groups list returns empty authorities")
        void convert_emptyGroupsList_returnsEmptyAuthorities() {
            Jwt jwt = buildJwt(List.of());

            Collection<GrantedAuthority> authorities = extractAuthorities(jwt);

            assertThat(authorities).isEmpty();
        }

        @Test
        @DisplayName("supervisor group maps correctly")
        void convert_supervisorGroup_mapsToSupervisorRole() {
            Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR"));

            Collection<GrantedAuthority> authorities = extractAuthorities(jwt);

            assertThat(authorities)
                    .extracting(GrantedAuthority::getAuthority)
                    .contains("ROLE_SG-DDD-SUPERVISOR");
        }

        @Test
        @DisplayName("sealed event group maps correctly")
        void convert_sealedEventGroup_mapsToSealedRole() {
            Jwt jwt = buildJwt(List.of("SG-SEALED-EVENT"));

            Collection<GrantedAuthority> authorities = extractAuthorities(jwt);

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

            Collection<GrantedAuthority> authorities = extractAuthorities(jwt);

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
    }

    // =========================================================================
    // Helpers
    // =========================================================================

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

    @SuppressWarnings("unchecked")
    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        var converter = jwtAuthenticationConverter.getJwtGrantedAuthoritiesConverter();
        return (Collection<GrantedAuthority>) converter.convert(jwt);
    }
}


====================================


package org.nnnn.ddd;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JasyptConfig Tests")
class JasyptConfigTest {

    private JasyptConfig jasyptConfig;

    // Save and restore system properties to avoid test pollution
    private String originalSysProp;
    private String originalEnvProp;

    @BeforeEach
    void setUp() {
        jasyptConfig = new JasyptConfig();
        originalSysProp = System.getProperty("jasypt.encryptor.password");
    }

    @AfterEach
    void tearDown() {
        // Restore system property
        if (originalSysProp != null) {
            System.setProperty("jasypt.encryptor.password", originalSysProp);
        } else {
            System.clearProperty("jasypt.encryptor.password");
        }
    }

    // =========================================================================
    // stringEncryptor() bean
    // =========================================================================

    @Nested
    @DisplayName("stringEncryptor() bean")
    class StringEncryptorBeanTests {

        @Test
        @DisplayName("returns non-null StringEncryptor bean")
        void stringEncryptor_returnsNonNull() {
            System.setProperty("jasypt.encryptor.password", "testpassword");
            StringEncryptor encryptor = jasyptConfig.stringEncryptor();
            assertThat(encryptor).isNotNull();
        }

        @Test
        @DisplayName("encryptor can encrypt a value")
        void stringEncryptor_canEncrypt() {
            System.setProperty("jasypt.encryptor.password", "testpassword");
            StringEncryptor encryptor = jasyptConfig.stringEncryptor();
            String encrypted = encryptor.encrypt("mySecretValue");
            assertThat(encrypted).isNotNull().isNotEmpty().isNotEqualTo("mySecretValue");
        }

        @Test
        @DisplayName("encryptor can decrypt an encrypted value")
        void stringEncryptor_canDecrypt() {
            System.setProperty("jasypt.encryptor.password", "testpassword");
            StringEncryptor encryptor = jasyptConfig.stringEncryptor();
            String original = "mySecretValue";
            String encrypted = encryptor.encrypt(original);
            String decrypted = encryptor.decrypt(encrypted);
            assertThat(decrypted).isEqualTo(original);
        }

        @Test
        @DisplayName("encrypt and decrypt roundtrip for DB password")
        void stringEncryptor_roundtrip_dbPassword() {
            System.setProperty("jasypt.encryptor.password", "testpassword");
            StringEncryptor encryptor = jasyptConfig.stringEncryptor();
            String dbPassword = "s3cur3_db_p@ssword!";
            assertThat(encryptor.decrypt(encryptor.encrypt(dbPassword))).isEqualTo(dbPassword);
        }

        @Test
        @DisplayName("encrypt and decrypt roundtrip for LDAP password")
        void stringEncryptor_roundtrip_ldapPassword() {
            System.setProperty("jasypt.encryptor.password", "testpassword");
            StringEncryptor encryptor = jasyptConfig.stringEncryptor();
            String ldapPassword = "ldap_s3cr3t";
            assertThat(encryptor.decrypt(encryptor.encrypt(ldapPassword))).isEqualTo(ldapPassword);
        }

        @Test
        @DisplayName("two encryptions of same value produce different ciphertext (random salt)")
        void stringEncryptor_randomSalt_differentCiphertextEachTime() {
            System.setProperty("jasypt.encryptor.password", "testpassword");
            StringEncryptor encryptor = jasyptConfig.stringEncryptor();
            String value = "mySecretValue";
            String enc1 = encryptor.encrypt(value);
            String enc2 = encryptor.encrypt(value);
            // Random salt means each encryption produces different ciphertext
            assertThat(enc1).isNotEqualTo(enc2);
            // But both should decrypt to same original
            assertThat(encryptor.decrypt(enc1)).isEqualTo(value);
            assertThat(encryptor.decrypt(enc2)).isEqualTo(value);
        }

        @Test
        @DisplayName("empty string password still creates encryptor")
        void stringEncryptor_emptyPassword_createsEncryptor() {
            System.clearProperty("jasypt.encryptor.password");
            // With no env var and no system property, password is empty string
            StringEncryptor encryptor = jasyptConfig.stringEncryptor();
            assertThat(encryptor).isNotNull();
        }

        @Test
        @DisplayName("system property password takes precedence when env not set")
        void stringEncryptor_systemPropertyPassword_usedWhenEnvNotSet() {
            System.setProperty("jasypt.encryptor.password", "sysproppassword");
            StringEncryptor encryptor = jasyptConfig.stringEncryptor();
            // Should be able to encrypt/decrypt with this password
            String encrypted = encryptor.encrypt("test");
            assertThat(encryptor.decrypt(encrypted)).isEqualTo("test");
        }
    }
}


