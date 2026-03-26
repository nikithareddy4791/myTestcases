// =========================================================================
// ADD THESE TESTS TO YOUR EXISTING CaseServiceTest.java
// Paste them before the last closing } of the class
// =========================================================================

    // =========================================================================
    // loadCase()
    // =========================================================================

    @Test
    @DisplayName("loadCase - throws CaseNotFoundException when case does not exist")
    void loadCase_caseNotFound_throwsCaseNotFoundException() {
        mockFindById_empty(999);

        assertThatThrownBy(() -> caseService.loadCase(999))
                .isInstanceOf(CaseNotFoundException.class)
                .hasMessageContaining("999");

        verify(caseRepository).findById(999);
        verifyNoInteractions(cdwRepository);
    }

    @Test
    @DisplayName("loadCase - sets activeFlg=1 when status is NOT_STARTED")
    void loadCase_statusNotStarted_setsActiveFlg1() {
        Status s = new Status();
        s.setId(AppConstants.STATUS_NOT_STARTED);
        dtoCase.setStatus(s);
        arrestInfo.setArrSealedFlg("N");

        mockFindById_found(100);
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);

        assertThat(caseService.loadCase(100).getActiveFlg()).isEqualTo(1);
    }

    @Test
    @DisplayName("loadCase - sets activeFlg=1 when status is IN_PROGRESS")
    void loadCase_statusInProgress_setsActiveFlg1() {
        Status s = new Status();
        s.setId(AppConstants.STATUS_IN_PROGRESS);
        dtoCase.setStatus(s);
        arrestInfo.setArrSealedFlg("N");

        mockFindById_found(100);
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);

        assertThat(caseService.loadCase(100).getActiveFlg()).isEqualTo(1);
    }

    @Test
    @DisplayName("loadCase - sets activeFlg=1 when status is WAITING")
    void loadCase_statusWaiting_setsActiveFlg1() {
        Status s = new Status();
        s.setId(AppConstants.STATUS_WAITING);
        dtoCase.setStatus(s);
        arrestInfo.setArrSealedFlg("N");

        mockFindById_found(100);
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);

        assertThat(caseService.loadCase(100).getActiveFlg()).isEqualTo(1);
    }

    @Test
    @DisplayName("loadCase - sets activeFlg=0 when status is COMPLETED")
    void loadCase_statusCompleted_setsActiveFlg0() {
        Status s = new Status();
        s.setId(AppConstants.STATUS_COMPLETED);
        dtoCase.setStatus(s);
        arrestInfo.setArrSealedFlg("N");

        mockFindById_found(100);
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);

        assertThat(caseService.loadCase(100).getActiveFlg()).isEqualTo(0);
    }

    @Test
    @DisplayName("loadCase - sets activeFlg=1 when status is null")
    void loadCase_statusNull_setsActiveFlg1() {
        dtoCase.setStatus(null);
        arrestInfo.setArrSealedFlg("N");

        mockFindById_found(100);
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);

        assertThat(caseService.loadCase(100).getActiveFlg()).isEqualTo(1);
    }

    @Test
    @DisplayName("loadCase - unsealed arrest loads without calling hasSealedAccess")
    void loadCase_unsealedArrest_loadsSuccessfully() {
        arrestInfo.setArrSealedFlg("N");
        dtoCase.setStatus(null);

        mockFindById_found(100);
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);

        assertThat(caseService.loadCase(100)).isNotNull();
        verify(authenticationService, never()).hasSealedAccess();
    }

    @Test
    @DisplayName("loadCase - sealed arrest with no sealed access throws SealedAccessException")
    void loadCase_sealedArrest_noSealedAccess_throwsSealedAccessException() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);

        mockFindById_found(100);
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(false);

        assertThatThrownBy(() -> caseService.loadCase(100))
                .isInstanceOf(SealedAccessException.class)
                .hasMessageContaining("no sealed access");
    }

    @Test
    @DisplayName("loadCase - sealed arrest, supervisor loads successfully")
    void loadCase_sealedArrest_supervisor_loadsSuccessfully() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);

        mockFindById_found(100);
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);
        when(authenticationService.isSupervisor()).thenReturn(true);

        assertThat(caseService.loadCase(100)).isNotNull();
    }

    @Test
    @DisplayName("loadCase - non-supervisor assigned to case loads successfully")
    void loadCase_nonSupervisor_assignedToCase_loadsSuccessfully() {
        arrestInfo.setArrSealedFlg("N");
        dtoCase.setStatus(null);
        entityCase.setAssignedNm("jdoe");

        mockFindById_found(100);
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.isSupervisor()).thenReturn(false);
        when(authenticationService.getUsername()).thenReturn("jdoe");

        assertThat(caseService.loadCase(100)).isNotNull();
        verify(authenticationService, never()).hasRole(any());
    }

    @Test
    @DisplayName("loadCase - non-supervisor not assigned, no office throws CaseAccessException")
    void loadCase_nonSupervisor_notAssigned_noOffice_throwsCaseAccessException() {
        arrestInfo.setArrSealedFlg("N");
        dtoCase.setStatus(null);
        entityCase.setAssignedNm("otherUser");

        mockFindById_found(100);
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.isSupervisor()).thenReturn(false);
        when(authenticationService.getUsername()).thenReturn("jdoe");

        assertThatThrownBy(() -> caseService.loadCase(100))
                .isInstanceOf(CaseAccessException.class)
                .hasMessageContaining("no access to case");
    }

    @Test
    @DisplayName("loadCase - populates assignedNmInfo when assignedNm is set")
    void loadCase_withAssignedNm_populatesAssignedNmInfo() {
        entityCase.setAssignedNm("jdoe");
        dtoCase.setStatus(null);
        arrestInfo.setArrSealedFlg("N");

        User mockUser = new User();
        mockUser.setUsername("jdoe");

        mockFindById_found(100);
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(adSearchService.findUser("jdoe")).thenReturn(mockUser);

        org.nnnn.ddd.model.dddCase result = caseService.loadCase(100);

        assertThat(result.getAssignedNmInfo()).isNotNull();
        verify(adSearchService).findUser("jdoe");
    }

    @Test
    @DisplayName("loadCase - skips AD lookup when assignedNm is null")
    void loadCase_noAssignedNm_skipsAdLookup() {
        entityCase.setAssignedNm(null);
        dtoCase.setStatus(null);
        arrestInfo.setArrSealedFlg("N");

        mockFindById_found(100);
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);

        caseService.loadCase(100);

        verifyNoInteractions(adSearchService);
    }

    @Test
    @DisplayName("loadCase - arrest info is attached to returned DTO")
    void loadCase_arrestInfoAttachedToDto() {
        dtoCase.setStatus(null);
        arrestInfo.setArrSealedFlg("N");
        arrestInfo.setDeftFrstNm("Jane");
        arrestInfo.setDeftLastNm("Smith");

        mockFindById_found(100);
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);

        org.nnnn.ddd.model.dddCase result = caseService.loadCase(100);

        assertThat(result.getArrest()).isNotNull();
        assertThat(result.getArrest().getDeftFrstNm()).isEqualTo("Jane");
        verify(cdwRepository).getArrestInfo("ARR001");
    }
