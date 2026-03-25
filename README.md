java// Add this method to CacheService.java
void setCacheManager(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
}



// Remove this line:
ReflectionTestUtils.setField(cacheService, "cacheManager", cacheManager);

// Replace with:
cacheService.setCacheManager(cacheManager);
