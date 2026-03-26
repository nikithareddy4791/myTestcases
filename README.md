package org.nnnn.ddd;

import java.util.stream.Collectors;

import org.checkerframework.checker.units.qual.mm;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.nnnn.ddd.converter.LocalDateToDateConverter;
import org.nnnn.ddd.converter.TimestampToOffsetDateTimeConverter;
import org.nnnn.ddd.entity.CaseTag;
import org.nnnn.ddd.entity.dddCase;
import org.nnnn.ddd.model.dddCase;
import org.nnnn.ddd.model.Tag;
import org.nnnn.ddd.converter.DateToLocalDateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm = new ModelMapper();
        // Configure to be strict to avoid unwanted mapping
        mm.addConverter(new LocalDateToDateConverter());
        mm.addConverter(new DateToLocalDateConverter());
        mm.addConverter(new TimestampToOffsetDateTimeConverter());
        mm.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setAmbiguityIgnored(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<dddCase, dddCase> typeMap = mm.emptyTypeMap(dddCase.class, dddCase.class);
        typeMap.addMappings(mapper -> {
            mapper.skip(dddCase::setAda);
            mapper.skip(dddCase::setItems);
            mapper.skip(dddCase::setTags);
            mapper.skip(dddCase::setNotes);
            mapper.skip(dddCase::setUploads);
            mapper.skip(dddCase::setCategory);
            mapper.skip(dddCase::setStatus);
            mapper.skip(dddCase::setddd);
            // Explicitly map version from client for optimistic lock WHERE clause
            mapper.map(dddCase::getVersion, dddCase::setVersion);
        });
        typeMap.implicitMappings();
        
        mm.typeMap(org.nnnn.ddd.entity.CaseTag.class, org.nnnn.ddd.model.CaseTag.class).addMappings(mapper -> {
            mapper.map(src -> src.getTag().getTagDesc(), org.nnnn.ddd.model.CaseTag::setTagDesc);
            mapper.map(src -> src.getTag().getId(), org.nnnn.ddd.model.CaseTag::setTagId);
        });

        mm.typeMap(org.nnnn.ddd.entity.CaseItem.class, org.nnnn.ddd.model.CaseItem.class).addMappings(mapper -> {
            mapper.map(src -> src.getItem().getItemDesc(), org.nnnn.ddd.model.CaseItem::setItemDesc);
            mapper.map(src -> src.getItem().getId(), org.nnnn.ddd.model.CaseItem::setItemId);
        });

        return mm;
    }
}


=============



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



=========
package org.nnnn.ddd;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.checksums.RequestChecksumCalculation;
import software.amazon.awssdk.core.checksums.ResponseChecksumValidation;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

@Configuration
public class DellS3Config {

    @Value("${dell.s3.endpoint}")
    private String endpoint;

    @Value("${dell.s3.access-key}")
    private String accessKey;

    @Value("${dell.s3.secret-key}")
    private String secretKey;

    @Bean
    public S3Client s3Client() {
        // Dell ECS often requires path-style access
        S3Configuration serviceConfig = S3Configuration.builder()
                .pathStyleAccessEnabled(true)
                .build();

        return S3Client.builder()
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .region(Region.US_EAST_1) // Region is required but ignored by ECS
                .requestChecksumCalculation(RequestChecksumCalculation.WHEN_REQUIRED)
                .responseChecksumValidation(ResponseChecksumValidation.WHEN_REQUIRED)
                .serviceConfiguration(serviceConfig)
                .build();
    }
}


==============

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
}



