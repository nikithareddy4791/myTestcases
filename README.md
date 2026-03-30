package org.nnnn.ddd.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

    @Autowired
    CacheManager cacheManager;

    public void clearAllCache() {
        cacheManager.getCacheNames()
                .forEach(name -> cacheManager.getCache(name).clear());
    }

    public void clearUserCache() {
        cacheManager.getCache("userList").clear();
        cacheManager.getCache("sealedUserList").clear();
    }

    void setCacheManager(CacheManager cacheManager) 
    { this.cacheManager = cacheManager; 
        
    }

    public void clearReferenceDataCache() {
        cacheManager.getCache("adaList").clear();
        cacheManager.getCache("statusList").clear();
        cacheManager.getCache("dddOfficeList").clear();
        cacheManager.getCache("userdddOfficeList").clear();
        cacheManager.getCache("itemList").clear();
        cacheManager.getCache("categoryList").clear();
        cacheManager.getCache("tagList").clear();
        cacheManager.getCache("dddCodeRef").clear();
        cacheManager.getCache("userdddCodeRef").clear();
    }
}
