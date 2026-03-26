FO]
[ERROR] Failures: 
[ERROR]   ADSearchServiceDevTest.findAllUsersWithSealedAccess_addsBronxTestUser:198 
Expecting any elements of:
  []
to match given predicate but none did.
[ERROR]   ADSearchServiceDevTest.findAllUsersWithSealedAccess_combinesProdAndBronxTestUser:223 
Expected size: 2 but was: 1 in:
[class User {
    username: jdoe
    firstName: John
    lastName: Doe
    email: jdoe@nnnn.org
    rank: null
    title: null
    tax: null
    cmdCode: null
    mobile: null
    roles: null
}]
[ERROR]   ADSearchServiceDevTest.findAllUsersWithSealedAccess_onlyAddsBronxNotAllTestUsers:210 
Expected size: 1 but was: 0 in:
[]
[ERROR]   ADSearchServiceDevTest.findAllUsers_combinesProdAndTestUsers:172 
Expected size: 10 but was: 1 in:
[class User {
    username: jdoe
    firstName: John
    lastName: Doe
    email: jdoe@nnnn.org
    rank: null
    title: null
    tax: null
    cmdCode: null
    mobile: null
    roles: null
}]
[ERROR]   ADSearchServiceDevTest.findAllUsers_noProdUsers_returnsOnlyTestUsers:183 
Expected size: 9 but was: 0 in:
[]
[ERROR]   ADSearchServiceDevTest.findUser_caseInsensitiveMatch_returnsTestUser:145 
Expecting actual not to be null
[ERROR]   ADSearchServiceDevTest.findUser_notInLdap_returnsTestUser:133 
Expecting actual not to be null
[INFO]
[ERROR] Tests run: 292, Failures: 7, Errors: 0, Skipped: 11
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  30.453 s
[INFO] Finished at: 2026-03-25T21:52:24-04:00
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.5.3:test (default-test) on project ddd-services: There are test failures.
[ERROR]
[ERROR] See F:\project\ddd-services\target\surefire-reports for the individual test results.
[ERROR] See dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR]
