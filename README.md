public class org.nnnn.ddd.service.CacheService {
  private static final org.slf4j.Logger logger;
  org.springframework.cache.CacheManager cacheManager;
  public org.nnnn.ddd.service.CacheService();
  public void clearAllCache();
  public void clearUserCache();
  void setCacheManager(org.springframework.cache.CacheManager);
  public void clearReferenceDataCache();
  private void lambda$clearAllCache$0(java.lang.String);
  static {};
}
