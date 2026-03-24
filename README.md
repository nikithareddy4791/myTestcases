[ERROR] Errors: 
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches:180 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because 
the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_doesNotTouchUserCaches:196 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_eachCacheClearedExactlyOnce:205 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[INFO]
[ERROR] Tests run: 168, Failures: 0, Errors: 3, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
