        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClassOrNull(BuiltinClassLoader.java:604)
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClassOrNull(BuiltinClassLoader.java:639)
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClassOrNull(BuiltinClassLoader.java:608)
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:578)
        at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:490)
        at org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator.<init>(DataSourceHealthIndicator.java:86)
        at org.nnnn.ddd.ServletInitializer.dbHealthIndicator(ServletInitializer.java:36)
        at org.nnnn.ddd.ServletInitializer$$SpringCGLIB$$0.CGLIB$dbHealthIndicator$1(<generated>)
        at org.nnnn.ddd.ServletInitializer$$SpringCGLIB$$FastClass$$1.invoke(<generated>)
        at org.springframework.cglib.proxy.MethodProxy.invokeSuper(MethodProxy.java:258)
        at org.springframework.context.annotation.ConfigurationClassEnhancer$BeanMethodInterceptor.intercept(ConfigurationClassEnhancer.java:393)       
        at org.nnnn.ddd.ServletInitializer$$SpringCGLIB$$0.dbHealthIndicator(<generated>)
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.lambda$instantiate$0(SimpleInstantiationStrategy.java:171)
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiateWithFactoryMethod(SimpleInstantiationStrategy.java:88)      
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:168)
        at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:653)
        at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:645)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1375)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1205)        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:569)       
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529)
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.instantiateSingleton(DefaultListableBeanFactory.java:1222)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingleton(DefaultListableBeanFactory.java:1188)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:1123)
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:987)
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:627)
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:753)
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:439)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:318)
        at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
        at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1462)
        at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
        at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
        at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)   
        at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        at org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener.postProcessFields(MockitoTestExecutionListener.java:128)
        at org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener.injectFields(MockitoTestExecutionListener.java:112)
        at org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener.prepareTestInstance(MockitoTestExecutionListener.java:69)
        at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
        at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$10(ClassBasedTestDescriptor.java:383)   
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:388)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$11(ClassBasedTestDescriptor.java:382)   
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:214)
        at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:197)
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:214)
        at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1716)
        at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:570)
        at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
        at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
        at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:382)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$6(ClassBasedTestDescriptor.java:293)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:292)        
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$4(ClassBasedTestDescriptor.java:281)
        at java.base/java.util.Optional.orElseGet(Optional.java:364)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$5(ClassBasedTestDescriptor.java:280)
        at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:112)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:111)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:69)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:128)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:128)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:160)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:146)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:144)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:143)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:100)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:160)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:146)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:144)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:143)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:100)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:201)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:170)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:94)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:59)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:142)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:58)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:103)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:85)
        at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
        at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
        at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
        at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
        at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
        at org.apache.maven.surefire.junitplatform.LazyLauncher.execute(LazyLauncher.java:56)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.execute(JUnitPlatformProvider.java:194)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invokeAllTests(JUnitPlatformProvider.java:150)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:124)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:385)
        at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162)
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:507)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:495)
Caused by: java.io.IOException: Error while instrumenting java/sql/Statement with JaCoCo 0.8.12.202403310830/dbfb6f2.
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrumentError(Instrumenter.java:161)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrument(Instrumenter.java:111)
        at org.jacoco.agent.rt.internal_aeaf9ab.CoverageTransformer.transform(CoverageTransformer.java:92)
        ... 132 more
Caused by: java.lang.IllegalArgumentException: Unsupported class file major version 69
        at org.jacoco.agent.rt.internal_aeaf9ab.asm.ClassReader.<init>(ClassReader.java:200)
        at org.jacoco.agent.rt.internal_aeaf9ab.asm.ClassReader.<init>(ClassReader.java:180)
        at org.jacoco.agent.rt.internal_aeaf9ab.asm.ClassReader.<init>(ClassReader.java:166)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.internal.instr.InstrSupport.classReaderFor(InstrSupport.java:280)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrument(Instrumenter.java:77)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrument(Instrumenter.java:109)
        ... 133 more
java.lang.instrument.IllegalClassFormatException: Error while instrumenting java/sql/BatchUpdateException with JaCoCo 0.8.12.202403310830/dbfb6f2.      
        at org.jacoco.agent.rt.internal_aeaf9ab.CoverageTransformer.transform(CoverageTransformer.java:94)
        at java.instrument/java.lang.instrument.ClassFileTransformer.transform(ClassFileTransformer.java:257)
        at java.instrument/sun.instrument.TransformerManager.transform(TransformerManager.java:188)
        at java.instrument/sun.instrument.InstrumentationImpl.transform(InstrumentationImpl.java:594)
        at java.base/java.lang.ClassLoader.defineClass2(Native Method)
        at java.base/java.lang.ClassLoader.defineClass(ClassLoader.java:1052)
        at java.base/java.security.SecureClassLoader.defineClass(SecureClassLoader.java:176)
        at java.base/jdk.internal.loader.BuiltinClassLoader.defineClass(BuiltinClassLoader.java:735)
        at java.base/jdk.internal.loader.BuiltinClassLoader.findClassInModuleOrNull(BuiltinClassLoader.java:678)
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClassOrNull(BuiltinClassLoader.java:604)
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClassOrNull(BuiltinClassLoader.java:639)
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClassOrNull(BuiltinClassLoader.java:608)
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:578)
        at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:490)
        at org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator.<init>(DataSourceHealthIndicator.java:86)
        at org.nnnn.ddd.ServletInitializer.dbHealthIndicator(ServletInitializer.java:36)
        at org.nnnn.ddd.ServletInitializer$$SpringCGLIB$$0.CGLIB$dbHealthIndicator$1(<generated>)
        at org.nnnn.ddd.ServletInitializer$$SpringCGLIB$$FastClass$$1.invoke(<generated>)
        at org.springframework.cglib.proxy.MethodProxy.invokeSuper(MethodProxy.java:258)
        at org.springframework.context.annotation.ConfigurationClassEnhancer$BeanMethodInterceptor.intercept(ConfigurationClassEnhancer.java:393)       
        at org.nnnn.ddd.ServletInitializer$$SpringCGLIB$$0.dbHealthIndicator(<generated>)
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.lambda$instantiate$0(SimpleInstantiationStrategy.java:171)
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiateWithFactoryMethod(SimpleInstantiationStrategy.java:88)      
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:168)
        at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:653)
        at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:645)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1375)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1205)        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:569)       
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529)
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.instantiateSingleton(DefaultListableBeanFactory.java:1222)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingleton(DefaultListableBeanFactory.java:1188)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:1123)
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:987)
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:627)
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:753)
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:439)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:318)
        at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
        at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1462)
        at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
        at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
        at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)   
        at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        at org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener.postProcessFields(MockitoTestExecutionListener.java:128)
        at org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener.injectFields(MockitoTestExecutionListener.java:112)
        at org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener.prepareTestInstance(MockitoTestExecutionListener.java:69)
        at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
        at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$10(ClassBasedTestDescriptor.java:383)   
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:388)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$11(ClassBasedTestDescriptor.java:382)   
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:214)
        at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:197)
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:214)
        at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1716)
        at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:570)
        at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
        at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
        at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:382)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$6(ClassBasedTestDescriptor.java:293)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:292)        
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$4(ClassBasedTestDescriptor.java:281)
        at java.base/java.util.Optional.orElseGet(Optional.java:364)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$5(ClassBasedTestDescriptor.java:280)
        at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:112)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:111)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:69)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:128)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:128)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:160)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:146)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:144)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:143)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:100)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:160)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:146)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:144)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:143)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:100)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:201)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:170)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:94)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:59)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:142)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:58)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:103)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:85)
        at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
        at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
        at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
        at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
        at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
        at org.apache.maven.surefire.junitplatform.LazyLauncher.execute(LazyLauncher.java:56)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.execute(JUnitPlatformProvider.java:194)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invokeAllTests(JUnitPlatformProvider.java:150)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:124)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:385)
        at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162)
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:507)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:495)
Caused by: java.io.IOException: Error while instrumenting java/sql/BatchUpdateException with JaCoCo 0.8.12.202403310830/dbfb6f2.
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrumentError(Instrumenter.java:161)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrument(Instrumenter.java:111)
        at org.jacoco.agent.rt.internal_aeaf9ab.CoverageTransformer.transform(CoverageTransformer.java:92)
        ... 132 more
Caused by: java.lang.IllegalArgumentException: Unsupported class file major version 69
        at org.jacoco.agent.rt.internal_aeaf9ab.asm.ClassReader.<init>(ClassReader.java:200)
        at org.jacoco.agent.rt.internal_aeaf9ab.asm.ClassReader.<init>(ClassReader.java:180)
        at org.jacoco.agent.rt.internal_aeaf9ab.asm.ClassReader.<init>(ClassReader.java:166)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.internal.instr.InstrSupport.classReaderFor(InstrSupport.java:280)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrument(Instrumenter.java:77)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrument(Instrumenter.java:109)
        ... 133 more
java.lang.instrument.IllegalClassFormatException: Error while instrumenting java/sql/SQLWarning with JaCoCo 0.8.12.202403310830/dbfb6f2.
        at org.jacoco.agent.rt.internal_aeaf9ab.CoverageTransformer.transform(CoverageTransformer.java:94)
        at java.instrument/java.lang.instrument.ClassFileTransformer.transform(ClassFileTransformer.java:257)
        at java.instrument/sun.instrument.TransformerManager.transform(TransformerManager.java:188)
        at java.instrument/sun.instrument.InstrumentationImpl.transform(InstrumentationImpl.java:594)
        at java.base/java.lang.ClassLoader.defineClass2(Native Method)
        at java.base/java.lang.ClassLoader.defineClass(ClassLoader.java:1052)
        at java.base/java.security.SecureClassLoader.defineClass(SecureClassLoader.java:176)
        at java.base/jdk.internal.loader.BuiltinClassLoader.defineClass(BuiltinClassLoader.java:735)
        at java.base/jdk.internal.loader.BuiltinClassLoader.findClassInModuleOrNull(BuiltinClassLoader.java:678)
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClassOrNull(BuiltinClassLoader.java:604)
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClassOrNull(BuiltinClassLoader.java:639)
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClassOrNull(BuiltinClassLoader.java:608)
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:578)
        at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:490)
        at org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator.<init>(DataSourceHealthIndicator.java:86)
        at org.nnnn.ddd.ServletInitializer.dbHealthIndicator(ServletInitializer.java:36)
        at org.nnnn.ddd.ServletInitializer$$SpringCGLIB$$0.CGLIB$dbHealthIndicator$1(<generated>)
        at org.nnnn.ddd.ServletInitializer$$SpringCGLIB$$FastClass$$1.invoke(<generated>)
        at org.springframework.cglib.proxy.MethodProxy.invokeSuper(MethodProxy.java:258)
        at org.springframework.context.annotation.ConfigurationClassEnhancer$BeanMethodInterceptor.intercept(ConfigurationClassEnhancer.java:393)       
        at org.nnnn.ddd.ServletInitializer$$SpringCGLIB$$0.dbHealthIndicator(<generated>)
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.lambda$instantiate$0(SimpleInstantiationStrategy.java:171)
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiateWithFactoryMethod(SimpleInstantiationStrategy.java:88)      
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:168)
        at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:653)
        at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:645)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1375)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1205)        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:569)       
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529)
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.instantiateSingleton(DefaultListableBeanFactory.java:1222)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingleton(DefaultListableBeanFactory.java:1188)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:1123)
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:987)
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:627)
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:753)
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:439)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:318)
        at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
        at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1462)
        at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
        at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
        at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)   
        at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        at org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener.postProcessFields(MockitoTestExecutionListener.java:128)
        at org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener.injectFields(MockitoTestExecutionListener.java:112)
        at org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener.prepareTestInstance(MockitoTestExecutionListener.java:69)
        at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
        at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$10(ClassBasedTestDescriptor.java:383)   
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:388)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$11(ClassBasedTestDescriptor.java:382)   
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:214)
        at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:197)
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:214)
        at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1716)
        at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:570)
        at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
        at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
        at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:382)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$6(ClassBasedTestDescriptor.java:293)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:292)        
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$4(ClassBasedTestDescriptor.java:281)
        at java.base/java.util.Optional.orElseGet(Optional.java:364)
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$5(ClassBasedTestDescriptor.java:280)
        at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:112)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:111)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:69)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:128)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:128)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:160)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:146)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:144)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:143)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:100)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:160)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:146)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:144)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:143)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:100)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:201)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:170)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:94)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:59)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:142)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:58)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:103)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:85)
        at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
        at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
        at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
        at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
        at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
        at org.apache.maven.surefire.junitplatform.LazyLauncher.execute(LazyLauncher.java:56)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.execute(JUnitPlatformProvider.java:194)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invokeAllTests(JUnitPlatformProvider.java:150)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:124)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:385)
        at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162)
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:507)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:495)
Caused by: java.io.IOException: Error while instrumenting java/sql/SQLWarning with JaCoCo 0.8.12.202403310830/dbfb6f2.
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrumentError(Instrumenter.java:161)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrument(Instrumenter.java:111)
        at org.jacoco.agent.rt.internal_aeaf9ab.CoverageTransformer.transform(CoverageTransformer.java:92)
        ... 132 more
Caused by: java.lang.IllegalArgumentException: Unsupported class file major version 69
        at org.jacoco.agent.rt.internal_aeaf9ab.asm.ClassReader.<init>(ClassReader.java:200)
        at org.jacoco.agent.rt.internal_aeaf9ab.asm.ClassReader.<init>(ClassReader.java:180)
        at org.jacoco.agent.rt.internal_aeaf9ab.asm.ClassReader.<init>(ClassReader.java:166)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.internal.instr.InstrSupport.classReaderFor(InstrSupport.java:280)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrument(Instrumenter.java:77)
        at org.jacoco.agent.rt.internal_aeaf9ab.core.instr.Instrumenter.instrument(Instrumenter.java:109)
        ... 133 more



============================
CONDITIONS EVALUATION REPORT
============================


Positive matches:
-----------------

   CacheAutoConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.cache.CacheManager' (OnClassCondition)
      - @ConditionalOnBean (types: org.springframework.cache.interceptor.CacheAspectSupport; SearchStrategy: all) found bean 'cacheInterceptor'; @ConditionalOnMissingBean (names: cacheResolver types: org.springframework.cache.CacheManager; SearchStrategy: all) did not find any beans (OnBeanCondition)   

   CacheAutoConfiguration#cacheManagerCustomizers matched:
      - @ConditionalOnMissingBean (types: org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers; SearchStrategy: all) did not find any beans (OnBeanCondition)

   ErrorMvcAutoConfiguration matched:
      - @ConditionalOnClass found required classes 'jakarta.servlet.Servlet', 'org.springframework.web.servlet.DispatcherServlet' (OnClassCondition)    
      - found 'session' scope (OnWebApplicationCondition)

   ErrorMvcAutoConfiguration#basicErrorController matched:
      - @ConditionalOnMissingBean (types: org.springframework.boot.web.servlet.error.ErrorController; SearchStrategy: current) did not find any beans (OnBeanCondition)

   ErrorMvcAutoConfiguration#errorAttributes matched:
      - @ConditionalOnMissingBean (types: org.springframework.boot.web.servlet.error.ErrorAttributes; SearchStrategy: current) did not find any beans (OnBeanCondition)

   ErrorMvcAutoConfiguration.WhitelabelErrorViewConfiguration matched:
      - @ConditionalOnBooleanProperty (server.error.whitelabel.enabled=true) matched (OnPropertyCondition)
      - ErrorTemplate Missing did not find error template view (ErrorMvcAutoConfiguration.ErrorTemplateMissingCondition)

   ErrorMvcAutoConfiguration.WhitelabelErrorViewConfiguration#beanNameViewResolver matched:
      - @ConditionalOnMissingBean (types: org.springframework.web.servlet.view.BeanNameViewResolver; SearchStrategy: all) did not find any beans (OnBeanCondition)

   ErrorMvcAutoConfiguration.WhitelabelErrorViewConfiguration#defaultErrorView matched:
      - @ConditionalOnMissingBean (names: error; SearchStrategy: all) did not find any beans (OnBeanCondition)

   FreeMarkerAutoConfiguration matched:
      - @ConditionalOnClass found required classes 'freemarker.template.Configuration', 'org.springframework.ui.freemarker.FreeMarkerConfigurationFactory' (OnClassCondition)

   FreeMarkerServletWebConfiguration matched:
      - @ConditionalOnClass found required classes 'jakarta.servlet.Servlet', 'org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer' (OnClassCondition)
      - found 'session' scope (OnWebApplicationCondition)

   FreeMarkerServletWebConfiguration#freeMarkerConfigurer matched:
      - @ConditionalOnMissingBean (types: org.springframework.web.servlet.view.freemarker.FreeMarkerConfig; SearchStrategy: all) did not find any beans 
(OnBeanCondition)

   FreeMarkerServletWebConfiguration#freeMarkerViewResolver matched:
      - @ConditionalOnBooleanProperty (spring.freemarker.enabled=true) matched (OnPropertyCondition)
      - @ConditionalOnMissingBean (names: freeMarkerViewResolver; SearchStrategy: all) did not find any beans (OnBeanCondition)

   FreeMarkerServletWebConfiguration#resourceUrlEncodingFilter matched:
      - @ConditionalOnEnabledResourceChain found class org.webjars.WebJarVersionLocator (OnEnabledResourceChainCondition)
      - @ConditionalOnMissingBean (types: org.springframework.web.servlet.resource.ResourceUrlEncodingFilter; SearchStrategy: all) did not find any beans (OnBeanCondition)

   HttpEncodingAutoConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.web.filter.CharacterEncodingFilter' (OnClassCondition)
      - found 'session' scope (OnWebApplicationCondition)
      - @ConditionalOnBooleanProperty (server.servlet.encoding.enabled=true) matched (OnPropertyCondition)

   HttpEncodingAutoConfiguration#characterEncodingFilter matched:
      - @ConditionalOnMissingBean (types: org.springframework.web.filter.CharacterEncodingFilter; SearchStrategy: all) did not find any beans (OnBeanCondition)

   HttpMessageConvertersAutoConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.http.converter.HttpMessageConverter' (OnClassCondition)
      - NoneNestedConditions 0 matched 1 did not; NestedCondition on HttpMessageConvertersAutoConfiguration.NotReactiveWebApplicationCondition.ReactiveWebApplication did not find reactive web application classes (HttpMessageConvertersAutoConfiguration.NotReactiveWebApplicationCondition)

   HttpMessageConvertersAutoConfiguration#messageConverters matched:
      - @ConditionalOnMissingBean (types: org.springframework.boot.autoconfigure.http.HttpMessageConverters; SearchStrategy: all) did not find any beans (OnBeanCondition)

   HttpMessageConvertersAutoConfiguration.StringHttpMessageConverterConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.http.converter.StringHttpMessageConverter' (OnClassCondition)

   HttpMessageConvertersAutoConfiguration.StringHttpMessageConverterConfiguration#stringHttpMessageConverter matched:
      - @ConditionalOnMissingBean (types: org.springframework.http.converter.StringHttpMessageConverter; SearchStrategy: all) did not find any beans (OnBeanCondition)

   JacksonAutoConfiguration matched:
      - @ConditionalOnClass found required class 'com.fasterxml.jackson.databind.ObjectMapper' (OnClassCondition)

   JacksonAutoConfiguration.Jackson2ObjectMapperBuilderCustomizerConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.http.converter.json.Jackson2ObjectMapperBuilder' (OnClassCondition)

   JacksonAutoConfiguration.JacksonObjectMapperBuilderConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.http.converter.json.Jackson2ObjectMapperBuilder' (OnClassCondition)

   JacksonAutoConfiguration.JacksonObjectMapperBuilderConfiguration#jacksonObjectMapperBuilder matched:
      - @ConditionalOnMissingBean (types: org.springframework.http.converter.json.Jackson2ObjectMapperBuilder; SearchStrategy: all) did not find any beans (OnBeanCondition)

   JacksonAutoConfiguration.JacksonObjectMapperConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.http.converter.json.Jackson2ObjectMapperBuilder' (OnClassCondition)

   JacksonAutoConfiguration.JacksonObjectMapperConfiguration#jacksonObjectMapper matched:
      - @ConditionalOnMissingBean (types: com.fasterxml.jackson.databind.ObjectMapper; SearchStrategy: all) did not find any beans (OnBeanCondition)    

   JacksonAutoConfiguration.ParameterNamesModuleConfiguration matched:
      - @ConditionalOnClass found required class 'com.fasterxml.jackson.module.paramnames.ParameterNamesModule' (OnClassCondition)

   JacksonAutoConfiguration.ParameterNamesModuleConfiguration#parameterNamesModule matched:
      - @ConditionalOnMissingBean (types: com.fasterxml.jackson.module.paramnames.ParameterNamesModule; SearchStrategy: all) did not find any beans (OnBeanCondition)

   JacksonHttpMessageConvertersConfiguration.MappingJackson2HttpMessageConverterConfiguration matched:
      - @ConditionalOnClass found required class 'com.fasterxml.jackson.databind.ObjectMapper' (OnClassCondition)
      - @ConditionalOnPreferredJsonMapper JACKSON no property was configured and Jackson is the default (OnPreferredJsonMapperCondition)
      - @ConditionalOnBean (types: com.fasterxml.jackson.databind.ObjectMapper; SearchStrategy: all) found bean 'jacksonObjectMapper' (OnBeanCondition) 

   JacksonHttpMessageConvertersConfiguration.MappingJackson2HttpMessageConverterConfiguration#mappingJackson2HttpMessageConverter matched:
      - @ConditionalOnMissingBean (types: org.springframework.http.converter.json.MappingJackson2HttpMessageConverter ignored: ?; SearchStrategy: all) did not find any beans (OnBeanCondition)

   MockMvcAutoConfiguration matched:
      - found 'session' scope (OnWebApplicationCondition)

   MockMvcAutoConfiguration#dispatcherServlet matched:
      - @ConditionalOnMissingBean (types: org.springframework.web.servlet.DispatcherServlet; SearchStrategy: all) did not find any beans (OnBeanCondition)

   MockMvcAutoConfiguration#dispatcherServletPath matched:
      - @ConditionalOnMissingBean (types: org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath; SearchStrategy: all) did not find any beans (OnBeanCondition)

   MockMvcConfiguration#mockMvc matched:
      - @ConditionalOnMissingBean (types: org.springframework.test.web.servlet.MockMvc; SearchStrategy: all) did not find any beans (OnBeanCondition)   

   MockMvcConfiguration#mockMvcBuilder matched:
      - @ConditionalOnMissingBean (types: org.springframework.test.web.servlet.MockMvcBuilder; SearchStrategy: all) did not find any beans (OnBeanCondition)

   MockMvcSecurityConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors' (OnClassCondition)

   MockMvcSecurityConfiguration#securityMockMvcBuilderCustomizer matched:
      - @ConditionalOnBean (names: springSecurityFilterChain; SearchStrategy: all) found bean 'springSecurityFilterChain' (OnBeanCondition)

   MockMvcTesterConfiguration matched:
      - @ConditionalOnClass found required class 'org.assertj.core.api.Assert' (OnClassCondition)

   MockMvcTesterConfiguration#mockMvcTester matched:
      - @ConditionalOnMissingBean (types: org.springframework.test.web.servlet.assertj.MockMvcTester; SearchStrategy: all) did not find any beans (OnBeanCondition)

   NoOpCacheConfiguration matched:
      - Cache org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration NONE cache type (CacheCondition)
      - @ConditionalOnMissingBean (types: org.springframework.cache.CacheManager; SearchStrategy: all) did not find any beans (OnBeanCondition)

   OAuth2ResourceServerAutoConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken' (OnClassCondition)
      - found 'session' scope (OnWebApplicationCondition)

   OAuth2ResourceServerJwtConfiguration.JwtDecoderConfiguration matched:
      - @ConditionalOnMissingBean (types: org.springframework.security.oauth2.jwt.JwtDecoder; SearchStrategy: all) did not find any beans (OnBeanCondition)

   OAuth2ResourceServerJwtConfiguration.JwtDecoderConfiguration#jwtDecoderByIssuerUri matched:
      - OpenID Connect Issuer URI Condition found issuer-uri property (IssuerUriCondition)

   OAuth2ResourceServerJwtConfiguration.OAuth2SecurityFilterChainConfiguration matched:
      - AllNestedConditions 2 matched 0 did not; NestedCondition on DefaultWebSecurityCondition.Beans @ConditionalOnMissingBean (types: org.springframework.security.web.SecurityFilterChain; SearchStrategy: all) did not find any beans; NestedCondition on DefaultWebSecurityCondition.Classes @ConditionalOnClass found required classes 'org.springframework.security.web.SecurityFilterChain', 'org.springframework.security.config.annotation.web.builders.HttpSecurity' (DefaultWebSecurityCondition)

   OAuth2ResourceServerJwtConfiguration.OAuth2SecurityFilterChainConfiguration#jwtSecurityFilterChain matched:
      - @ConditionalOnBean (types: org.springframework.security.oauth2.jwt.JwtDecoder; SearchStrategy: all) found bean 'jwtDecoderByIssuerUri' (OnBeanCondition)

   OAuth2ResourceServerOpaqueTokenConfiguration.OpaqueTokenIntrospectionClientConfiguration matched:
      - @ConditionalOnMissingBean (types: org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector; SearchStrategy: all) did not find any beans (OnBeanCondition)

   Oauth2ResourceServerConfiguration.JwtConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.security.oauth2.jwt.JwtDecoder' (OnClassCondition)

   SecurityAutoConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.security.authentication.DefaultAuthenticationEventPublisher' (OnClassCondition)   

   SecurityAutoConfiguration#authenticationEventPublisher matched:
      - @ConditionalOnMissingBean (types: org.springframework.security.authentication.AuthenticationEventPublisher; SearchStrategy: all) did not find any beans (OnBeanCondition)

   SecurityFilterAutoConfiguration matched:
      - @ConditionalOnClass found required classes 'org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer', 'org.springframework.security.config.http.SessionCreationPolicy' (OnClassCondition)
      - found 'session' scope (OnWebApplicationCondition)

   SecurityFilterAutoConfiguration#securityFilterChainRegistration matched:
      - @ConditionalOnBean (names: springSecurityFilterChain; SearchStrategy: all) found bean 'springSecurityFilterChain' (OnBeanCondition)

   SpringBootWebSecurityConfiguration matched:
      - found 'session' scope (OnWebApplicationCondition)

   SpringBootWebSecurityConfiguration.WebSecurityEnablerConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.security.config.annotation.web.configuration.EnableWebSecurity' (OnClassCondition)      - @ConditionalOnMissingBean (names: springSecurityFilterChain; SearchStrategy: all) did not find any beans (OnBeanCondition)

   SpringDataWebAutoConfiguration matched:
      - @ConditionalOnClass found required classes 'org.springframework.data.web.PageableHandlerMethodArgumentResolver', 'org.springframework.web.servlet.config.annotation.WebMvcConfigurer' (OnClassCondition)
      - found 'session' scope (OnWebApplicationCondition)
      - @ConditionalOnMissingBean (types: org.springframework.data.web.PageableHandlerMethodArgumentResolver; SearchStrategy: all) did not find any beans (OnBeanCondition)

   SpringDataWebAutoConfiguration#pageableCustomizer matched:
      - @ConditionalOnMissingBean (types: org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer; SearchStrategy: all) did 
not find any beans (OnBeanCondition)

   SpringDataWebAutoConfiguration#sortCustomizer matched:
      - @ConditionalOnMissingBean (types: org.springframework.data.web.config.SortHandlerMethodArgumentResolverCustomizer; SearchStrategy: all) did not 
find any beans (OnBeanCondition)

   SpringDataWebAutoConfiguration#springDataWebSettings matched:
      - @ConditionalOnMissingBean (types: org.springframework.data.web.config.SpringDataWebSettings; SearchStrategy: all) did not find any beans (OnBeanCondition)

   TaskExecutionAutoConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor' (OnClassCondition)

   TaskExecutorConfigurations.AsyncConfigurerConfiguration matched:
      - @ConditionalOnMissingBean (types: org.springframework.scheduling.annotation.AsyncConfigurer; SearchStrategy: all) did not find any beans (OnBeanCondition)

   TaskExecutorConfigurations.AsyncConfigurerConfiguration#applicationTaskExecutorAsyncConfigurer matched:
      - @ConditionalOnMissingBean (types: org.springframework.scheduling.annotation.AsyncConfigurer; SearchStrategy: all) did not find any beans (OnBeanCondition)

   TaskExecutorConfigurations.SimpleAsyncTaskExecutorBuilderConfiguration#simpleAsyncTaskExecutorBuilder matched:
      - @ConditionalOnMissingBean (types: org.springframework.boot.task.SimpleAsyncTaskExecutorBuilder; SearchStrategy: all) did not find any beans (OnBeanCondition)
      - @ConditionalOnThreading found PLATFORM (OnThreadingCondition)

   TaskExecutorConfigurations.TaskExecutorConfiguration matched:
      - AnyNestedCondition 1 matched 1 did not; NestedCondition on TaskExecutorConfigurations.OnExecutorCondition.ModelCondition @ConditionalOnProperty 
(spring.task.execution.mode=force) did not find property 'spring.task.execution.mode'; NestedCondition on TaskExecutorConfigurations.OnExecutorCondition.ExecutorBeanCondition @ConditionalOnMissingBean (types: java.util.concurrent.Executor; SearchStrategy: all) did not find any beans (TaskExecutorConfigurations.OnExecutorCondition)

   TaskExecutorConfigurations.TaskExecutorConfiguration#applicationTaskExecutor matched:
      - @ConditionalOnThreading found PLATFORM (OnThreadingCondition)

   TaskExecutorConfigurations.ThreadPoolTaskExecutorBuilderConfiguration#threadPoolTaskExecutorBuilder matched:
      - @ConditionalOnMissingBean (types: org.springframework.boot.task.ThreadPoolTaskExecutorBuilder; SearchStrategy: all) did not find any beans (OnBeanCondition)

   ValidationAutoConfiguration matched:
      - @ConditionalOnClass found required class 'jakarta.validation.executable.ExecutableValidator' (OnClassCondition)
      - @ConditionalOnResource found location classpath:META-INF/services/jakarta.validation.spi.ValidationProvider (OnResourceCondition)

   ValidationAutoConfiguration#defaultValidator matched:
      - @ConditionalOnMissingBean (types: jakarta.validation.Validator; SearchStrategy: all) did not find any beans (OnBeanCondition)

   ValidationAutoConfiguration#methodValidationPostProcessor matched:
      - @ConditionalOnMissingBean (types: org.springframework.validation.beanvalidation.MethodValidationPostProcessor; SearchStrategy: current) did not 
find any beans (OnBeanCondition)

   WebMvcAutoConfiguration matched:
      - @ConditionalOnClass found required classes 'jakarta.servlet.Servlet', 'org.springframework.web.servlet.DispatcherServlet', 'org.springframework.web.servlet.config.annotation.WebMvcConfigurer' (OnClassCondition)
      - found 'session' scope (OnWebApplicationCondition)
      - @ConditionalOnMissingBean (types: org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport; SearchStrategy: all) did not find any beans (OnBeanCondition)

   WebMvcAutoConfiguration#formContentFilter matched:
      - @ConditionalOnBooleanProperty (spring.mvc.formcontent.filter.enabled=true) matched (OnPropertyCondition)
      - @ConditionalOnMissingBean (types: org.springframework.web.filter.FormContentFilter; SearchStrategy: all) did not find any beans (OnBeanCondition)

   WebMvcAutoConfiguration.EnableWebMvcConfiguration#flashMapManager matched:
      - @ConditionalOnMissingBean (names: flashMapManager; SearchStrategy: all) did not find any beans (OnBeanCondition)

   WebMvcAutoConfiguration.EnableWebMvcConfiguration#localeResolver matched:
      - @ConditionalOnMissingBean (names: localeResolver; SearchStrategy: all) did not find any beans (OnBeanCondition)

   WebMvcAutoConfiguration.EnableWebMvcConfiguration#themeResolver matched:
      - @ConditionalOnMissingBean (names: themeResolver; SearchStrategy: all) did not find any beans (OnBeanCondition)

   WebMvcAutoConfiguration.EnableWebMvcConfiguration#viewNameTranslator matched:
      - @ConditionalOnMissingBean (names: viewNameTranslator; SearchStrategy: all) did not find any beans (OnBeanCondition)

   WebMvcAutoConfiguration.ResourceChainCustomizerConfiguration matched:
      - @ConditionalOnEnabledResourceChain found class org.webjars.WebJarVersionLocator (OnEnabledResourceChainCondition)

   WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#defaultViewResolver matched:
      - @ConditionalOnMissingBean (types: org.springframework.web.servlet.view.InternalResourceViewResolver; SearchStrategy: all) did not find any beans (OnBeanCondition)

   WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#requestContextFilter matched:
      - @ConditionalOnMissingBean (types: org.springframework.web.context.request.RequestContextListener,org.springframework.web.filter.RequestContextFilter; SearchStrategy: all) did not find any beans (OnBeanCondition)

   WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#viewResolver matched:
      - @ConditionalOnBean (types: org.springframework.web.servlet.ViewResolver; SearchStrategy: all) found beans 'defaultViewResolver', 'beanNameViewResolver', 'mvcViewResolver'; @ConditionalOnMissingBean (names: viewResolver types: org.springframework.web.servlet.view.ContentNegotiatingViewResolver; SearchStrategy: all) did not find any beans (OnBeanCondition)


Negative matches:
-----------------

   Cache2kCacheConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.cache2k.Cache2kBuilder' (OnClassCondition)

   CacheAutoConfiguration.CacheManagerEntityManagerFactoryDependsOnPostProcessor:
      Did not match:
         - @ConditionalOnBean (types: org.springframework.orm.jpa.AbstractEntityManagerFactoryBean; SearchStrategy: all) did not find any beans of type 
org.springframework.orm.jpa.AbstractEntityManagerFactoryBean (OnBeanCondition)
      Matched:
         - @ConditionalOnClass found required class 'org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean' (OnClassCondition)

   CaffeineCacheConfiguration:
      Did not match:
         - Cache org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration unknown cache type (CacheCondition)
      Matched:
         - @ConditionalOnClass found required classes 'com.github.benmanes.caffeine.cache.Caffeine', 'org.springframework.cache.caffeine.CaffeineCacheManager' (OnClassCondition)

   CouchbaseCacheConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'com.couchbase.client.java.Cluster' (OnClassCondition)

   ErrorMvcAutoConfiguration.DefaultErrorViewResolverConfiguration#conventionErrorViewResolver:
      Did not match:
         - @ConditionalOnBean (types: org.springframework.web.servlet.DispatcherServlet; SearchStrategy: all) did not find any beans of type org.springframework.web.servlet.DispatcherServlet (OnBeanCondition)

   FreeMarkerNonWebConfiguration:
      Did not match:
         - @ConditionalOnWebApplication found 'session' scope and did not find reactive web application classes (OnWebApplicationCondition)

   FreeMarkerReactiveWebConfiguration:
      Did not match:
         - did not find reactive web application classes (OnWebApplicationCondition)

   GenericCacheConfiguration:
      Did not match:
         - Cache org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration unknown cache type (CacheCondition)

   GroovyTemplateAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'groovy.text.markup.MarkupTemplateEngine' (OnClassCondition)

   GsonAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'com.google.gson.Gson' (OnClassCondition)

   GsonHttpMessageConvertersConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'com.google.gson.Gson' (OnClassCondition)

   HazelcastCacheConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'com.hazelcast.core.HazelcastInstance' (OnClassCondition)

   HypermediaAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.hateoas.EntityModel' (OnClassCondition)

   InfinispanCacheConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.infinispan.spring.embedded.provider.SpringEmbeddedCacheManager' (OnClassCondition)      

   JCacheCacheConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'javax.cache.Caching' (OnClassCondition)

   JacksonHttpMessageConvertersConfiguration.MappingJackson2XmlHttpMessageConverterConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'com.fasterxml.jackson.dataformat.xml.XmlMapper' (OnClassCondition)

   JsonbAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'jakarta.json.bind.Jsonb' (OnClassCondition)

   JsonbHttpMessageConvertersConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'jakarta.json.bind.Jsonb' (OnClassCondition)

   MessageSourceAutoConfiguration:
      Did not match:
         - ResourceBundle did not find bundle with basename messages (MessageSourceAutoConfiguration.ResourceBundleCondition)

   MockMvcAutoConfiguration.WebTestClientMockMvcConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.web.reactive.function.client.WebClient' (OnClassCondition)

   MockMvcWebClientAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.htmlunit.WebClient' (OnClassCondition)

   MockMvcWebDriverAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.openqa.selenium.htmlunit.HtmlUnitDriver' (OnClassCondition)

   MustacheAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'com.samskivert.mustache.Mustache' (OnClassCondition)

   OAuth2ClientAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.security.oauth2.client.registration.ClientRegistration' (OnClassCondition)

   OAuth2ClientWebSecurityAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository' (OnClassCondition)

   OAuth2ResourceServerJwtConfiguration.JwtConverterConfiguration:
      Did not match:
         - AnyNestedCondition 0 matched 3 did not; NestedCondition on OAuth2ResourceServerJwtConfiguration.JwtConverterPropertiesCondition.OnAuthoritiesClaimName @ConditionalOnProperty (spring.security.oauth2.resourceserver.jwt.authorities-claim-name) did not find property 'spring.security.oauth2.resourceserver.jwt.authorities-claim-name'; NestedCondition on OAuth2ResourceServerJwtConfiguration.JwtConverterPropertiesCondition.OnPrincipalClaimName @ConditionalOnProperty (spring.security.oauth2.resourceserver.jwt.principal-claim-name) did not find property 'spring.security.oauth2.resourceserver.jwt.principal-claim-name'; NestedCondition on OAuth2ResourceServerJwtConfiguration.JwtConverterPropertiesCondition.OnAuthorityPrefix @ConditionalOnProperty (spring.security.oauth2.resourceserver.jwt.authority-prefix) did not find property 'spring.security.oauth2.resourceserver.jwt.authority-prefix' (OAuth2ResourceServerJwtConfiguration.JwtConverterPropertiesCondition)

   OAuth2ResourceServerJwtConfiguration.JwtDecoderConfiguration#jwtDecoderByJwkKeySetUri:
      Did not match:
         - @ConditionalOnProperty (spring.security.oauth2.resourceserver.jwt.jwk-set-uri) did not find property 'spring.security.oauth2.resourceserver.jwt.jwk-set-uri' (OnPropertyCondition)

   OAuth2ResourceServerJwtConfiguration.JwtDecoderConfiguration#jwtDecoderByPublicKeyValue:
      Did not match:
         - Public Key Value Condition did not find public-key-location property (KeyValueCondition)

   OAuth2ResourceServerOpaqueTokenConfiguration.OAuth2SecurityFilterChainConfiguration:
      Did not match:
         - AllNestedConditions 1 matched 1 did not; NestedCondition on DefaultWebSecurityCondition.Beans @ConditionalOnMissingBean (types: org.springframework.security.web.SecurityFilterChain; SearchStrategy: all) found beans of type 'org.springframework.security.web.SecurityFilterChain' jwtSecurityFilterChain; NestedCondition on DefaultWebSecurityCondition.Classes @ConditionalOnClass found required classes 'org.springframework.security.web.SecurityFilterChain', 'org.springframework.security.config.annotation.web.builders.HttpSecurity' (DefaultWebSecurityCondition)

   OAuth2ResourceServerOpaqueTokenConfiguration.OpaqueTokenIntrospectionClientConfiguration#opaqueTokenIntrospector:
      Did not match:
         - @ConditionalOnProperty (spring.security.oauth2.resourceserver.opaquetoken.introspection-uri) did not find property 'spring.security.oauth2.resourceserver.opaquetoken.introspection-uri' (OnPropertyCondition)

   RedisCacheConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.data.redis.connection.RedisConnectionFactory' (OnClassCondition)        

   SecurityDataConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.security.data.repository.query.SecurityEvaluationContextExtension' (OnClassCondition)

   SimpleCacheConfiguration:
      Did not match:
         - Cache org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration unknown cache type (CacheCondition)

   SpringBootWebSecurityConfiguration.SecurityFilterChainConfiguration:
      Did not match:
         - AllNestedConditions 1 matched 1 did not; NestedCondition on DefaultWebSecurityCondition.Beans @ConditionalOnMissingBean (types: org.springframework.security.web.SecurityFilterChain; SearchStrategy: all) found beans of type 'org.springframework.security.web.SecurityFilterChain' jwtSecurityFilterChain; NestedCondition on DefaultWebSecurityCondition.Classes @ConditionalOnClass found required classes 'org.springframework.security.web.SecurityFilterChain', 'org.springframework.security.config.annotation.web.builders.HttpSecurity' (DefaultWebSecurityCondition)

   TaskExecutorConfigurations.SimpleAsyncTaskExecutorBuilderConfiguration#simpleAsyncTaskExecutorBuilderVirtualThreads:
      Did not match:
         - @ConditionalOnMissingBean (types: org.springframework.boot.task.SimpleAsyncTaskExecutorBuilder; SearchStrategy: all) found beans of type 'org.springframework.boot.task.SimpleAsyncTaskExecutorBuilder' simpleAsyncTaskExecutorBuilder (OnBeanCondition)

   TaskExecutorConfigurations.TaskExecutorConfiguration#applicationTaskExecutorVirtualThreads:
      Did not match:
         - @ConditionalOnThreading did not find VIRTUAL (OnThreadingCondition)

   ThymeleafAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.thymeleaf.spring6.SpringTemplateEngine' (OnClassCondition)

   UserDetailsServiceAutoConfiguration:
      Did not match:
         - AnyNestedCondition 0 matched 3 did not; NestedCondition on UserDetailsServiceAutoConfiguration.MissingAlternativeOrUserPropertiesConfigured.PasswordConfigured @ConditionalOnProperty (spring.security.user.password) did not find property 'spring.security.user.password'; NestedCondition on UserDetailsServiceAutoConfiguration.MissingAlternativeOrUserPropertiesConfigured.NameConfigured @ConditionalOnProperty (spring.security.user.name) did not find property 'spring.security.user.name'; NestedCondition on UserDetailsServiceAutoConfiguration.MissingAlternativeOrUserPropertiesConfigured.MissingAlternative @ConditionalOnMissingClass found unwanted class 'org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector' (UserDetailsServiceAutoConfiguration.MissingAlternativeOrUserPropertiesConfigured)
      Matched:
         - @ConditionalOnClass found required class 'org.springframework.security.authentication.AuthenticationManager' (OnClassCondition)
         - found 'session' scope (OnWebApplicationCondition)

   WebMvcAutoConfiguration#hiddenHttpMethodFilter:
      Did not match:
         - @ConditionalOnBooleanProperty (spring.mvc.hiddenmethod.filter.enabled=true) did not find property 'spring.mvc.hiddenmethod.filter.enabled' (OnPropertyCondition)

   WebMvcAutoConfiguration.ProblemDetailsErrorHandlingConfiguration:
      Did not match:
         - @ConditionalOnBooleanProperty (spring.mvc.problemdetails.enabled=true) did not find property 'spring.mvc.problemdetails.enabled' (OnPropertyCondition)

   WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#beanNameViewResolver:
      Did not match:
         - @ConditionalOnMissingBean (types: org.springframework.web.servlet.view.BeanNameViewResolver; SearchStrategy: all) found beans of type 'org.springframework.web.servlet.view.BeanNameViewResolver' beanNameViewResolver (OnBeanCondition)

   WebTestClientAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.web.reactive.function.client.WebClient' (OnClassCondition)


Exclusions:
-----------

    None


Unconditional classes:
----------------------

    None




Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate 
[org.springframework.web.servlet.function.support.RouterFunctionMapping]: Factory method 'routerFunctionMapping' threw exception with message: Cannot   
invoke "com.fasterxml.jackson.databind.ObjectReader.forType(java.lang.Class)" because the return value of
"com.fasterxml.jackson.databind.ObjectMapper.reader()" is null
Caused by: java.lang.NullPointerException: Cannot invoke "com.fasterxml.jackson.databind.ObjectReader.forType(java.lang.Class)" because the return      
value of "com.fasterxml.jackson.databind.ObjectMapper.reader()" is null
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'routerFunctionMapping' defined in class path
resource [org/springframework/boot/autoconfigure/web/servlet/WebMvcAutoConfiguration$EnableWebMvcConfiguration.class]: Failed to instantiate
[org.springframework.web.servlet.function.support.RouterFunctionMapping]: Factory method 'routerFunctionMapping' threw exception with message: Cannot 
invoke "com.fasterxml.jackson.databind.ObjectReader.forType(java.lang.Class)" because the return value of
"com.fasterxml.jackson.databind.ObjectMapper.reader()" is null
Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate
[org.springframework.web.servlet.function.support.RouterFunctionMapping]: Factory method 'routerFunctionMapping' threw exception with message: Cannot   
invoke "com.fasterxml.jackson.databind.ObjectReader.forType(java.lang.Class)" because the return value of
"com.fasterxml.jackson.databind.ObjectMapper.reader()" is null
Caused by: java.lang.NullPointerException: Cannot invoke "com.fasterxml.jackson.databind.ObjectReader.forType(java.lang.Class)" because the return      
value of "com.fasterxml.jackson.databind.ObjectMapper.reader()" is null
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'routerFunctionMapping' defined in class path 
resource [org/springframework/boot/autoconfigure/web/servlet/WebMvcAutoConfiguration$EnableWebMvcConfiguration.class]: Failed to instantiate
[org.springframework.web.servlet.function.support.RouterFunctionMapping]: Factory method 'routerFunctionMapping' threw exception with message: Cannot   
invoke "com.fasterxml.jackson.databind.ObjectReader.forType(java.lang.Class)" because the return value of
"com.fasterxml.jackson.databind.ObjectMapper.reader()" is null
Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate
[org.springframework.web.servlet.function.support.RouterFunctionMapping]: Factory method 'routerFunctionMapping' threw exception with message: Cannot   
invoke "com.fasterxml.jackson.databind.ObjectReader.forType(java.lang.Class)" because the return value of
"com.fasterxml.jackson.databind.ObjectMapper.reader()" is null
Caused by: java.lang.NullPointerException: Cannot invoke "com.fasterxml.jackson.databind.ObjectReader.forType(java.lang.Class)" because the return      
value of "com.fasterxml.jackson.databind.ObjectMapper.reader()" is null
Caused by: org.apache.maven.plugin.MojoFailureException: 
