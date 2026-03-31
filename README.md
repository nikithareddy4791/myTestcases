[ERROR] Failures: 
[ERROR]   SecurityConfigTest$JwtAuthenticationConverterTests.convert_allBoroughGroups_mapCorrectly:151 
Expecting ArrayList:
  []
to contain:
  ["ROLE_SG-DDD-ANALYST-BROOKLYN",
    "ROLE_SG-DDD-ANALYST-BRONX",
    "ROLE_SG-DDD-ANALYST-QUEENS",
    "ROLE_SG-DDD-ANALYST-SI",
    "ROLE_SG-DDD-ANALYST-MANHATTAN"]
but could not find the following element(s):
  ["ROLE_SG-DDD-ANALYST-BROOKLYN",
    "ROLE_SG-DDD-ANALYST-BRONX",
    "ROLE_SG-DDD-ANALYST-QUEENS",
    "ROLE_SG-DDD-ANALYST-SI",
    "ROLE_SG-DDD-ANALYST-MANHATTAN"]

[ERROR]   SecurityConfigTest$JwtAuthenticationConverterTests.convert_authorityCountMatchesGroupCount:172 
Expected size: 3 but was: 0 in:
[]
[ERROR]   SecurityConfigTest$JwtAuthenticationConverterTests.convert_groupWithLeadingSlash_removesSlash:77 
Expecting ArrayList:
  []
to contain:
  ["ROLE_SG-DDD-ANALYST-BRONX"]
but could not find the following element(s):
  ["ROLE_SG-DDD-ANALYST-BRONX"]

[ERROR]   SecurityConfigTest$JwtAuthenticationConverterTests.convert_lowercaseGroup_isUppercased:89 
Expecting ArrayList:
  []
to contain:
  ["ROLE_SG-DDD-ANALYST-QUEENS"]
but could not find the following element(s):
  ["ROLE_SG-DDD-ANALYST-QUEENS"]

[ERROR]   SecurityConfigTest$JwtAuthenticationConverterTests.convert_multipleGroups_mapsAllToRoleAuthorities:65 
Expecting ArrayList:
  []
to contain:
  ["ROLE_SG-DDD-ANALYST-BROOKLYN", "ROLE_SG-DDD-SUPERVISOR"]
but could not find the following element(s):
  ["ROLE_SG-DDD-ANALYST-BROOKLYN", "ROLE_SG-DDD-SUPERVISOR"]

[ERROR]   SecurityConfigTest$JwtAuthenticationConverterTests.convert_sealedEventGroup_mapsToSealedRole:133 
Expecting ArrayList:
  []
to contain:
  ["ROLE_SG-SEALED-EVENT"]
but could not find the following element(s):
  ["ROLE_SG-SEALED-EVENT"]

[ERROR]   SecurityConfigTest$JwtAuthenticationConverterTests.convert_singleGroup_mapsToRoleAuthority:53 
Expecting ArrayList:
  []
to contain:
  ["ROLE_SG-DDD-ANALYST-MANHATTAN"]
but could not find the following element(s):
  ["ROLE_SG-DDD-ANALYST-MANHATTAN"]

[ERROR]   SecurityConfigTest$JwtAuthenticationConverterTests.convert_supervisorGroup_mapsToSupervisorRole:121 
Expecting ArrayList:
  []
to contain:
  ["ROLE_SG-DDD-SUPERVISOR"]
but could not find the following element(s):
  ["ROLE_SG-DDD-SUPERVISOR"]

[INFO] 
[ERROR] Tests
