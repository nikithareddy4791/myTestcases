Results:
[INFO]
[ERROR] Failures: 
[ERROR]   CacheConfigTest.cacheManager_containsAllExpectedCacheNames:181 
Expecting UnmodifiableSet:
  ["adaList",
    "tagList",
    "sealedUserList",
    "userList",
    "statusList",
    "userdddOfficeList",
    "categoryList",
    "dddOfficeList",
    "itemList",
    "dddCodeRef"]
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
  ["userdddOfficeList"]

[ERROR]   CacheConfigTest.cacheManager_containsUserdddOfficeListCache:92 
Expecting actual not to be null
[INFO]
[ERROR] Tests run: 401, Failures: 2, Errors: 0, Skipped: 11
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] -------------
