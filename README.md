package org.nnnn.ddd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Uses a real SimpleCacheManager with real ConcurrentMapCache instances.
 * This avoids all Mockito stubbing issues with CacheManager.getCache().
 */
@DisplayName("CacheService Tests")
class CacheServiceTest {

    private CacheService cacheService;
    private SimpleCacheManager cacheManager;

    // Real cache instances — we can inspect them directly
    private ConcurrentMapCache userListCache;
    private ConcurrentMapCache sealedUserListCache;
    private ConcurrentMapCache adaListCache;
    private ConcurrentMapCache statusListCache;
    private ConcurrentMapCache dddOfficeListCache;
    private ConcurrentMapCache userdddOfficeListCache;
    private ConcurrentMapCache itemListCache;
    private ConcurrentMapCache categoryListCache;
    private ConcurrentMapCache tagListCache;
    private ConcurrentMapCache dddCodeRefCache;
    private ConcurrentMapCache userdddCodeRefCache;

    @BeforeEach
    void setUp() {
        // Create real cache instances
        userListCache           = new ConcurrentMapCache("userList");
        sealedUserListCache     = new ConcurrentMapCache("sealedUserList");
        adaListCache            = new ConcurrentMapCache("adaList");
        statusListCache         = new ConcurrentMapCache("statusList");
        dddOfficeListCache      = new ConcurrentMapCache("dddOfficeList");
        userdddOfficeListCache  = new ConcurrentMapCache("userdddOfficeList");
        itemListCache           = new ConcurrentMapCache("itemList");
        categoryListCache       = new ConcurrentMapCache("categoryList");
        tagListCache            = new ConcurrentMapCache("tagList");
        dddCodeRefCache         = new ConcurrentMapCache("dddCodeRef");
        userdddCodeRefCache     = new ConcurrentMapCache("userdddCodeRef");

        // Wire into SimpleCacheManager
        cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                userListCache, sealedUserListCache,
                adaListCache, statusListCache,
                dddOfficeListCache, userdddOfficeListCache,
                itemListCache, categoryListCache,
                tagListCache, dddCodeRefCache, userdddCodeRefCache
        ));
        cacheManager.afterPropertiesSet(); // required to initialize SimpleCacheManager

        // Inject real CacheManager into service
        cacheService = new CacheService();
        cacheService.cacheManager = cacheManager;

        // Pre-populate caches with dummy data so we can verify clear() works
        userListCache.put("key1", "value1");
        sealedUserListCache.put("key1", "value1");
        adaListCache.put("key1", "value1");
        statusListCache.put("key1", "value1");
        dddOfficeListCache.put("key1", "value1");
        userdddOfficeListCache.put("key1", "value1");
        itemListCache.put("key1", "value1");
        categoryListCache.put("key1", "value1");
        tagListCache.put("key1", "value1");
        dddCodeRefCache.put("key1", "value1");
        userdddCodeRefCache.put("key1", "value1");
    }

    // =========================================================================
    // clearAllCache()
    // =========================================================================

    @Test
    @DisplayName("clearAllCache - clears all registered caches")
    void clearAllCache_clearsAllCaches() {
        cacheService.clearAllCache();

        assertThat(userListCache.get("key1")).isNull();
        assertThat(sealedUserListCache.get("key1")).isNull();
        assertThat(adaListCache.get("key1")).isNull();
        assertThat(statusListCache.get("key1")).isNull();
        assertThat(dddOfficeListCache.get("key1")).isNull();
        assertThat(userdddOfficeListCache.get("key1")).isNull();
        assertThat(itemListCache.get("key1")).isNull();
        assertThat(categoryListCache.get("key1")).isNull();
        assertThat(tagListCache.get("key1")).isNull();
        assertThat(dddCodeRefCache.get("key1")).isNull();
        assertThat(userdddCodeRefCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearAllCache - cache is empty after clearing")
    void clearAllCache_cacheIsEmptyAfterClearing() {
        // Verify data exists before clear
        assertThat(userListCache.get("key1")).isNotNull();

        cacheService.clearAllCache();

        // Verify data is gone after clear
        assertThat(userListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearAllCache - can put new data after clearing")
    void clearAllCache_canPutDataAfterClearing() {
        cacheService.clearAllCache();

        // Should be able to put new data after clear
        userListCache.put("newKey", "newValue");
        assertThat(userListCache.get("newKey")).isNotNull();
    }

    // =========================================================================
    // clearUserCache()
    // =========================================================================

    @Test
    @DisplayName("clearUserCache - clears userList cache")
    void clearUserCache_clearsUserListCache() {
        cacheService.clearUserCache();

        assertThat(userListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearUserCache - clears sealedUserList cache")
    void clearUserCache_clearsSealedUserListCache() {
        cacheService.clearUserCache();

        assertThat(sealedUserListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearUserCache - does not clear reference data caches")
    void clearUserCache_doesNotClearReferenceCaches() {
        cacheService.clearUserCache();

        // Reference caches should still have their data
        assertThat(adaListCache.get("key1")).isNotNull();
        assertThat(statusListCache.get("key1")).isNotNull();
        assertThat(dddOfficeListCache.get("key1")).isNotNull();
        assertThat(userdddOfficeListCache.get("key1")).isNotNull();
        assertThat(itemListCache.get("key1")).isNotNull();
        assertThat(categoryListCache.get("key1")).isNotNull();
        assertThat(tagListCache.get("key1")).isNotNull();
        assertThat(dddCodeRefCache.get("key1")).isNotNull();
        assertThat(userdddCodeRefCache.get("key1")).isNotNull();
    }

    // =========================================================================
    // clearReferenceDataCache()
    // =========================================================================

    @Test
    @DisplayName("clearReferenceDataCache - clears adaList cache")
    void clearReferenceDataCache_clearsAdaList() {
        cacheService.clearReferenceDataCache();
        assertThat(adaListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears statusList cache")
    void clearReferenceDataCache_clearsStatusList() {
        cacheService.clearReferenceDataCache();
        assertThat(statusListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears dddOfficeList cache")
    void clearReferenceDataCache_clearsDddOfficeList() {
        cacheService.clearReferenceDataCache();
        assertThat(dddOfficeListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears userdddOfficeList cache")
    void clearReferenceDataCache_clearsUserdddOfficeList() {
        cacheService.clearReferenceDataCache();
        assertThat(userdddOfficeListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears itemList cache")
    void clearReferenceDataCache_clearsItemList() {
        cacheService.clearReferenceDataCache();
        assertThat(itemListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears categoryList cache")
    void clearReferenceDataCache_clearsCategoryList() {
        cacheService.clearReferenceDataCache();
        assertThat(categoryListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears tagList cache")
    void clearReferenceDataCache_clearsTagList() {
        cacheService.clearReferenceDataCache();
        assertThat(tagListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears dddCodeRef cache")
    void clearReferenceDataCache_clearsDddCodeRef() {
        cacheService.clearReferenceDataCache();
        assertThat(dddCodeRefCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears userdddCodeRef cache")
    void clearReferenceDataCache_clearsUserdddCodeRef() {
        cacheService.clearReferenceDataCache();
        assertThat(userdddCodeRefCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - does not clear user caches")
    void clearReferenceDataCache_doesNotClearUserCaches() {
        cacheService.clearReferenceDataCache();

        // User caches should still have their data
        assertThat(userListCache.get("key1")).isNotNull();
        assertThat(sealedUserListCache.get("key1")).isNotNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears all 9 reference caches at once")
    void clearReferenceDataCache_clearsAllReferenceCaches() {
        cacheService.clearReferenceDataCache();

        assertThat(adaListCache.get("key1")).isNull();
        assertThat(statusListCache.get("key1")).isNull();
        assertThat(dddOfficeListCache.get("key1")).isNull();
        assertThat(userdddOfficeListCache.get("key1")).isNull();
        assertThat(itemListCache.get("key1")).isNull();
        assertThat(categoryListCache.get("key1")).isNull();
        assertThat(tagListCache.get("key1")).isNull();
        assertThat(dddCodeRefCache.get("key1")).isNull();
        assertThat(userdddCodeRefCache.get("key1")).isNull();
    }
}
