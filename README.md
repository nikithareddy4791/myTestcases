 ... 34 more
[INFO] Running org.nnnn.ddd.service.CacheServiceTest
[ERROR] Tests run: 17, Failures: 0, Errors: 11, Skipped: 0, Time elapsed: 0.500 s <<< FAILURE! -- in org.nnnn.ddd.service.CacheServiceTest
[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsdddOfficeList -- Time elapsed: 0.155 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsdddOfficeList(CacheServiceTest.java:152)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsdddCodeRef -- Time elapsed: 0.003 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsdddCodeRef(CacheServiceTest.java:187)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsUserdddOfficeList -- Time elapsed: 0.004 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsUserdddOfficeList(CacheServiceTest.java:159)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches -- Time elapsed: 0.004 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches(CacheServiceTest.java:201)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsUserdddCodeRef -- Time elapsed: 0.003 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsUserdddCodeRef(CacheServiceTest.java:194)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_doesNotClearUserCaches -- Time elapsed: 0.003 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_doesNotClearUserCaches(CacheServiceTest.java:216)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsStatusList -- Time elapsed: 0.004 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsStatusList(CacheServiceTest.java:145)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsItemList -- Time elapsed: 0.004 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsItemList(CacheServiceTest.java:166)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsCategoryList -- Time elapsed: 0.005 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsCategoryList(CacheServiceTest.java:173)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsTagList -- Time elapsed: 0.003 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsTagList(CacheServiceTest.java:180)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsAdaList -- Time elapsed: 0.003 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsAdaList(CacheServiceTest.java:138)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[INFO] 
[INFO] Results:
[INFO]
[ERROR] Errors: 
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsAdaList:138 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches:201 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsCategoryList:173 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsItemList:166 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsStatusList:145 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" 
because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsTagList:180 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsUserdddCodeRef:194 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsUserdddOfficeList:159 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsdddCodeRef:187 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" 
because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsdddOfficeList:152 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_doesNotClearUserCaches:216 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[INFO]
[ERROR] Tests run: 17, Failures: 0, Errors: 11, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  14.546 s
[INFO] Finished at: 2026-03-25T10:41:35-04:00
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
