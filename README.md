
[ERROR] Failures: 
[ERROR]   MapperConfigTest.dddCase_skipsAdaMapping:170 
expected: null
 but was: class ADA {
    id: 1
    jobTitleDesc: null
    frstNm: null
    lastNm: null
    emailAddrDesc: null
    boroughNm: null
    busPhoneNum: null
    cellPhoneNum: null
    faxNum: null
    noteDesc: null
    archiveFlg: null
    inactiveFlg: 0
}
[ERROR]   MapperConfigTest.dddCase_skipsCategoryMapping:184 
expected: null
 but was: class Category {
    id: 1
    categoryDesc: null
    inactiveFlg: 0
}
[ERROR]   MapperConfigTest.dddCase_skipsStatusMapping:198 
expected: null
 but was: class Status {
    id: 1
    statusDesc: null
    inactiveFlg: 0
}
[ERROR]   SecurityConfigTest.converter_mapsMultipleGroupsToMultipleRoles:135 
Expected size: 4 but was: 0 in:
[]
[ERROR]   SecurityConfigTest.converter_mapsnnnnGroupsToRoleAuthorities:54 
Expecting ListN:
  []
to contain:
  ["ROLE_SG-ddd-SUPERVISOR"]
but could not find the following element(s):
  ["ROLE_SG-ddd-SUPERVISOR"]

[ERROR]   SecurityConfigTest.converter_removesLeadingSlash:73 
Expecting any elements of:
  []
to match given predicate but none did.
[ERROR]   SecurityConfigTest.converter_uppercasesGroupNames:91 
Expecting ListN:
  []
to contain:
  ["ROLE_SG-ddd-SUPERVISOR"]
but could not find the following element(s):
  ["ROLE_SG-ddd-SUPERVISOR"]

[INFO]
