
[ERROR] Errors: 
[ERROR]   DellS3ServiceTest.listFiles_callsWithCorrectBucket:234->mockIterableWithObjects:200 UnfinishedStubbing 
Unfinished stubbing detected here:
-> at org.nnnn.ddd.service.DellS3ServiceTest.listFiles_callsWithCorrectBucket(DellS3ServiceTest.java:233)

E.g. thenReturn() may be missing.
Examples of correct stubbing:
    when(mock.isOk()).thenReturn(true);
    when(mock.isOk()).thenThrow(exception);
    doThrow(exception).when(mock).someVoidMethod();
Hints:
 1. missing thenReturn()
 2. you are trying to stub a final method, which is not supported
 3. you are stubbing the behaviour of another mock inside before 'thenReturn' instruction is completed

[ERROR]   DellS3ServiceTest.listFiles_emptyBucket_returnsEmptyList:223->mockIterableWithObjects:200 UnfinishedStubbing 
Unfinished stubbing detected here:
-> at org.nnnn.ddd.service.DellS3ServiceTest.listFiles_emptyBucket_returnsEmptyList(DellS3ServiceTest.java:222)

E.g. thenReturn() may be missing.
Examples of correct stubbing:
    when(mock.isOk()).thenReturn(true);
    when(mock.isOk()).thenThrow(exception);
    doThrow(exception).when(mock).someVoidMethod();
Hints:
 1. missing thenReturn()
 2. you are trying to stub a final method, which is not supported
 3. you are stubbing the behaviour of another mock inside before 'thenReturn' instruction is completed

[ERROR]   DellS3ServiceTest.listFiles_returnsFileKeys:211->mockIterableWithObjects:200 UnfinishedStubbing 
Unfinished stubbing detected here:
-> at org.nnnn.ddd.service.DellS3ServiceTest.listFiles_returnsFileKeys(DellS3ServiceTest.java:210)

E.g. thenReturn() may be missing.
Examples of correct stubbing:
    when(mock.isOk()).thenReturn(true);
    when(mock.isOk()).thenThrow(exception);
    doThrow(exception).when(mock).someVoidMethod();
Hints:
 1. missing thenReturn()
 2. you are trying to stub a final method, which is not supported
 3. you are stubbing the behaviour of another mock inside before 'thenReturn' instruction is completed

[ERROR]   DellS3ServiceTest.listFiles_returnsOnlyKeys:252->mockIterableWithObjects:200 UnfinishedStubbing 
Unfinished stubbing detected here:
-> at org.nnnn.ddd.service.DellS3ServiceTest.listFiles_returnsOnlyKeys(DellS3ServiceTest.java:251)

E.g. thenReturn() may be missing.
Examples of correct stubbing:
    when(mock.isOk()).thenReturn(true);
    when(mock.isOk()).thenThrow(exception);
    doThrow(exception).when(mock).someVoidMethod();
Hints:
 1. missing thenReturn()
 2. you are trying to stub a final method, which is not supported
 3. you are stubbing the behaviour of another mock inside before 'thenReturn' instruction is completed

[INFO]
[ERROR] Tests run: 13, Failures: 0, Errors: 4, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
