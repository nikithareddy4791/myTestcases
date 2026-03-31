package org.nnnn.ddd;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.DB2Dialect;
import org.hibernate.mapping.Table;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.nnnn.ddd.audit.entity.CustomRevisionEntity;
import org.nnnn.ddd.entity.*;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EnversSchemaGenerator {

    public static void main(String[] args) {
        // 1. Define DB2 Hibernate properties
        Map<String, Object> settings = new HashMap<>();
        settings.put(AvailableSettings.DIALECT, DB2Dialect.class.getName());
        // Use 'legacy' strategy if you want to avoid 'rev_info_seq not found' errors
        // settings.put("hibernate.id.db_structure_naming_strategy", "legacy");

        // 2. Build the ServiceRegistry
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings)
                .build();

        try {
            // 3. Add your entities and custom revision class to MetadataSources
            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            metadataSources.addAnnotatedClass(CustomRevisionEntity.class);
            metadataSources.addAnnotatedClass(AdaList.class);
            metadataSources.addAnnotatedClass(CategoryList.class);
            metadataSources.addAnnotatedClass(dddOfficeList.class);
            metadataSources.addAnnotatedClass(ItemList.class);
            metadataSources.addAnnotatedClass(StatusList.class);
            metadataSources.addAnnotatedClass(TagList.class);
            metadataSources.addAnnotatedClass(dddCase.class);
            metadataSources.addAnnotatedClass(CaseItem.class);
            metadataSources.addAnnotatedClass(CaseTag.class);
            metadataSources.addAnnotatedClass(CaseNote.class);
            metadataSources.addAnnotatedClass(CaseUpload.class);

            // 4. Build Metadata
            Metadata metadata = metadataSources.buildMetadata();

            metadata.getDatabase().getNamespaces().forEach(namespace -> {
                Iterator<Table> tables = namespace.getTables().iterator();
                while (tables.hasNext()) {
                    Table table = tables.next();
                    String name = table.getName().toUpperCase();

                    // Define what to KEEP
                    boolean isAudit = name.endsWith("_AUD");
                    boolean isRev = name.equals("CUSTOM_REV_INFO");
                    boolean isSeq = name.contains("SEQ"); // Keep sequences for DB2

                    if (!isAudit && !isRev && !isSeq) {
                        tables.remove(); // This removes it from the generation queue
                    }
                }
            });

            // 5. Run SchemaExport to generate a script file
            SchemaExport schemaExport = new SchemaExport();
            schemaExport.setOutputFile("db2-envers-schema.sql");
            schemaExport.setFormat(true);
            schemaExport.setDelimiter(";");

            // Targets: SCRIPT (to file), STDOUT (to console). Action: CREATE
            schemaExport.execute(EnumSet.of(TargetType.SCRIPT, TargetType.STDOUT),
                    SchemaExport.Action.CREATE, metadata);

            System.out.println("Schema generation complete: db2-envers-schema.sql");
        } finally {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }
}







=================================

package org.nnnn.ddd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.nnnn.ddd.security.KeycloakAuthorityConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
            JwtAuthenticationConverter jwtAuthenticationConverter)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/health").permitAll()
                        .requestMatchers("/testRole", "/testAuth").authenticated()
                        .anyRequest().authenticated())

                .oauth2ResourceServer(oauth -> oauth
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            // 1. Read your custom claim
            List<String> groups = jwt.getClaimAsStringList("nnnn Groups");
            if (groups != null) {
                for (String g : groups) {
                    // Normalize: remove leading slash, uppercase, prefix ROLE_
                    String role = g.replace("/", "").toUpperCase();
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                }
            }

            return authorities;
        });

        return converter;
    }

}

======================================
package org.nnnn.ddd;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jasypt Configuration for encrypting sensitive properties.
 * 
 * This configuration creates a StringEncryptor bean that decrypts ENC(...)
 * values
 * in application.properties files.
 * 
 * The master password must be supplied outside Jasypt-decrypted properties
 * (never use ENC(...) for {@code jasypt.encryptor.password} in property files).
 * 
 * Provide it via:
 * <ul>
 * <li>{@code JASYPT_ENCRYPTOR_PASSWORD} environment variable, or</li>
 * <li>{@code -Djasypt.encryptor.password=...} in Liberty {@code jvm.options}
 * (or {@code jvmEntries})</li>
 * </ul>
 * 
 * Usage in properties:
 * spring.ldap.password=ENC(encrypted_value_here)
 */
@Configuration
public class JasyptConfig {

    /**
     * Master password is read only from the JVM (env / system property), not via
     * {@code @Value}, so resolution does not go through Jasypt's property
     * sources. That avoids {@link StackOverflowError} when the encryptor bean is
     * created.
     */
    private static String resolveEncryptorPassword() {
        String fromEnv = System.getenv("JASYPT_ENCRYPTOR_PASSWORD");
        if (fromEnv != null && !fromEnv.isEmpty()) {
            return fromEnv;
        }
        String fromSys = System.getProperty("jasypt.encryptor.password");
        return fromSys != null ? fromSys : "";
    }

    /**
     * Creates the Jasypt StringEncryptor bean.
     * Bean name must be "jasyptStringEncryptor" for auto-detection.
     * 
     * @return configured StringEncryptor
     */
    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(resolveEncryptorPassword());

        // Encryption algorithm - must match what was used to encrypt
        config.setAlgorithm("PBEWithMD5AndDES");

        // Number of hashing iterations
        config.setKeyObtentionIterations("1000");

        // Pool size for concurrent encryption/decryption
        config.setPoolSize("1");

        // Security provider
        config.setProviderName("SunJCE");

        // Salt generator for encryption
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");

        // IV generator (NoIvGenerator for PBEWithMD5AndDES)
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");

        // Output format (base64 encoding)
        config.setStringOutputType("base64");

        encryptor.setConfig(config);
        return encryptor;
    }
}



================================
package org.nnnn.ddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.actuate.health.*;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;


import javax.sql.DataSource;


import io.swagger.Swagger2SpringBoot;

@SpringBootApplication
@EnableScheduling
@EnableCaching
//@EnableAspectJAutoProxy
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Swagger2SpringBoot.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(ServletInitializer.class, args);
    }

    @Bean
    public HealthIndicator dbHealthIndicator(DataSource dataSource) {
        return new DataSourceHealthIndicator(dataSource, "SELECT 1 FROM SYSIBM.SYSDUMMY1");
    
    }


}

==========================
package org.nnnn.ddd;

public class AppConstants {
    public static final String SEALED_STRING = "SEALED";
    
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String SG_SUPERVISOR = "SG-ddd-SUPERVISOR";
    public static final String SG_ANALYST_BK = "SG-ddd-ANALYST-BROOKLYN";
    public static final String SG_ANALYST_BX = "SG-ddd-ANALYST-BRONX";
    public static final String SG_ANALYST_QN = "SG-ddd-ANALYST-QUEENS";
    public static final String SG_ANALYST_SI = "SG-ddd-ANALYST-SI";
    public static final String SG_ANALYST_MN = "SG-ddd-ANALYST-MANHATTAN";
    public static final String SG_ANALYST_SNP = "SG-ddd-ANALYST-SNP";
    public static final String SG_ANALYST_RMD = "SG-ddd-ANALYST-REMANDED";
    public static final String SG_SEALED_EVENT = "SG-SEALED-EVENT";

    public static final String SG_SEALED_EVENT_DN = "CN=SG-Sealed-Event,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest";
    
    public static final int DEFAULT_PAGE_SIZE = 100;

    public static final int STATUS_NOT_STARTED = 1;
    public static final int STATUS_IN_PROGRESS = 2;
    public static final int STATUS_WAITING = 3;
    public static final int STATUS_COMPLETED = 4;

    public static final String AUDIT_ACTION_LOGIN = "LOGIN";
    public static final String AUDIT_ACTION_LOGOUT = "LOGOUT";




