package org.nnnn.ddd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("CacheService Tests")
class CacheServiceTest {

    @Mock private CacheManager cacheManager;

    // Declare ALL cache mocks as @Mock fields — NOT created with mock() inside setUp()
    // This ensures Mockito manages their lifecycle correctly
    @Mock private Cache userListCache;
    @Mock private Cache sealedUserListCache;
    @Mock private Cache adaListCache;
    @Mock private Cache statusListCache;
    @Mock private Cache dddOfficeListCache;
    @Mock private Cache userdddOfficeListCache;
    @Mock private Cache itemListCache;
    @Mock private Cache categoryListCache;
    @Mock private Cache tagListCache;
    @Mock private Cache dddCodeRefCache;
    @Mock private Cache userdddCodeRefCache;

    @InjectMocks
    private CacheService cacheService;

    @BeforeEach
    void setUp() {
        // Wire all cache mocks to their names using doReturn
        doReturn(userListCache).when(cacheManager).getCache("userList");
        doReturn(sealedUserListCache).when(cacheManager).getCache("sealedUserList");
        doReturn(adaListCache).when(cacheManager).getCache("adaList");
        doReturn(statusListCache).when(cacheManager).getCache("statusList");
        doReturn(dddOfficeListCache).when(cacheManager).getCache("dddOfficeList");
        doReturn(userdddOfficeListCache).when(cacheManager).getCache("userdddOfficeList");
        doReturn(itemListCache).when(cacheManager).getCache("itemList");
        doReturn(categoryListCache).when(cacheManager).getCache("categoryList");
        doReturn(tagListCache).when(cacheManager).getCache("tagList");
        doReturn(dddCodeRefCache).when(cacheManager).getCache("dddCodeRef");
        doReturn(userdddCodeRefCache).when(cacheManager).getCache("userdddCodeRef");
    }

    // =========================================================================
    // clearAllCache()
    // =========================================================================

    @Test
    @DisplayName("clearAllCache - clears all caches returned by CacheManager")
    void clearAllCache_clearsAllCaches() {
        when(cacheManager.getCacheNames())
                .thenReturn(Arrays.asList("userList", "adaList", "statusList"));

        cacheService.clearAllCache();

        verify(userListCache).clear();
        verify(adaListCache).clear();
        verify(statusListCache).clear();
    }

    @Test
    @DisplayName("clearAllCache - does nothing when no caches are registered")
    void clearAllCache_noCaches_doesNothing() {
        when(cacheManager.getCacheNames()).thenReturn(Collections.emptyList());

        cacheService.clearAllCache();

        verify(cacheManager, never()).getCache(anyString());
    }

    @Test
    @DisplayName("clearAllCache - handles single cache correctly")
    void clearAllCache_singleCache_clearsIt() {
        when(cacheManager.getCacheNames()).thenReturn(Collections.singletonList("userList"));

        cacheService.clearAllCache();

        verify(userListCache, times(1)).clear();
    }

    @Test
    @DisplayName("clearAllCache - clears all registered caches")
    void clearAllCache_allCachesRegistered_clearsAll() {
        when(cacheManager.getCacheNames()).thenReturn(Arrays.asList(
                "userList", "sealedUserList", "adaList", "statusList",
                "dddOfficeList", "userdddOfficeList", "itemList",
                "categoryList", "tagList", "dddCodeRef", "userdddCodeRef"
        ));

        cacheService.clearAllCache();

        verify(userListCache).clear();
        verify(sealedUserListCache).clear();
        verify(adaListCache).clear();
        verify(statusListCache).clear();
        verify(dddOfficeListCache).clear();
        verify(userdddOfficeListCache).clear();
        verify(itemListCache).clear();
        verify(categoryListCache).clear();
        verify(tagListCache).clear();
        verify(dddCodeRefCache).clear();
        verify(userdddCodeRefCache).clear();
    }

    // =========================================================================
    // clearUserCache()
    // =========================================================================

    @Test
    @DisplayName("clearUserCache - clears userList and sealedUserList")
    void clearUserCache_clearsBothUserCaches() {
        cacheService.clearUserCache();

        verify(userListCache).clear();
        verify(sealedUserListCache).clear();
    }

    @Test
    @DisplayName("clearUserCache - does not touch reference data caches")
    void clearUserCache_doesNotTouchReferenceCaches() {
        cacheService.clearUserCache();

        verify(adaListCache, never()).clear();
        verify(statusListCache, never()).clear();
        verify(dddOfficeListCache, never()).clear();
        verify(userdddOfficeListCache, never()).clear();
        verify(itemListCache, never()).clear();
        verify(categoryListCache, never()).clear();
        verify(tagListCache, never()).clear();
        verify(dddCodeRefCache, never()).clear();
        verify(userdddCodeRefCache, never()).clear();
    }

    @Test
    @DisplayName("clearUserCache - each user cache cleared exactly once")
    void clearUserCache_eachCacheClearedExactlyOnce() {
        cacheService.clearUserCache();

        verify(userListCache, times(1)).clear();
        verify(sealedUserListCache, times(1)).clear();
    }

    // =========================================================================
    // clearReferenceDataCache()
    // =========================================================================

    @Test
    @DisplayName("clearReferenceDataCache - clears all 9 reference data caches")
    void clearReferenceDataCache_clearsAllReferenceCaches() {
        cacheService.clearReferenceDataCache();

        verify(adaListCache).clear();
        verify(statusListCache).clear();
        verify(dddOfficeListCache).clear();
        verify(userdddOfficeListCache).clear();
        verify(itemListCache).clear();
        verify(categoryListCache).clear();
        verify(tagListCache).clear();
        verify(dddCodeRefCache).clear();
        verify(userdddCodeRefCache).clear();
    }

    @Test
    @DisplayName("clearReferenceDataCache - does not touch user caches")
    void clearReferenceDataCache_doesNotTouchUserCaches() {
        cacheService.clearReferenceDataCache();

        verify(userListCache, never()).clear();
        verify(sealedUserListCache, never()).clear();
    }

    @Test
    @DisplayName("clearReferenceDataCache - each cache cleared exactly once")
    void clearReferenceDataCache_eachCacheClearedExactlyOnce() {
        cacheService.clearReferenceDataCache();

        verify(adaListCache, times(1)).clear();
        verify(statusListCache, times(1)).clear();
        verify(dddOfficeListCache, times(1)).clear();
        verify(userdddOfficeListCache, times(1)).clear();
        verify(itemListCache, times(1)).clear();
        verify(categoryListCache, times(1)).clear();
        verify(tagListCache, times(1)).clear();
        verify(dddCodeRefCache, times(1)).clear();
        verify(userdddCodeRefCache, times(1)).clear();
    }
}
