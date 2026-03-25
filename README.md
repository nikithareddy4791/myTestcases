package org.nnnn.ddd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CacheService Tests")
class CacheServiceTest {

    private CacheService cacheService;
    private SimpleCacheManager cacheManager;

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
    void setUp() throws Exception {
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

        cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                userListCache, sealedUserListCache,
                adaListCache, statusListCache,
                dddOfficeListCache, userdddOfficeListCache,
                itemListCache, categoryListCache,
                tagListCache, dddCodeRefCache, userdddCodeRefCache
        ));
        cacheManager.afterPropertiesSet();

        cacheService = new CacheService();

        // Try ReflectionTestUtils first, then fall back to direct field access
        try {
            ReflectionTestUtils.setField(cacheService, "cacheManager", cacheManager);
        } catch (Exception e) {
            // Fall back: set field directly via java.lang.reflect
            java.lang.reflect.Field field = findField(cacheService.getClass(), "cacheManager");
            field.setAccessible(true);
            field.set(cacheService, cacheManager);
        }

        // Verify injection worked
        Object injected = findAndGetField(cacheService, "cacheManager");
        if (injected != cacheManager) {
            throw new IllegalStateException(
                "CacheManager injection failed! injected=" + injected + " expected=" + cacheManager);
        }

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

    // Utility: find field in class or superclass hierarchy
    private java.lang.reflect.Field findField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        Class<?> current = clazz;
        while (current != null) {
            try {
                return current.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                current = current.getSuperclass();
            }
        }
        throw new NoSuchFieldException("Field '" + fieldName + "' not found in " + clazz.getName() + " or its superclasses");
    }

    // Utility: get field value from class or superclass
    private Object findAndGetField(Object target, String fieldName) throws Exception {
        java.lang.reflect.Field field = findField(target.getClass(), fieldName);
        field.setAccessible(true);
        return field.get(target);
    }

    // =========================================================================
    // DEBUG TEST — run this first to confirm injection works
    // =========================================================================

    @Test
    @DisplayName("DEBUG - verify CacheManager is correctly injected")
    void debug_verifyCacheManagerInjected() throws Exception {
        Object injected = findAndGetField(cacheService, "cacheManager");
        System.out.println("Injected: " + injected);
        System.out.println("Expected: " + cacheManager);
        System.out.println("Same instance: " + (injected == cacheManager));

        assertThat(injected).isSameAs(cacheManager);

        // Also verify getCache works
        assertThat(cacheManager.getCache("adaList")).isNotNull();
        assertThat(cacheManager.getCache("userdddOfficeList")).isNotNull();
        assertThat(cacheManager.getCache("dddCodeRef")).isNotNull();
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
    @DisplayName("clearAllCache - data is gone after clearing")
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
    void clearReferenceDataCache_clearsDddOfficeList() {
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
    void clearReferenceDataCache_clearsDddCodeRef() {
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
