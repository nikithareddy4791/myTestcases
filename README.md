[ERROR] Failures: 
[ERROR]   CaseFlowChartTest.getCaseById_caseSealed_notSupervisor_notInSealedGroup_returns500:352 Status expected:<500> but was:<403>
[ERROR]   CaseFlowChartTest.getCaseById_userNotAssigned_notSealed_notInOffice_returns500:302 Status expected:<500> but was:<403>
[ERROR]   CaseServiceLoadCaseTest.loadCase_statusNull_setsActiveFlg0:179 
expected: 0
 but was: 1
[ERROR] Errors: 
[ERROR]   CaseServiceLoadCaseTest.loadCase_sealedArrest_nonSupervisor_wrongRole_throwsCaseAccessException » UnnecessaryStubbing 
Unnecessary stubbings detected.
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at org.nnnn.ddd.service.CaseServiceLoadCaseTest.loadCase_sealedArrest_nonSupervisor_wrongRole_throwsCaseAccessException(CaseServiceLoadTest.java:292)Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
[ERROR]   CaseServiceLoadCaseTest.loadCase_statusCompleted_setsActiveFlg0 » UnnecessaryStubbing 
Unnecessary stubbings detected.
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at org.nnnn.ddd.service.CaseServiceLoadCaseTest.loadCase_statusCompleted_setsActiveFlg0(CaseServiceLoadTest.java:164)
Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
[ERROR]   CaseServiceLoadCaseTest.loadCase_statusInProgress_setsActiveFlg1 » UnnecessaryStubbing 
Unnecessary stubbings detected.
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at org.nnnn.ddd.service.CaseServiceLoadCaseTest.loadCase_statusInProgress_setsActiveFlg1(CaseServiceLoadTest.java:134)
Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
[ERROR]   CaseServiceLoadCaseTest.loadCase_statusNotStarted_setsActiveFlg1 » UnnecessaryStubbing 
Unnecessary stubbings detected.
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at org.nnnn.ddd.service.CaseServiceLoadCaseTest.loadCase_statusNotStarted_setsActiveFlg1(CaseServiceLoadTest.java:119)
Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
[ERROR]   CaseServiceLoadCaseTest.loadCase_statusWaiting_setsActiveFlg1 » UnnecessaryStubbing 
Unnecessary stubbings detected.
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at org.nnnn.ddd.service.CaseServiceLoadCaseTest.loadCase_statusWaiting_setsActiveFlg1(CaseServiceLoadTest.java:149)
Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
[INFO]
[ERROR] Tests run: 154, Failures: 3, Errors: 5, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  34.849 s
[INFO] Finished at: 2026-03-24T14:55:59-04:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.5.3:test (default-test) on project ddd-services: There are test failures.    
[ERROR]
[ERROR] See F:\project\ddd-services\target\surefire-reports for the individual test results.
[ERROR] See dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
