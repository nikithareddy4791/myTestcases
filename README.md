java.lang.instrument.IllegalClassFormatException: Error while instrumenting sun/util/resources/cldr/provider/CLDRLocaleDataMetaInfo with JaCoCo 0.8.12.202403310830/dbfb6f2.
        at org.jacoco.agent.rt.internal_aeaf9ab.CoverageTransformer.transform(CoverageTransformer.java:94)
        at java.instrument/java.lang.instrument.ClassFileTransformer.transform(ClassFileTransformer.java:257)
        at java.instrument/sun.instrument.TransformerManager.transform(TransformerManager.java:188)
        at java.instrument/sun.instrument.InstrumentationImpl.transform(InstrumentationImpl.java:594)
        at java.base/java.lang.ClassLoader.defineClass2(Native Method)
        at java.base/java.lang.ClassLoader.defineClass(ClassLoader.java:1052)
        at java.base/java.security.SecureClassLoader.defineClass(SecureClassLoader.java:176)
        at java.base/jdk.internal.loader.BuiltinClassLoader.defineClass(BuiltinClassLoader.java:735)
        at java.base/jdk.internal.loader.BuiltinClassLoader.findClassInModuleOrNull(BuiltinClassLoader.java:678)
        at java.base/jdk.internal.loader.BuiltinClassLoader.findClass(BuiltinClassLoader.java:560)
        at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:602)
        at java.base/java.lang.Class.forName(Class.java:599)
        at java.base/java.util.ServiceLoader.loadProvider(ServiceLoader.java:755)
        at java.base/java.util.ServiceLoader$ModuleServicesLookupIterator.hasNext(ServiceLoader.java:955)
        at java.base/java.util.ServiceLoader$1.hasNext(ServiceLoader.java:1164)
        at java.base/java.util.ServiceLoader$2.hasNext(ServiceLoader.java:1246)
        at java.base/sun.util.cldr.CLDRLocaleProviderAdapter.<init>(CLDRLocaleProviderAdapter.java:75)
        at java.base/jdk.internal.reflect.DirectConstructorHandleAccessor.newInstance(DirectConstructorHandleAccessor.java:62)
        at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:499)
        at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:483)
        at java.base/sun.util.locale.provider.LocaleProviderAdapter.forType(LocaleProviderAdapter.java:181)
        at java.base/sun.util.locale.provider.LocaleProviderAdapter.findAdapter(LocaleProviderAdapter.java:280)
        at java.base/sun.util.locale.provider.LocaleProviderAdapter.getAdapter(LocaleProviderAdapter.java:251)
        at java.base/java.util.Calendar.createCalendar(Calendar.java:1692)
        at java.base/java.util.Calendar.getInstance(Calendar.java:1659)
        at java.base/java.text.SimpleDateFormat.initializeCalendar(SimpleDateFormat.java:683)
        at java.base/java.text.SimpleDateFormat.<init>(SimpleDateFormat.java:627)
        at java.base/java.text.SimpleDateFormat.<init>(SimpleDateFormat.java:606)
        at org.apache.maven.surefire.booter.PpidChecker.createWindowsCreationDateFormat(PpidChecker.java:315)
        at org.apache.maven.surefire.booter.PpidChecker.<clinit>(PpidChecker.java:70)
        at org.apache.maven.surefire.booter.ForkedBooter.listenToShutdownCommands(ForkedBooter.java:218)
        at org.apache.maven.surefire.booter.ForkedBooter.setupBooter(ForkedBooter.java:139)
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:506)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:495)
Caused by: java.io.IOException: Error while instrumenting sun/util/resources/cldr/provider/CLDRLocaleDataMetaInfo with JaCoCo 0.8.12.202403310830/dbfb6f2.
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrumentError(Instrumenter.java:161)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrument(Instrumenter.java:111)
        at org.jacoco.agent.rt.internal_aeaf9ab.CoverageTransformer.transform(CoverageTransformer.java:92)
        ... 33 more
Caused by: java.lang.IllegalArgumentException: Unsupported class file major version 69
        at org.jacoco.agent.rt.internal_aeaf9ab.asm.ClassReader.<init>(ClassReader.java:200)
        at org.jacoco.agent.rt.internal_aeaf9ab.asm.ClassReader.<init>(ClassReader.java:180)
        at org.jacoco.agent.rt.internal_aeaf9ab.asm.ClassReader.<init>(ClassReader.java:166)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.internal.instr.InstrSupport.classReaderFor(InstrSupport.java:280)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrument(Instrumenter.java:77)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrument(Instrumenter.java:109)
        ... 34 more
[INFO] Running org.nnnn.ddd.service.CacheServiceTest
[ERROR] Tests run: 17, Failures: 0, Errors: 11, Skipped: 0, Time elapsed: 0.374 s <<< FAILURE! -- in org.nnnn.ddd.service.CacheServiceTest
[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsdddOfficeList -- Time elapsed: 0.131 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsdddOfficeList(CacheServiceTest.java:174)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsdddCodeRef -- Time elapsed: 0.003 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsdddCodeRef(CacheServiceTest.java:209)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsUserdddOfficeList -- Time elapsed: 0.002 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsUserdddOfficeList(CacheServiceTest.java:181)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches -- Time elapsed: 0.003 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches(CacheServiceTest.java:223)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsUserdddCodeRef -- Time elapsed: 0.002 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsUserdddCodeRef(CacheServiceTest.java:216)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_doesNotClearUserCaches -- Time elapsed: 0.002 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_doesNotClearUserCaches(CacheServiceTest.java:238)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsStatusList -- Time elapsed: 0.002 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsStatusList(CacheServiceTest.java:167)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsItemList -- Time elapsed: 0.002 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsItemList(CacheServiceTest.java:188)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsCategoryList -- Time elapsed: 0.002 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsCategoryList(CacheServiceTest.java:195)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsTagList -- Time elapsed: 0.002 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsTagList(CacheServiceTest.java:202)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsAdaList -- Time elapsed: 0.002 s <<< ERROR!
java.lang.NullPointerException: Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
        at org.nnnn.ddd.service.CacheService.clearReferenceDataCache(CacheService.java:35)
        at org.nnnn.ddd.service.CacheServiceTest.clearReferenceDataCache_clearsAdaList(CacheServiceTest.java:160)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[INFO] 
[INFO] Results:
[INFO]
[ERROR] Errors: 
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsAdaList:160 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsAllReferenceCaches:223 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsCategoryList:195 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsItemList:188 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsStatusList:167 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" 
because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsTagList:202 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsUserdddCodeRef:216 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsUserdddOfficeList:181 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsdddCodeRef:209 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" 
because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_clearsdddOfficeList:174 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[ERROR]   CacheServiceTest.clearReferenceDataCache_doesNotClearUserCaches:238 » NullPointer Cannot invoke "org.springframework.cache.Cache.clear()" because the return value of "org.springframework.cache.CacheManager.getCache(String)" is null
[INFO]
[ERROR] Tests run: 17, Failures: 0, Errors: 11, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  12.132 s
[INFO] Finished at: 2026-03-25T11:35:19-04:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.5.3:test (default-test) on project ddd-services:
[ERROR]
[ERROR] See F:\project\ddd-services\target\surefire-reports for the individual test results.
[ERROR] See dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
