grep -n "CacheManager\|cacheManager" src/main/java/org/nnnn/ddd/service/CacheService.java
```

It will show something like:
```
8:  @Autowired
9:  CacheManager cacheManager;        // field name is "cacheManager"
```

or maybe:
```
8:  @Autowired
9:  private CacheManager manager;     // field name is "manager" ← different!


================

@Test
void debug_checkInjection() {
    Object injected = ReflectionTestUtils.getField(cacheService, "cacheManager");
    System.out.println("Injected CacheManager: " + injected);
    System.out.println("Our CacheManager: " + cacheManager);
    assertThat(injected).isSameAs(cacheManager);
}


==============

6:import org.springframework.cache.CacheManager;
14:    CacheManager cacheManager;
17:        cacheManager.getCacheNames()
18:                .forEach(name -> cacheManager.getCache(name).clear());
22:        cacheManager.getCache("userList").clear();
23:        cacheManager.getCache("sealedUserList").clear();
27:        cacheManager.getCache("adaList").clear();
28:        cacheManager.getCache("statusList").clear();
29:        cacheManager.getCache("dddOfficeList").clear();
30:        cacheManager.getCache("userdddOfficeList").clear();
31:        cacheManager.getCache("itemList").clear();
32:        cacheManager.getCache("categoryList").clear();
33:        cacheManager.getCache("tagList").clear();
34:        cacheManager.getCache("dddCodeRef").clear();
35:        cacheManager.getCache("userdddCodeRef").clear();
