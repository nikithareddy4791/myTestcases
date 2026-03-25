cacheManager.afterPropertiesSet();

        cacheService = new CacheService();
        cacheManager.afterPropertiesSet();

        cacheService = new CacheService();
        // Uses package-private setter added to CacheService.java:
        // void setCacheManager(CacheManager cm) { this.cacheManager = cm; }
        // Uses package-private setter added to CacheService.java:
        // void setCacheManager(CacheManager cm) { this.cacheManager = cm; }
        cacheService.setCacheManager(cacheManager);
        cacheService.setCacheManager(cacheManager);


        userListCache.put("key1", "value1");
        sealedUserListCache.put("key1", "value1");
        adaListCache.put("key1", "value1");
        statusListCache.put("key1", "value1");
        dddOfficeListCache.put("key1", "value1");
        userdddOfficeListCache.put("key1", "value1");
        itemListCache.put("key1", "value1");
        categoryListCache.put("key1", "value1");
