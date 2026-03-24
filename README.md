Results:
[INFO]
[ERROR] Errors: 
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsAdaList:155 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return 
value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches:218 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because 
the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsCategoryList:190 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsItemList:183 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsStatusList:162 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsTagList:197 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return 
value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsUserdddCodeRef:211 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the 
return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsUserdddOfficeList:176 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsdddCodeRef:204 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsdddOfficeList:169 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_doesNotClearUserCaches:234 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[INFO]
[ERROR] Tests run: 175, Failures: 0, Errors: 11, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  30.150 s
[INFO] Finished at: 2026-03-24T18:31:28-04:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.5.3:test (default-test) on project ddd-services:
[ERROR]
[ERROR] See F:\project\ddd-services\target\surefire-reports for the individual test results.
[ERROR] See dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
