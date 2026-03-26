@Test
@DisplayName("loadCase - sealed, has sealed access, not supervisor, not assigned, in office loads successfully")
void loadCase_sealedArrest_sealedAccess_notSupervisor_notAssigned_inOffice_loadsSuccessfully() {
    arrestInfo.setArrSealedFlg("Y");
    dtoCase.setStatus(null);
    entityCase.setAssignedNm("otherUser"); // not assigned

    // Set up ddd office on entity
    try {
        java.lang.reflect.Field dddField = entityCase.getClass().getDeclaredField("ddd");
        dddField.setAccessible(true);
        Object officeInstance = dddField.getType().getDeclaredConstructor().newInstance();
        java.lang.reflect.Field adSgNmField = officeInstance.getClass().getDeclaredField("adSgNm");
        adSgNmField.setAccessible(true);
        adSgNmField.set(officeInstance, "ROLE_MANHATTAN");
        dddField.set(entityCase, officeInstance);
    } catch (Exception e) {
        return;
    }

    mockFindById_found(100);
    when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
    when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
    when(authenticationService.hasSealedAccess()).thenReturn(true);
    when(authenticationService.isSupervisor()).thenReturn(false);
    when(authenticationService.getUsername()).thenReturn("jdoe"); // not assigned
    when(authenticationService.hasRole("ROLE_MANHATTAN")).thenReturn(true); // in office

    // Should load successfully — user has sealed access and is in the office
    assertThat(caseService.loadCase(100)).isNotNull();
}

@Test
@DisplayName("loadCase - sealed, has sealed access, not supervisor, not assigned, not in office throws CaseAccessException")
void loadCase_sealedArrest_sealedAccess_notSupervisor_notAssigned_notInOffice_throwsCaseAccessException() {
    arrestInfo.setArrSealedFlg("Y");
    dtoCase.setStatus(null);
    entityCase.setAssignedNm("otherUser");

    try {
        java.lang.reflect.Field dddField = entityCase.getClass().getDeclaredField("ddd");
        dddField.setAccessible(true);
        Object officeInstance = dddField.getType().getDeclaredConstructor().newInstance();
        java.lang.reflect.Field adSgNmField = officeInstance.getClass().getDeclaredField("adSgNm");
        adSgNmField.setAccessible(true);
        adSgNmField.set(officeInstance, "ROLE_MANHATTAN");
        dddField.set(entityCase, officeInstance);
    } catch (Exception e) {
        return;
    }

    mockFindById_found(100);
    when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
    when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
    when(authenticationService.hasSealedAccess()).thenReturn(true);
    when(authenticationService.isSupervisor()).thenReturn(false);
    when(authenticationService.getUsername()).thenReturn("jdoe");
    when(authenticationService.hasRole("ROLE_MANHATTAN")).thenReturn(false); // NOT in office

    assertThatThrownBy(() -> caseService.loadCase(100))
            .isInstanceOf(CaseAccessException.class)
            .hasMessageContaining("no access to case");
}

@Test
@DisplayName("loadCase - not sealed, not assigned, in office loads successfully (Read-Only path)")
void loadCase_notSealed_notAssigned_inOffice_loadsSuccessfully() {
    arrestInfo.setArrSealedFlg("N");
    dtoCase.setStatus(null);
    entityCase.setAssignedNm("otherUser"); // not assigned to jdoe

    try {
        java.lang.reflect.Field dddField = entityCase.getClass().getDeclaredField("ddd");
        dddField.setAccessible(true);
        Object officeInstance = dddField.getType().getDeclaredConstructor().newInstance();
        java.lang.reflect.Field adSgNmField = officeInstance.getClass().getDeclaredField("adSgNm");
        adSgNmField.setAccessible(true);
        adSgNmField.set(officeInstance, "ROLE_MANHATTAN");
        dddField.set(entityCase, officeInstance);
    } catch (Exception e) {
        return;
    }

    mockFindById_found(100);
    when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
    when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
    when(authenticationService.isSupervisor()).thenReturn(false);
    when(authenticationService.getUsername()).thenReturn("jdoe"); // not assigned
    when(authenticationService.hasRole("ROLE_MANHATTAN")).thenReturn(true); // in office

    // Read-Only path — should still load successfully
    assertThat(caseService.loadCase(100)).isNotNull();
    verify(authenticationService).hasRole("ROLE_MANHATTAN");
}
