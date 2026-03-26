
[ERROR] Failures: 
[ERROR]   CacheConfigTest.cacheManager_containsAdaListCache:48 
Expecting actual not to be null
[ERROR]   CacheConfigTest.cacheManager_containsAllExpectedCacheNames:179 
Expecting EmptySet:
  []
to contain:
  ["adaList",
    "statusList",
    "dddOfficeList",
    "itemList",
    "dddCodeRef",
    "categoryList",
    "tagList",
    "userdddOfficeList",
    "userList",
    "sealedUserList"]
but could not find the following element(s):
  ["adaList",
    "statusList",
    "dddOfficeList",
    "itemList",
    "dddCodeRef",
    "categoryList",
    "tagList",
    "userdddOfficeList",
    "userList",
    "sealedUserList"]

[ERROR]   CacheConfigTest.cacheManager_containsCategoryListCache:78 
Expecting actual not to be null
[ERROR]   CacheConfigTest.cacheManager_containsExactly10Caches:38 
Expected size: 10 but was: 0 in:
[]
[ERROR]   CacheConfigTest.cacheManager_containsItemListCache:66 
Expecting actual not to be null
[ERROR]   CacheConfigTest.cacheManager_containsSealedUserListCache:106 
Expecting actual not to be null
[ERROR]   CacheConfigTest.cacheManager_containsStatusListCache:54 
Expecting actual not to be null
[ERROR]   CacheConfigTest.cacheManager_containsTagListCache:84 
Expecting actual not to be null
[ERROR]   CacheConfigTest.cacheManager_containsUserListCache:100 
Expecting actual not to be null
[ERROR]   CacheConfigTest.cacheManager_containsUserdddOfficeListCache:90 
Expecting actual not to be null
[ERROR]   CacheConfigTest.cacheManager_containsdddCodeRefCache:72 
Expecting actual not to be null
[ERROR]   CacheConfigTest.cacheManager_containsdddOfficeListCache:60 
Expecting actual not to be null
[ERROR] Errors: 
[ERROR]   CacheConfigTest.adaListCache_canStoreAndRetrieveValues:117 NullPointer Cannot invoke "org.springframework.cache.Cache.put(Object, Object)" because "cache" is null
[ERROR]   CacheConfigTest.cache_evictsValueAfterClear:156 NullPointer Cannot invoke "org.springframework.cache.Cache.put(Object, Object)" because "cache" is null
[ERROR]   CacheConfigTest.cache_returnsNullForMissingKey:149 NullPointer Cannot invoke "org.springframework.cache.Cache.get(Object, java.lang.Class)" because "cache" is null
[ERROR]   CacheConfigTest.sealedUserListCache_canStoreAndRetrieveValues:141 NullPointer Cannot invoke "org.springframework.cache.Cache.put(Object, Object)" because "cache" is null
[ERROR]   CacheConfigTest.statusListCache_canStoreAndRetrieveValues:125 NullPointer Cannot invoke "org.springframework.cache.Cache.put(Object, Object)" because "cache" is null
[ERROR]   CacheConfigTest.userListCache_canStoreAndRetrieveValues:133 NullPointer Cannot invoke "org.springframework.cache.Cache.put(Object, Object)" because "cache" is null
[INFO]
[ERROR] Tests run: 401, Failures: 12, Errors: 6, Skipped: 11
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] --------------
