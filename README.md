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
