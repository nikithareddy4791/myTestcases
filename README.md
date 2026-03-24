
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
