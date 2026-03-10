[ERROR] Failures: 
[ERROR]   ReferenceControllerTest.getdluOfficeList_returnsOkWithList:349 
Wanted but not invoked:
org.nypd.dlu.service.ReferenceDataService#0 bean.getAllDLUOffices();
-> at org.nypd.dlu.service.ReferenceDataService.getAllDLUOffices(ReferenceDataService.java:130)

However, there was exactly 1 interaction with this mock:
org.nypd.dlu.service.ReferenceDataService#0 bean.getDLUOffices();
-> at org.nypd.dlu.api.ReferenceApiController.getDLUOfficeList(ReferenceApiController.java:158)


[INFO]
[ERROR] Tests run: 68, Failures: 1, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  23.546 s
[INFO] Finished at: 2026-03-10T15:04:25-04:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.5.3:test (default-test) on project dlu-services: There are test failures.
[ERROR]
[ERROR] See F:\project\dlu-services\target\surefire-reports for the individual test results.
[ERROR] See dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR] -> [Help 1]
