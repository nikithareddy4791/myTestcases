------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running org.nnnn.ddd.SecurityConfigTest
[ERROR] Tests run: 10, Failures: 5, Errors: 0, Skipped: 0, Time elapsed: 0.461 s <<< FAILURE! -- in org.nnnn.ddd.SecurityConfigTest
[ERROR] org.nnnn.ddd.SecurityConfigTest.convert_mapsMultipleGroupsToMultipleRoles -- Time elapsed: 0.015 s <<< FAILURE!
java.lang.AssertionError:

Expected size: 4 but was: 0 in:
[]
        at org.nnnn.ddd.SecurityConfigTest.convert_mapsMultipleGroupsToMultipleRoles(SecurityConfigTest.java:112)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.SecurityConfigTest.convert_allAnalystGroupsConvertCorrectly -- Time elapsed: 0.002 s <<< FAILURE!
java.lang.AssertionError:

Expected size: 7 but was: 0 in:
[]
        at org.nnnn.ddd.SecurityConfigTest.convert_allAnalystGroupsConvertCorrectly(SecurityConfigTest.java:165)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.SecurityConfigTest.convert_mapsGroupsToRoleAuthorities -- Time elapsed: 0.008 s <<< FAILURE!
java.lang.AssertionError:

Expecting any elements of:
  []
to match given predicate but none did.
        at org.nnnn.ddd.SecurityConfigTest.convert_mapsGroupsToRoleAuthorities(SecurityConfigTest.java:58)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.SecurityConfigTest.convert_sealedEventGroupConvertsCorrectly -- Time elapsed: 0.003 s <<< FAILURE!
java.lang.AssertionError:

Expecting any elements of:
  []
to match given predicate but none did.
        at org.nnnn.ddd.SecurityConfigTest.convert_sealedEventGroupConvertsCorrectly(SecurityConfigTest.java:147)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[ERROR] org.nnnn.ddd.SecurityConfigTest.convert_singleGroupProducesSingleRole -- Time elapsed: 0.001 s <<< FAILURE!
java.lang.AssertionError:

Expected size: 1 but was: 0 in:
[]
        at org.nnnn.ddd.SecurityConfigTest.convert_singleGroupProducesSingleRole(SecurityConfigTest.java:133)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)

[INFO] 
[INFO] Results:
[INFO]
[ERROR] Failures: 
[ERROR]   SecurityConfigTest.convert_allAnalystGroupsConvertCorrectly:165 
Expected size: 7 but was: 0 in:
[]
[ERROR]   SecurityConfigTest.convert_mapsGroupsToRoleAuthorities:58 
Expecting any elements of:
  []
to match given predicate but none did.
[ERROR]   SecurityConfigTest.convert_mapsMultipleGroupsToMultipleRoles:112 
Expected size: 4 but was: 0 in:
[]
[ERROR]   SecurityConfigTest.convert_sealedEventGroupConvertsCorrectly:147 
Expecting any elements of:
  []
to match given predicate but none did.
[ERROR]   SecurityConfigTest.convert_singleGroupProducesSingleRole:133 
Expected size: 1 but was: 0 in:
[]
[INFO]
[ERROR] Tests run: 10, Failures: 5, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILUR
