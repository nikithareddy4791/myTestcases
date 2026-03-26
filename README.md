 T E S T S
[INFO] -------------------------------------------------------
[INFO] Running org.nnnn.ddd.service.DellS3ServiceTest
Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build as described in Mockito's documentation: https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/Mockito.html#0.3
Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
WARNING: A Java agent has been loaded dynamically (C:\Users\V-KOMANDLA1\.m2\repository\net\bytebuddy\byte-buddy-agent\1.17.5\byte-buddy-agent-1.17.5.jar)
WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning
WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information
WARNING: Dynamic loading of agents will be disallowed by default in a future release
[ERROR] Tests run: 13, Failures: 0, Errors: 4, Skipped: 0, Time elapsed: 3.030 s <<< FAILURE! -- in org.nnnn.ddd.service.DellS3ServiceTest
[ERROR] org.nnnn.ddd.service.DellS3ServiceTest.listFiles_returnsFileKeys -- Time elapsed: 2.583 s <<< ERROR!
org.mockito.exceptions.misusing.WrongTypeOfReturnValue:

Default answer returned a result with the wrong type:
DellS3ServiceTest$$Lambda/0x00000000132ebd88 cannot be returned by contents()
contents() should return SdkIterable

The default answer of listObjectsV2Iterable that was configured on the mock is probably incorrectly implemented.

        at software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable.contents(ListObjectsV2Iterable.java:116)
        at org.nnnn.ddd.service.DellS3Service.listFiles(DellS3Service.java:64)
        at org.nnnn.ddd.service.DellS3ServiceTest.listFiles_returnsFileKeys(DellS3ServiceTest.java:208)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.DellS3ServiceTest.listFiles_emptyBucket_returnsEmptyList -- Time elapsed: 0.005 s <<< ERROR!
org.mockito.exceptions.misusing.WrongTypeOfReturnValue:

Default answer returned a result with the wrong type:
DellS3ServiceTest$$Lambda/0x00000000133136a8 cannot be returned by contents()
contents() should return SdkIterable

The default answer of listObjectsV2Iterable that was configured on the mock is probably incorrectly implemented.

        at software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable.contents(ListObjectsV2Iterable.java:116)
        at org.nnnn.ddd.service.DellS3Service.listFiles(DellS3Service.java:64)
        at org.nnnn.ddd.service.DellS3ServiceTest.listFiles_emptyBucket_returnsEmptyList(DellS3ServiceTest.java:227)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.DellS3ServiceTest.listFiles_callsWithCorrectBucket -- Time elapsed: 0.004 s <<< ERROR!
org.mockito.exceptions.misusing.WrongTypeOfReturnValue:

Default answer returned a result with the wrong type:
DellS3ServiceTest$$Lambda/0x0000000013357458 cannot be returned by contents()
contents() should return SdkIterable

The default answer of listObjectsV2Iterable that was configured on the mock is probably incorrectly implemented.

        at software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable.contents(ListObjectsV2Iterable.java:116)
        at org.nnnn.ddd.service.DellS3Service.listFiles(DellS3Service.java:64)
        at org.nnnn.ddd.service.DellS3ServiceTest.listFiles_callsWithCorrectBucket(DellS3ServiceTest.java:246)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.service.DellS3ServiceTest.listFiles_returnsOnlyKeys -- Time elapsed: 0.005 s <<< ERROR!
org.mockito.exceptions.misusing.WrongTypeOfReturnValue:

Default answer returned a result with the wrong type:
DellS3ServiceTest$$Lambda/0x000000001335c000 cannot be returned by contents()
contents() should return SdkIterable

The default answer of listObjectsV2Iterable that was configured on the mock is probably incorrectly implemented.

        at software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable.contents(ListObjectsV2Iterable.java:116)
        at org.nnnn.ddd.service.DellS3Service.listFiles(DellS3Service.java:64)
        at org.nnnn.ddd.service.DellS3ServiceTest.listFiles_returnsOnlyKeys(DellS3ServiceTest.java:269)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[INFO] 
[INFO] Results:
[INFO]
[ERROR] Errors: 
[ERROR]   DellS3ServiceTest.listFiles_callsWithCorrectBucket:246 » WrongTypeOfReturnValue 
Default answer returned a result with the wrong type:
DellS3ServiceTest$$Lambda/0x0000000013357458 cannot be returned by contents()
contents() should return SdkIterable

The default answer of listObjectsV2Iterable that was configured on the mock is probably incorrectly implemented.

[ERROR]   DellS3ServiceTest.listFiles_emptyBucket_returnsEmptyList:227 » WrongTypeOfReturnValue 
Default answer returned a result with the wrong type:
DellS3ServiceTest$$Lambda/0x00000000133136a8 cannot be returned by contents()
contents() should return SdkIterable

The default answer of listObjectsV2Iterable that was configured on the mock is probably incorrectly implemented.

[ERROR]   DellS3ServiceTest.listFiles_returnsFileKeys:208 » WrongTypeOfReturnValue 
Default answer returned a result with the wrong type:
DellS3ServiceTest$$Lambda/0x00000000132ebd88 cannot be returned by contents()
contents() should return SdkIterable

The default answer of listObjectsV2Iterable that was configured on the mock is probably incorrectly implemented.

[ERROR]   DellS3ServiceTest.listFiles_returnsOnlyKeys:269 » WrongTypeOfReturnValue 
Default answer returned a result with the wrong type:
DellS3ServiceTest$$Lambda/0x000000001335c000 cannot be returned by contents()
contents() should return SdkIterable

The default answer of listObjectsV2Iterable that was configured on the mock is probably incorrectly implemented.

[INFO]
[ERROR] Tests run: 13, Failures: 0, Errors: 4, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO]
