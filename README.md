package org.nnnn.ddd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.nnnn.ddd.entity.CaseItem;
import org.nnnn.ddd.entity.CaseTag;
import org.nnnn.ddd.entity.ItemList;
import org.nnnn.ddd.entity.TagList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayName("MapperConfig Tests")
class MapperConfigTest {

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MapperConfig config = new MapperConfig();
        modelMapper = config.modelMapper();
    }

    @Test
    @DisplayName("modelMapper bean is created successfully")
    void modelMapper_beanCreatedSuccessfully() {
        assertThat(modelMapper).isNotNull();
    }

    @Test
    @DisplayName("modelMapper has strict matching strategy")
    void modelMapper_hasStrictMatchingStrategy() {
        assertThat(modelMapper.getConfiguration().getMatchingStrategy())
                .isEqualTo(org.modelmapper.convention.MatchingStrategies.STRICT);
    }

    @Test
    @DisplayName("modelMapper has field matching enabled")
    void modelMapper_hasFieldMatchingEnabled() {
        assertThat(modelMapper.getConfiguration().isFieldMatchingEnabled()).isTrue();
    }

    @Test
    @DisplayName("modelMapper has ambiguity ignored")
    void modelMapper_hasAmbiguityIgnored() {
        assertThat(modelMapper.getConfiguration().isAmbiguityIgnored()).isTrue();
    }

    @Test
    @DisplayName("modelMapper has private field access level")
    void modelMapper_hasPrivateFieldAccessLevel() {
        assertThat(modelMapper.getConfiguration().getFieldAccessLevel())
                .isEqualTo(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    }

    // =========================================================================
    // CaseTag → model.CaseTag mapping
    // =========================================================================

    @Test
    @DisplayName("CaseTag maps tagDesc from nested Tag entity")
    void caseTag_mapsTagDescFromNestedTag() {
        TagList tagList = new TagList();
        tagList.setId(1);
        tagList.setTagDesc("Gun");
        CaseTag entityTag = new CaseTag();
        entityTag.setId(10);
        entityTag.setTag(tagList);

        org.nnnn.ddd.model.CaseTag modelTag = modelMapper.map(entityTag, org.nnnn.ddd.model.CaseTag.class);

        assertThat(modelTag.getTagDesc()).isEqualTo("Gun");
    }

    @Test
    @DisplayName("CaseTag maps tagId from nested Tag entity")
    void caseTag_mapsTagIdFromNestedTag() {
        TagList tagList = new TagList();
        tagList.setId(5);
        tagList.setTagDesc("Gang");
        CaseTag entityTag = new CaseTag();
        entityTag.setId(10);
        entityTag.setTag(tagList);

        org.nnnn.ddd.model.CaseTag modelTag = modelMapper.map(entityTag, org.nnnn.ddd.model.CaseTag.class);

        assertThat(modelTag.getTagId()).isEqualTo(5);
    }

    // =========================================================================
    // CaseItem → model.CaseItem mapping
    // =========================================================================

    @Test
    @DisplayName("CaseItem maps itemDesc from nested Item entity")
    void caseItem_mapsItemDescFromNestedItem() {
        ItemList itemList = new ItemList();
        itemList.setId(1);
        itemList.setItemDesc("ECMS");
        CaseItem entityItem = new CaseItem();
        entityItem.setId(10);
        entityItem.setItem(itemList);

        org.nnnn.ddd.model.CaseItem modelItem = modelMapper.map(entityItem, org.nnnn.ddd.model.CaseItem.class);

        assertThat(modelItem.getItemDesc()).isEqualTo("ECMS");
    }

    @Test
    @DisplayName("CaseItem maps itemId from nested Item entity")
    void caseItem_mapsItemIdFromNestedItem() {
        ItemList itemList = new ItemList();
        itemList.setId(3);
        itemList.setItemDesc("Court Records");
        CaseItem entityItem = new CaseItem();
        entityItem.setId(10);
        entityItem.setItem(itemList);

        org.nnnn.ddd.model.CaseItem modelItem = modelMapper.map(entityItem, org.nnnn.ddd.model.CaseItem.class);

        assertThat(modelItem.getItemId()).isEqualTo(3);
    }

    // =========================================================================
    // dddCase entity → model mapping
    // =========================================================================

    @Test
    @DisplayName("dddCase maps basic scalar fields correctly")
    void dddCase_mapsBasicFields() {
        org.nnnn.ddd.entity.dddCase entity = new org.nnnn.ddd.entity.dddCase();
        entity.setId(100);
        entity.setArrId("ARR001");
        entity.setAssignedNm("jdoe");
        entity.setVersion(1L);

        org.nnnn.ddd.model.dddCase model = modelMapper.map(entity, org.nnnn.ddd.model.dddCase.class);

        assertThat(model.getId()).isEqualTo(100);
        assertThat(model.getArrId()).isEqualTo("ARR001");
        assertThat(model.getAssignedNm()).isEqualTo("jdoe");
        assertThat(model.getVersion()).isEqualTo(1L);
    }

    @Test
    @DisplayName("dddCase maps version explicitly for optimistic locking")
    void dddCase_mapsVersionExplicitly() {
        org.nnnn.ddd.entity.dddCase entity = new org.nnnn.ddd.entity.dddCase();
        entity.setId(100);
        entity.setVersion(5L);

        org.nnnn.ddd.model.dddCase model = modelMapper.map(entity, org.nnnn.ddd.model.dddCase.class);

        assertThat(model.getVersion()).isEqualTo(5L);
    }

    @Test
    @DisplayName("dddCase maps proactiveFlg correctly")
    void dddCase_mapsProactiveFlg() {
        org.nnnn.ddd.entity.dddCase entity = new org.nnnn.ddd.entity.dddCase();
        entity.setId(100);
        entity.setProactiveFlg((short) 1);

        org.nnnn.ddd.model.dddCase model = modelMapper.map(entity, org.nnnn.ddd.model.dddCase.class);

        assertThat(model.getProactiveFlg()).isEqualTo(1);
    }

    @Test
    @DisplayName("dddCase maps dvFlg correctly")
    void dddCase_mapsDvFlg() {
        org.nnnn.ddd.entity.dddCase entity = new org.nnnn.ddd.entity.dddCase();
        entity.setId(100);
        entity.setDvFlg((short) 1);

        org.nnnn.ddd.model.dddCase model = modelMapper.map(entity, org.nnnn.ddd.model.dddCase.class);

        assertThat(model.getDvFlg()).isEqualTo(1);
    }

    @Test
    @DisplayName("dddCase mapping does not throw for empty entity")
    void dddCase_emptyEntityDoesNotThrow() {
        org.nnnn.ddd.entity.dddCase entity = new org.nnnn.ddd.entity.dddCase();

        assertThatNoException().isThrownBy(() ->
                modelMapper.map(entity, org.nnnn.ddd.model.dddCase.class));
    }

    @Test
    @DisplayName("dddCase maps null arrId correctly")
    void dddCase_mapsNullArrId() {
        org.nnnn.ddd.entity.dddCase entity = new org.nnnn.ddd.entity.dddCase();
        entity.setId(100);
        entity.setArrId(null);

        org.nnnn.ddd.model.dddCase model = modelMapper.map(entity, org.nnnn.ddd.model.dddCase.class);

        assertThat(model.getArrId()).isNull();
    }
}







======================

package org.nnnn.ddd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SecurityConfig Tests")
class SecurityConfigTest {

    private SecurityConfig securityConfig;
    // Test the granted authorities converter directly via reflection
    // since JwtAuthenticationConverter.convert() wraps it
    private org.springframework.core.convert.converter.Converter<Jwt, Collection<GrantedAuthority>>
            grantedAuthoritiesConverter;

    @BeforeEach
    void setUp() throws Exception {
        securityConfig = new SecurityConfig();
        JwtAuthenticationConverter jwtConverter = securityConfig.jwtAuthenticationConverter();

        // Extract the internal grantedAuthoritiesConverter via reflection
        // so we can test it directly without needing a full auth token
        java.lang.reflect.Field field = JwtAuthenticationConverter.class
                .getDeclaredField("jwtGrantedAuthoritiesConverter");
        field.setAccessible(true);
        grantedAuthoritiesConverter =
                (org.springframework.core.convert.converter.Converter<Jwt, Collection<GrantedAuthority>>)
                        field.get(jwtConverter);
    }

    // =========================================================================
    // jwtAuthenticationConverter bean
    // =========================================================================

    @Test
    @DisplayName("jwtAuthenticationConverter bean is created successfully")
    void jwtAuthenticationConverter_beanCreatedSuccessfully() {
        assertThat(securityConfig.jwtAuthenticationConverter()).isNotNull();
    }

    // =========================================================================
    // grantedAuthoritiesConverter — test the lambda directly
    // =========================================================================

    @Test
    @DisplayName("converter maps nnnn Groups claim to ROLE_ prefixed authorities")
    void converter_mapsNnnnGroupsToRoleAuthorities() {
        Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR", "SG-ddd-ANALYST-MANHATTAN"));

        Collection<GrantedAuthority> authorities = grantedAuthoritiesConverter.convert(jwt);

        assertThat(authorities).isNotNull();
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        assertThat(roles).anyMatch(r -> r.contains("SUPERVISOR"));
        assertThat(roles).anyMatch(r -> r.contains("MANHATTAN"));
        assertThat(roles).allMatch(r -> r.startsWith("ROLE_"));
    }

    @Test
    @DisplayName("converter removes slash characters from group names")
    void converter_removesSlashFromGroupNames() {
        Jwt jwt = buildJwt(List.of("/SG-ddd-SUPERVISOR"));

        Collection<GrantedAuthority> authorities = grantedAuthoritiesConverter.convert(jwt);

        assertThat(authorities).isNotNull().isNotEmpty();
        assertThat(authorities).allMatch(a -> !a.getAuthority().contains("/"));
    }

    @Test
    @DisplayName("converter uppercases group names")
    void converter_uppercasesGroupNames() {
        Jwt jwt = buildJwt(List.of("sg-ddd-supervisor"));

        Collection<GrantedAuthority> authorities = grantedAuthoritiesConverter.convert(jwt);

        assertThat(authorities).isNotNull().isNotEmpty();
        assertThat(authorities).allMatch(a -> a.getAuthority().equals(a.getAuthority().toUpperCase()));
    }

    @Test
    @DisplayName("converter returns empty when nnnn Groups claim is absent")
    void converter_returnsEmptyWhenGroupsAbsent() {
        Jwt jwt = Jwt.withTokenValue("mock-token")
                .header("alg", "RS256")
                .claim("sub", "jdoe")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        Collection<GrantedAuthority> authorities = grantedAuthoritiesConverter.convert(jwt);

        assertThat(authorities).isEmpty();
    }

    @Test
    @DisplayName("converter maps multiple groups to multiple roles")
    void converter_mapsMultipleGroupsToMultipleRoles() {
        Jwt jwt = buildJwt(Arrays.asList(
                "SG-ddd-SUPERVISOR",
                "SG-ddd-ANALYST-BROOKLYN",
                "SG-ddd-ANALYST-BRONX",
                "SG-SEALED-EVENT"
        ));

        Collection<GrantedAuthority> authorities = grantedAuthoritiesConverter.convert(jwt);

        assertThat(authorities).hasSize(4);
    }

    @Test
    @DisplayName("all converted authorities have ROLE_ prefix")
    void converter_allAuthoritiesHaveRolePrefix() {
        Jwt jwt = buildJwt(Arrays.asList("SG-ddd-SUPERVISOR", "SG-ddd-ANALYST-MANHATTAN"));

        Collection<GrantedAuthority> authorities = grantedAuthoritiesConverter.convert(jwt);

        assertThat(authorities).allMatch(a -> a.getAuthority().startsWith("ROLE_"));
    }

    @Test
    @DisplayName("converter handles single group correctly")
    void converter_handlesSingleGroup() {
        Jwt jwt = buildJwt(List.of("SG-ddd-SUPERVISOR"));

        Collection<GrantedAuthority> authorities = grantedAuthoritiesConverter.convert(jwt);

        assertThat(authorities).hasSize(1);
        assertThat(authorities.iterator().next().getAuthority())
                .startsWith("ROLE_")
                .contains("SUPERVISOR");
    }

    // =========================================================================
    // Helper
    // =========================================================================

    private Jwt buildJwt(List<String> groups) {
        Map<String, Object> claims = new HashMap<>();
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



