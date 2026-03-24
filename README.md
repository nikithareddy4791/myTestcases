package org.nnnn.ddd.service;

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
    @InjectMocks private CacheService cacheService;

    // Helper — stubs all reference data cache names and returns the mocks as an array
    // index: 0=ada, 1=status, 2=dddOffice, 3=userdddOffice, 4=item, 5=category, 6=tag, 7=dddCodeRef, 8=userdddCodeRef
    private Cache[] stubAllReferenceCaches() {
        Cache ada           = mock(Cache.class);
        Cache status        = mock(Cache.class);
        Cache dddOffice     = mock(Cache.class);
        Cache userdddOffice = mock(Cache.class);
        Cache item          = mock(Cache.class);
        Cache category      = mock(Cache.class);
        Cache tag           = mock(Cache.class);
        Cache dddCodeRef    = mock(Cache.class);
        Cache userdddCodeRef = mock(Cache.class);

        doReturn(ada).when(cacheManager).getCache("adaList");
        doReturn(status).when(cacheManager).getCache("statusList");
        doReturn(dddOffice).when(cacheManager).getCache("dddOfficeList");
        doReturn(userdddOffice).when(cacheManager).getCache("userdddOfficeList");
        doReturn(item).when(cacheManager).getCache("itemList");
        doReturn(category).when(cacheManager).getCache("categoryList");
        doReturn(tag).when(cacheManager).getCache("tagList");
        doReturn(dddCodeRef).when(cacheManager).getCache("dddCodeRef");
        doReturn(userdddCodeRef).when(cacheManager).getCache("userdddCodeRef");

        return new Cache[]{ada, status, dddOffice, userdddOffice, item, category, tag, dddCodeRef, userdddCodeRef};
    }

    // Helper — stubs user cache names
    private Cache[] stubAllUserCaches() {
        Cache userList       = mock(Cache.class);
        Cache sealedUserList = mock(Cache.class);

        doReturn(userList).when(cacheManager).getCache("userList");
        doReturn(sealedUserList).when(cacheManager).getCache("sealedUserList");

        return new Cache[]{userList, sealedUserList};
    }

    // =========================================================================
    // clearAllCache()
    // =========================================================================

    @Test
    @DisplayName("clearAllCache - clears each cache returned by getCacheNames")
    void clearAllCache_clearsAllCaches() {
        Cache userList = mock(Cache.class);
        Cache adaList  = mock(Cache.class);
        Cache status   = mock(Cache.class);

        doReturn(userList).when(cacheManager).getCache("userList");
        doReturn(adaList).when(cacheManager).getCache("adaList");
        doReturn(status).when(cacheManager).getCache("statusList");

        when(cacheManager.getCacheNames())
                .thenReturn(Arrays.asList("userList", "adaList", "statusList"));

        cacheService.clearAllCache();

        verify(userList).clear();
        verify(adaList).clear();
        verify(status).clear();
    }

    @Test
    @DisplayName("clearAllCache - does nothing when no caches registered")
    void clearAllCache_noCaches_doesNothing() {
        when(cacheManager.getCacheNames()).thenReturn(Collections.emptyList());

        cacheService.clearAllCache();

        verify(cacheManager, never()).getCache(anyString());
    }

    @Test
    @DisplayName("clearAllCache - handles single cache correctly")
    void clearAllCache_singleCache_clearsIt() {
        Cache userList = mock(Cache.class);
        doReturn(userList).when(cacheManager).getCache("userList");
        when(cacheManager.getCacheNames()).thenReturn(Collections.singletonList("userList"));

        cacheService.clearAllCache();

        verify(userList, times(1)).clear();
    }

    @Test
    @DisplayName("clearAllCache - clears all 11 caches when all are registered")
    void clearAllCache_allCachesRegistered_clearsAll() {
        Cache[] refs  = stubAllReferenceCaches();
        Cache[] users = stubAllUserCaches();

        when(cacheManager.getCacheNames()).thenReturn(Arrays.asList(
                "userList", "sealedUserList", "adaList", "statusList",
                "dddOfficeList", "userdddOfficeList", "itemList",
                "categoryList", "tagList", "dddCodeRef", "userdddCodeRef"
        ));

        cacheService.clearAllCache();

        verify(users[0]).clear();  // userList
        verify(users[1]).clear();  // sealedUserList
        for (Cache c : refs) {
            verify(c).clear();
        }
    }

    // =========================================================================
    // clearUserCache()
    // =========================================================================

    @Test
    @DisplayName("clearUserCache - clears userList and sealedUserList")
    void clearUserCache_clearsBothUserCaches() {
        Cache[] users = stubAllUserCaches();

        cacheService.clearUserCache();

        verify(users[0]).clear();  // userList
        verify(users[1]).clear();  // sealedUserList
    }

    @Test
    @DisplayName("clearUserCache - each user cache cleared exactly once")
    void clearUserCache_eachCacheClearedExactlyOnce() {
        Cache[] users = stubAllUserCaches();

        cacheService.clearUserCache();

        verify(users[0], times(1)).clear();
        verify(users[1], times(1)).clear();
    }

    @Test
    @DisplayName("clearUserCache - does not touch reference data caches")
    void clearUserCache_doesNotTouchReferenceCaches() {
        stubAllUserCaches();

        cacheService.clearUserCache();

        verify(cacheManager, never()).getCache("adaList");
        verify(cacheManager, never()).getCache("statusList");
        verify(cacheManager, never()).getCache("dddOfficeList");
        verify(cacheManager, never()).getCache("userdddOfficeList");
        verify(cacheManager, never()).getCache("itemList");
        verify(cacheManager, never()).getCache("categoryList");
        verify(cacheManager, never()).getCache("tagList");
        verify(cacheManager, never()).getCache("dddCodeRef");
        verify(cacheManager, never()).getCache("userdddCodeRef");
    }

    // =========================================================================
    // clearReferenceDataCache()
    // =========================================================================

    @Test
    @DisplayName("clearReferenceDataCache - clears all 9 reference data caches")
    void clearReferenceDataCache_clearsAllReferenceCaches() {
        Cache[] refs = stubAllReferenceCaches();

        cacheService.clearReferenceDataCache();

        for (Cache c : refs) {
            verify(c).clear();
        }
    }

    @Test
    @DisplayName("clearReferenceDataCache - does not touch user caches")
    void clearReferenceDataCache_doesNotTouchUserCaches() {
        stubAllReferenceCaches();

        cacheService.clearReferenceDataCache();

        verify(cacheManager, never()).getCache("userList");
        verify(cacheManager, never()).getCache("sealedUserList");
    }

    @Test
    @DisplayName("clearReferenceDataCache - each cache cleared exactly once")
    void clearReferenceDataCache_eachCacheClearedExactlyOnce() {
        Cache[] refs = stubAllReferenceCaches();

        cacheService.clearReferenceDataCache();

        for (Cache c : refs) {
            verify(c, times(1)).clear();
        }
    }
}
