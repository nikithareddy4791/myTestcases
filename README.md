package org.nnnn.ddd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CacheConfig Tests")
class CacheConfigTest {

    private CacheManager cacheManager;

    @BeforeEach
    void setUp() throws Exception {
        CacheConfig config = new CacheConfig();
        cacheManager = config.cacheManager();
        // SimpleCacheManager requires afterPropertiesSet() to initialize
        // Spring calls this automatically in a container but we must call it manually in tests
        ((org.springframework.cache.support.SimpleCacheManager) cacheManager).afterPropertiesSet();
    }

    // =========================================================================
    // Bean creation
    // =========================================================================

    @Test
    @DisplayName("cacheManager bean is created successfully")
    void cacheManager_beanCreatedSuccessfully() {
        assertThat(cacheManager).isNotNull();
    }

    @Test
    @DisplayName("cacheManager contains exactly 10 caches")
    void cacheManager_containsExactly10Caches() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        assertThat(cacheNames).hasSize(10);
    }

    // =========================================================================
    // Reference data caches (1 day TTL)
    // =========================================================================

    @Test
    @DisplayName("adaList cache exists")
    void cacheManager_containsAdaListCache() {
        assertThat(cacheManager.getCache("adaList")).isNotNull();
    }

    @Test
    @DisplayName("statusList cache exists")
    void cacheManager_containsStatusListCache() {
        assertThat(cacheManager.getCache("statusList")).isNotNull();
    }

    @Test
    @DisplayName("dddOfficeList cache exists")
    void cacheManager_containsDddOfficeListCache() {
        assertThat(cacheManager.getCache("dddOfficeList")).isNotNull();
    }

    @Test
    @DisplayName("itemList cache exists")
    void cacheManager_containsItemListCache() {
        assertThat(cacheManager.getCache("itemList")).isNotNull();
    }

    @Test
    @DisplayName("dddCodeRef cache exists")
    void cacheManager_containsDddCodeRefCache() {
        assertThat(cacheManager.getCache("dddCodeRef")).isNotNull();
    }

    @Test
    @DisplayName("categoryList cache exists")
    void cacheManager_containsCategoryListCache() {
        assertThat(cacheManager.getCache("categoryList")).isNotNull();
    }

    @Test
    @DisplayName("tagList cache exists")
    void cacheManager_containsTagListCache() {
        assertThat(cacheManager.getCache("tagList")).isNotNull();
    }

    @Test
    @DisplayName("userdddOfficeList cache exists")
    void cacheManager_containsUserdddOfficeListCache() {
        assertThat(cacheManager.getCache("userdddOfficeList")).isNotNull();
    }

    // =========================================================================
    // User caches (1 hour TTL)
    // =========================================================================

    @Test
    @DisplayName("userList cache exists")
    void cacheManager_containsUserListCache() {
        assertThat(cacheManager.getCache("userList")).isNotNull();
    }

    @Test
    @DisplayName("sealedUserList cache exists")
    void cacheManager_containsSealedUserListCache() {
        assertThat(cacheManager.getCache("sealedUserList")).isNotNull();
    }

    // =========================================================================
    // Cache is functional — can put and get values
    // =========================================================================

    @Test
    @DisplayName("adaList cache can store and retrieve values")
    void adaListCache_canStoreAndRetrieveValues() {
        Cache cache = cacheManager.getCache("adaList");
        cache.put("key1", "value1");
        assertThat(cache.get("key1", String.class)).isEqualTo("value1");
    }

    @Test
    @DisplayName("statusList cache can store and retrieve values")
    void statusListCache_canStoreAndRetrieveValues() {
        Cache cache = cacheManager.getCache("statusList");
        cache.put("key1", 42);
        assertThat(cache.get("key1", Integer.class)).isEqualTo(42);
    }

    @Test
    @DisplayName("userList cache can store and retrieve values")
    void userListCache_canStoreAndRetrieveValues() {
        Cache cache = cacheManager.getCache("userList");
        cache.put("user1", "John Doe");
        assertThat(cache.get("user1", String.class)).isEqualTo("John Doe");
    }

    @Test
    @DisplayName("sealedUserList cache can store and retrieve values")
    void sealedUserListCache_canStoreAndRetrieveValues() {
        Cache cache = cacheManager.getCache("sealedUserList");
        cache.put("user2", "Jane Doe");
        assertThat(cache.get("user2", String.class)).isEqualTo("Jane Doe");
    }

    @Test
    @DisplayName("cache returns null for missing key")
    void cache_returnsNullForMissingKey() {
        Cache cache = cacheManager.getCache("adaList");
        assertThat(cache.get("nonexistent", String.class)).isNull();
    }

    @Test
    @DisplayName("cache evicts value after clear")
    void cache_evictsValueAfterClear() {
        Cache cache = cacheManager.getCache("adaList");
        cache.put("key1", "value1");
        cache.clear();
        assertThat(cache.get("key1", String.class)).isNull();
    }

    // =========================================================================
    // Non-existent cache returns null
    // =========================================================================

    @Test
    @DisplayName("cacheManager returns null for unknown cache name")
    void cacheManager_returnsNullForUnknownCacheName() {
        assertThat(cacheManager.getCache("nonExistentCache")).isNull();
    }

    // =========================================================================
    // All cache names are present
    // =========================================================================

    @Test
    @DisplayName("cacheManager contains all expected cache names")
    void cacheManager_containsAllExpectedCacheNames() {
        Collection<String> names = cacheManager.getCacheNames();
        assertThat(names).contains(
                "adaList",
                "statusList",
                "dddOfficeList",
                "itemList",
                "dddCodeRef",
                "categoryList",
                "tagList",
                "userdddOfficeList",
                "userList",
                "sealedUserList"
        );
    }
}
