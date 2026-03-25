
[ERROR] Failures: 
[ERROR]   CaseServiceTest.saveCase_versionMismatch_throwsOptimisticLockException:317 
Expecting actual throwable to be an instance of:
  jakarta.persistence.OptimisticLockException
but was:
  org.nnnn.ddd.exceptions.CaseNotFoundException: Case not found with id: 100
        at org.nnnn.ddd.service.CaseService.saveCase(CaseService.java:242)
        at org.nnnn.ddd.service.CaseServiceTest.lambda$saveCase_versionMismatch_throwsOptimisticLockException$0(CaseServiceLoadCaseTest.java:316)
        at org.assertj.core.api.ThrowableAssert.catchThrowable(ThrowableAssert.java:63)
        ...(78 remaining lines not displayed - this can be changed with Assertions.setMaxStackTraceElementsDisplayed)
[ERROR]   CaseServiceTest.saveNote_s3Failure_throwsFileUploadException:502 
Expecting actual throwable to be an instance of:
  org.nnnn.ddd.exceptions.FileUploadException
but was:
  org.nnnn.ddd.exceptions.CaseNotFoundException: Case 100 not found
        at org.nnnn.ddd.service.CaseService.saveNote(CaseService.java:500)
        at org.nnnn.ddd.service.CaseServiceTest.lambda$saveNote_s3Failure_throwsFileUploadException$0(CaseServiceLoadCaseTest.java:501)       
        at org.assertj.core.api.ThrowableAssert.catchThrowable(ThrowableAssert.java:63)
        ...(78 remaining lines not displayed - this can be changed with Assertions.setMaxStackTraceElementsDisplayed)
[ERROR] Errors: 
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsAdaList:153 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches:216 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsCategoryList:188 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsItemList:181 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsStatusList:160 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" 
because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsTagList:195 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsUserdddCodeRef:209 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsUserdddOfficeList:174 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsdddCodeRef:202 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" 
because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsdddOfficeList:167 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_doesNotClearUserCaches:231 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CaseServiceTest.saveCase_nullCategory_clearsCategory:350 » CaseNotFound Case not found with id: 100
[ERROR]   CaseServiceTest.saveCase_validCase_returnsUpdatedDto:291 » CaseNotFound Case not found with id: 100
[ERROR]   CaseServiceTest.saveCase_withCategory_updatesCategory:334 » CaseNotFound Case not found with id: 100
[ERROR]   CaseServiceTest.saveCase_withStatus_updatesStatus:367 » CaseNotFound Case not found with id: 100
[ERROR]   CaseServiceTest.saveNote_textOnly_createsNote:466 » CaseNotFound Case 100 not found
[ERROR]   CaseServiceTest.saveNote_withFile_uploadsToS3:485 » CaseNotFound Case 100 not found
[INFO]
[ERROR] Tests run: 187, Failures: 2, Errors: 17, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  40.494 s
[INFO] Finished at: 2026-03-25T16:59:19-04:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.5.3:test (default-test) on project ddd-services: There are test failures.
[ERROR]
[ERROR] See F:\project\ddd-services\target\surefire-reports for the individual test results.
[ERROR] See dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR]
