package org.nnnn.ddd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.cache.CacheManager;

@Configuration
public class CacheConfig {
    private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);

    @Bean
    public CacheManager cacheManager() {

        Map<String, CaffeineCache> caches = new HashMap<>();

        caches.put("adaList", new CaffeineCache("adaList",
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.DAYS)
                        .recordStats()
                        .build()));

        caches.put("statusList", new CaffeineCache("statusList",
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.DAYS)
                        .recordStats()
                        .build()));

        caches.put("dddOfficeList", new CaffeineCache("dddOfficeList",
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.DAYS)
                        .recordStats()
                        .build()));

        caches.put("itemList", new CaffeineCache("itemList",
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.DAYS)
                        .recordStats()
                        .build()));

        caches.put("dddCodeRef", new CaffeineCache("dddCodeRef",
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.DAYS)
                        .recordStats()
                        .build()));

        caches.put("categoryList", new CaffeineCache("categoryList",
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.DAYS)
                        .recordStats()
                        .build()));

        caches.put("tagList", new CaffeineCache("tagList",
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.DAYS)
                        .recordStats()
                        .build()));

        caches.put("userdddOfficeList", new CaffeineCache("userdddOfficeList",
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.DAYS)
                        .recordStats()
                        .build()));

        caches.put("userList", new CaffeineCache("userList",
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.HOURS)
                        .recordStats()
                        .build()));

        caches.put("sealedUserList", new CaffeineCache("sealedUserList",
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.HOURS)
                        .recordStats()
                        .build()));

        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(new ArrayList<>(caches.values()));
        return manager;
    }
}
