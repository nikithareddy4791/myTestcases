[ERROR] Failures: 
[ERROR]   CaseServiceControllerTest.deleteCaseUploadById_noAcceptHeader_returns501:583 Status expected:<500> but was:<403>       
[ERROR]   CaseServiceControllerTest.deleteCaseUploadById_noAuth_returns401:590 Status expected:<401> but was:<403>
[ERROR]   CaseServiceControllerTest.deleteCaseUploadById_success_returnsOk:561 Status expected:<200> but was:<403>
[ERROR] Errors: 
[ERROR]   CaseServiceControllerTest.deleteCaseUploadById_ioException_returns500:572 NotAMock 
Argument passed to when() is not a mock!
Example of correct stubbing:
    doThrow(new RuntimeException()).when(mock).someMethod();
