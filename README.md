grep "getCache" src/main/java/org/nnnn/ddd/service/CacheService.java



grep -n "getCache" src/main/java/org/nnnn/ddd/service/CacheService.java
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



