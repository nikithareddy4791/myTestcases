grep "getCache" src/main/java/org/nnnnn/ddd/service/CacheService.java



grep -n "getCache" src/main/java/org/nnnnn/ddd/service/CacheService.java
```

Paste the output here. It will look something like:
```
28:    cacheManager.getCache("adaList").clear();
29:    cacheManager.getCache("statusList").clear();
30:    cacheManager.getCache("dddOfficeList").clear();
...



mvn test -Dtest=CacheServiceTest -Dsurefire.failIfNoSpecifiedTests=false
```

Look at the output — it will show something like:
```
Tests run: 15, Failures: 0, Errors: 11, Skipped: 0



cacheManager.getCacheNames()
                .forEach(name -> cacheManager.getCache(name).clear());
        cacheManager.getCache("userList").clear();
        cacheManager.getCache("sealedUserList").clear();
        cacheManager.getCache("adaList").clear();
        cacheManager.getCache("statusList").clear();
        cacheManager.getCache("dddOfficeList").clear();
        cacheManager.getCache("userdddOfficeList").clear();
        cacheManager.getCache("itemList").clear();
        cacheManager.getCache("categoryList").clear();
        cacheManager.getCache("tagList").clear();
        cacheManager.getCache("dddCodeRef").clear();
        cacheManager.getCache("userdddCodeRef").clear();



        [INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.584 s
[INFO] Finished at: 2026-03-24T18:21:29-04:00
[INFO] ------------------------------------------------------------------------
[ERROR] Unknown lifecycle phase ".failIfNoSpecifiedTests=false". You must specify a valid lifecycle phase or a goal in the format <plugin-prefix>:<goal> or <plugin-group-id>:<plugin-artifact-id>[:<plugin-version>]:<goal>. Available lifecycle phases are: pre-clean, clean, post-clean, validate, initialize, generate-sources, process-sources, generate-resources, process-resources, compile, process-classes, generate-test-sources, process-test-sources, generate-test-resources, process-test-resources, test-compile, process-test-classes, test, prepare-package, package, pre-integration-test, integration-test, post-integration-test, verify, install, deploy, pre-site, site, post-site, site-deploy. -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/LifecyclePhaseNotFoundException
PS F:\project\ddd-services> mvn test -Dtest=CacheServiceTest -Dsurefire.failIfNoSpecifiedTests=false
[INFO] Scanning for projects...
[INFO] 
[INFO] -----------------------< org.nnnn:ddd-services >------------------------
[INFO] Building ddd-services 1.0.0
[INFO]   from pom.xml
[INFO] --------------------------------[ war ]---------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.692 s
[INFO] Finished at: 2026-03-24T18:21:46-04:00
[INFO] ------------------------------------------------------------------------
[ERROR] Unknown lifecycle phase ".failIfNoSpecifiedTests=false". You must specify a valid lifecycle phase or a goal in the format <plugin-prefix>:<goal> or <plugin-group-id>:<plugin-artifact-id>[:<plugin-version>]:<goal>. Available lifecycle phases are: pre-clean, clean, post-clean, validate, initialize, generate-sources, process-sources, generate-resources, process-resources, compile, process-classes, generate-test-sources, process-test-sources, generate-test-resources, process-test-resources, test-compile, process-test-classes, test, prepare-package, package, pre-integration-test, integration-test, post-integration-test, verify, install, deploy, pre-site, site, post-site, site-deploy. -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/LifecyclePhaseNotFoundException
