Get-Content "src\main\java\org\nnnn\ddd\service\CacheService.java" | Select-String "getCache"


// ❌ Old — SimpleCacheManager name lookup fails due to hidden characters
cacheManager = new SimpleCacheManager();
cacheManager.setCaches(Arrays.asList(userListCache, ...));

// ✅ New — mock returns exact cache object for each string key
cacheManager = mock(CacheManager.class);
when(cacheManager.getCache("userList")).thenReturn(userListCache);
when(cacheManager.getCache("adaList")).thenReturn(adaListCache);
// etc.



package org.nnnn.ddd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("CacheService Tests")
class CacheServiceTest {

    private CacheService cacheService;

    // Real caches — so we can verify clear() was actually called
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

    // Mock CacheManager — we control exactly what getCache(name) returns
    // This avoids hidden character issues with SimpleCacheManager name lookup
    private CacheManager cacheManager;

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

        // Mock CacheManager — getCache() returns exact cache by string key
        cacheManager = mock(CacheManager.class);
        when(cacheManager.getCache("userList")).thenReturn(userListCache);
        when(cacheManager.getCache("sealedUserList")).thenReturn(sealedUserListCache);
        when(cacheManager.getCache("adaList")).thenReturn(adaListCache);
        when(cacheManager.getCache("statusList")).thenReturn(statusListCache);
        when(cacheManager.getCache("dddOfficeList")).thenReturn(dddOfficeListCache);
        when(cacheManager.getCache("userdddOfficeList")).thenReturn(userdddOfficeListCache);
        when(cacheManager.getCache("itemList")).thenReturn(itemListCache);
        when(cacheManager.getCache("categoryList")).thenReturn(categoryListCache);
        when(cacheManager.getCache("tagList")).thenReturn(tagListCache);
        when(cacheManager.getCache("dddCodeRef")).thenReturn(dddCodeRefCache);
        when(cacheManager.getCache("userdddCodeRef")).thenReturn(userdddCodeRefCache);

        // getCacheNames() for clearAllCache()
        when(cacheManager.getCacheNames()).thenReturn(Arrays.asList(
                "userList", "sealedUserList", "adaList", "statusList",
                "dddOfficeList", "userdddOfficeList", "itemList",
                "categoryList", "tagList", "dddCodeRef", "userdddCodeRef"
        ));

        // Inject mock cacheManager into service
        cacheService = new CacheService();
        cacheService.setCacheManager(cacheManager);

        // Pre-populate all caches
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
    @DisplayName("clearAllCache - data gone after clearing")
    void clearAllCache_dataGoneAfterClearing() {
        assertThat(userListCache.get("key1")).isNotNull();
        cacheService.clearAllCache();
        assertThat(userListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearAllCache - can put new data after clearing")
    void clearAllCache_canPutDataAfterClearing() {
        cacheService.clearAllCache();
        userListCache.put("newKey", "newValue");
        assertThat(userListCache.get("newKey")).isNotNull();
    }

    // =========================================================================
    // clearUserCache()
    // =========================================================================

    @Test
    @DisplayName("clearUserCache - clears userList")
    void clearUserCache_clearsUserList() {
        cacheService.clearUserCache();
        assertThat(userListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearUserCache - clears sealedUserList")
    void clearUserCache_clearsSealedUserList() {
        cacheService.clearUserCache();
        assertThat(sealedUserListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearUserCache - does not clear reference caches")
    void clearUserCache_doesNotClearReferenceCaches() {
        cacheService.clearUserCache();

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
    @DisplayName("clearReferenceDataCache - clears adaList")
    void clearReferenceDataCache_clearsAdaList() {
        cacheService.clearReferenceDataCache();
        assertThat(adaListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears statusList")
    void clearReferenceDataCache_clearsStatusList() {
        cacheService.clearReferenceDataCache();
        assertThat(statusListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears dddOfficeList")
    void clearReferenceDataCache_clearsdddOfficeList() {
        cacheService.clearReferenceDataCache();
        assertThat(dddOfficeListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears userdddOfficeList")
    void clearReferenceDataCache_clearsUserdddOfficeList() {
        cacheService.clearReferenceDataCache();
        assertThat(userdddOfficeListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears itemList")
    void clearReferenceDataCache_clearsItemList() {
        cacheService.clearReferenceDataCache();
        assertThat(itemListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears categoryList")
    void clearReferenceDataCache_clearsCategoryList() {
        cacheService.clearReferenceDataCache();
        assertThat(categoryListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears tagList")
    void clearReferenceDataCache_clearsTagList() {
        cacheService.clearReferenceDataCache();
        assertThat(tagListCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears dddCodeRef")
    void clearReferenceDataCache_clearsdddCodeRef() {
        cacheService.clearReferenceDataCache();
        assertThat(dddCodeRefCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears userdddCodeRef")
    void clearReferenceDataCache_clearsUserdddCodeRef() {
        cacheService.clearReferenceDataCache();
        assertThat(userdddCodeRefCache.get("key1")).isNull();
    }

    @Test
    @DisplayName("clearReferenceDataCache - clears all 9 reference caches")
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

    @Test
    @DisplayName("clearReferenceDataCache - does not clear user caches")
    void clearReferenceDataCache_doesNotClearUserCaches() {
        cacheService.clearReferenceDataCache();

        assertThat(userListCache.get("key1")).isNotNull();
        assertThat(sealedUserListCache.get("key1")).isNotNull();
    }
}
