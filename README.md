[ERROR] Failures: 
[ERROR]   CacheApiControllerTest.clearCache_responseBodyIsValidJson:129 Content type [application/json] is not compatible with [text/plain]
[ERROR]   CacheApiControllerTest.clearCache_success_returns200:64 Response content expected:<"Success: The operation completed successfully."> but was:<Success: The operation completed successfully.>
[ERROR] Errors: 
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches:148 » PotentialStubbingProblem 
Strict stubbing argument mismatch. Please check:
 - this invocation of 'getCache' method:
    cacheManager.getCache("userdddOfficeList");
    -> at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:30)
 - has following stubbing(s) with different arguments:
    1. cacheManager.getCache("userdddOfficeList");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches(CacheServiceTest.java:141)
    2. cacheManager.getCache("itemList");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches(CacheServiceTest.java:142)
    3. cacheManager.getCache("categoryList");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches(CacheServiceTest.java:143)
    4. cacheManager.getCache("tagList");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches(CacheServiceTest.java:144)
    5. cacheManager.getCache("dddCodeRef");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches(CacheServiceTest.java:145)
    6. cacheManager.getCache("userdddCodeRef");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches(CacheServiceTest.java:146)
Typically, stubbing argument mismatch indicates user mistake when writing tests.
Mockito fails early so that you can debug potential problem easily.
However, there are legit scenarios when this exception generates false negative signal:
  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
    Please use 'will().given()' or 'doReturn().when()' API for stubbing.
  - stubbed method is intentionally invoked with different arguments by code under test
    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
For more information see javadoc for PotentialStubbingProblem class.
[ERROR]   CacheServiceTest.clearReferenceDataCache_doesNotTouchUserCaches:174 » PotentialStubbingProblem 
Strict stubbing argument mismatch. Please check:
 - this invocation of 'getCache' method:
    cacheManager.getCache("userdddOfficeList");
    -> at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:30)
 - has following stubbing(s) with different arguments:
    1. cacheManager.getCache("userdddOfficeList");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_doesNotTouchUserCaches(CacheServiceTest.java:167)
    2. cacheManager.getCache("itemList");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_doesNotTouchUserCaches(CacheServiceTest.java:168)
    3. cacheManager.getCache("categoryList");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_doesNotTouchUserCaches(CacheServiceTest.java:169)
    4. cacheManager.getCache("tagList");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_doesNotTouchUserCaches(CacheServiceTest.java:170)
    5. cacheManager.getCache("dddCodeRef");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_doesNotTouchUserCaches(CacheServiceTest.java:171)
    6. cacheManager.getCache("userdddCodeRef");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_doesNotTouchUserCaches(CacheServiceTest.java:172)
Typically, stubbing argument mismatch indicates user mistake when writing tests.
Mockito fails early so that you can debug potential problem easily.
However, there are legit scenarios when this exception generates false negative signal:
  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
    Please use 'will().given()' or 'doReturn().when()' API for stubbing.
  - stubbed method is intentionally invoked with different arguments by code under test
    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
For more information see javadoc for PotentialStubbingProblem class.
[ERROR]   CacheServiceTest.clearReferenceDataCache_eachCacheClearedExactlyOnce:195 » PotentialStubbingProblem 
Strict stubbing argument mismatch. Please check:
 - this invocation of 'getCache' method:
    cacheManager.getCache("userdddOfficeList");
    -> at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:30)
 - has following stubbing(s) with different arguments:
    1. cacheManager.getCache("userdddOfficeList");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_eachCacheClearedExactlyOnce(CacheServiceTest.java:188)
    2. cacheManager.getCache("itemList");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_eachCacheClearedExactlyOnce(CacheServiceTest.java:189)
    3. cacheManager.getCache("categoryList");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_eachCacheClearedExactlyOnce(CacheServiceTest.java:190)
    4. cacheManager.getCache("tagList");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_eachCacheClearedExactlyOnce(CacheServiceTest.java:191)
    5. cacheManager.getCache("dddCodeRef");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_eachCacheClearedExactlyOnce(CacheServiceTest.java:192)
    6. cacheManager.getCache("userdddCodeRef");
      -> at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_eachCacheClearedExactlyOnce(CacheServiceTest.java:193)
Typically, stubbing argument mismatch indicates user mistake when writing tests.
Mockito fails early so that you can debug potential problem easily.
However, there are legit scenarios when this exception generates false negative signal:
  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
    Please use 'will().given()' or 'doReturn().when()' API for stubbing.
  - stubbed method is intentionally invoked with different arguments by code under test
    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
For more information see javadoc for PotentialStubbingProblem class.
[INFO]
[ERROR] Tests run: 166, Failures: 2, Errors: 3, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ----------------------------------------------------------------
