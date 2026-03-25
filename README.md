package org.nnnn.ddd.service;

// ADD THESE TESTS TO YOUR EXISTING CaseServiceTest.java
// Place them before the closing } of the class

    // =========================================================================
    // saveCase() — additional branch coverage
    // =========================================================================

    @Test
    @DisplayName("saveCase - updates ADA when provided")
    void saveCase_withAda_updatesAda() {
        dtoCase.setId(100);
        org.nnnn.ddd.model.ADA ada = new org.nnnn.ddd.model.ADA();
        ada.setId(1);
        dtoCase.setAda(ada);

        AdaList adaEntity = new AdaList();
        adaEntity.setId(1);

        mockFindById_found(100);
        when(adaListRepository.findById(1)).thenReturn(Optional.of(adaEntity));

        caseService.saveCase(dtoCase);

        verify(adaListRepository).findById(1);
        assertThat(entityCase.getAda()).isEqualTo(adaEntity);
    }

    @Test
    @DisplayName("saveCase - clears ADA when null")
    void saveCase_nullAda_clearsAda() {
        dtoCase.setId(100);
        dtoCase.setAda(null);

        AdaList existingAda = new AdaList();
        entityCase.setAda(existingAda);

        mockFindById_found(100);

        caseService.saveCase(dtoCase);

        assertThat(entityCase.getAda()).isNull();
    }

    @Test
    @DisplayName("saveCase - updates ddd office when provided")
    void saveCase_withDddOffice_updatesDddOffice() {
        dtoCase.setId(100);
        org.nnnn.ddd.model.dddOffice office = new org.nnnn.ddd.model.dddOffice();
        office.setId(1);
        dtoCase.setddd(office);

        dddOfficeList officeEntity = new dddOfficeList();
        officeEntity.setId(1);

        mockFindById_found(100);
        when(dddOfficeListRepository.findById(1)).thenReturn(Optional.of(officeEntity));

        caseService.saveCase(dtoCase);

        verify(dddOfficeListRepository).findById(1);
        assertThat(entityCase.getddd()).isEqualTo(officeEntity);
    }

    @Test
    @DisplayName("saveCase - clears ddd office when null")
    void saveCase_nullDddOffice_clearsDddOffice() {
        dtoCase.setId(100);
        dtoCase.setddd(null);

        dddOfficeList existingOffice = new dddOfficeList();
        entityCase.setddd(existingOffice);

        mockFindById_found(100);

        caseService.saveCase(dtoCase);

        assertThat(entityCase.getddd()).isNull();
    }

    @Test
    @DisplayName("saveCase - adds new tag when tag not already on case")
    void saveCase_withNewTag_addsTag() {
        dtoCase.setId(100);
        org.nnnn.ddd.model.CaseTag dtoTag = new org.nnnn.ddd.model.CaseTag();
        dtoTag.setTagId(1);
        dtoCase.setTags(List.of(dtoTag));

        TagList tagEntity = new TagList();
        tagEntity.setId(1);

        mockFindById_found(100);
        when(tagListRepository.findById(1)).thenReturn(Optional.of(tagEntity));

        caseService.saveCase(dtoCase);

        verify(tagListRepository).findById(1);
        assertThat(entityCase.getTags()).hasSize(1);
    }

    @Test
    @DisplayName("saveCase - skips tag when tagId is null")
    void saveCase_tagWithNullTagId_skipsTag() {
        dtoCase.setId(100);
        org.nnnn.ddd.model.CaseTag dtoTag = new org.nnnn.ddd.model.CaseTag();
        dtoTag.setTagId(null); // null tagId should be skipped
        dtoCase.setTags(List.of(dtoTag));

        mockFindById_found(100);

        caseService.saveCase(dtoCase);

        verify(tagListRepository, never()).findById(any());
        assertThat(entityCase.getTags()).isEmpty();
    }

    @Test
    @DisplayName("saveCase - adds new item when item not already on case")
    void saveCase_withNewItem_addsItem() {
        dtoCase.setId(100);
        org.nnnn.ddd.model.CaseItem dtoItem = new org.nnnn.ddd.model.CaseItem();
        dtoItem.setItemId(1);
        dtoItem.setNoteDesc("test note");
        dtoItem.setQuantity(2);
        dtoCase.setItems(List.of(dtoItem));

        ItemList itemEntity = new ItemList();
        itemEntity.setId(1);

        mockFindById_found(100);
        when(itemListRepository.findById(1)).thenReturn(Optional.of(itemEntity));

        caseService.saveCase(dtoCase);

        verify(itemListRepository).findById(1);
        assertThat(entityCase.getItems()).hasSize(1);
    }

    @Test
    @DisplayName("saveCase - updates existing item fields")
    void saveCase_withExistingItem_updatesFields() {
        dtoCase.setId(100);

        // Create existing item on the entity
        CaseItem existingItem = new CaseItem();
        existingItem.setId(1);
        existingItem.setNoteDesc("old note");
        entityCase.getItems().add(existingItem);

        org.nnnn.ddd.model.CaseItem dtoItem = new org.nnnn.ddd.model.CaseItem();
        dtoItem.setId(1); // matches existing item
        dtoItem.setItemId(1);
        dtoItem.setNoteDesc("new note");
        dtoItem.setQuantity(5);
        dtoItem.setStatusDesc("completed");
        dtoCase.setItems(List.of(dtoItem));

        mockFindById_found(100);

        caseService.saveCase(dtoCase);

        assertThat(existingItem.getNoteDesc()).isEqualTo("new note");
        assertThat(existingItem.getQuantity()).isEqualTo(5);
        assertThat(existingItem.getStatusDesc()).isEqualTo("completed");
    }

    // =========================================================================
    // findCases() — additional filter branch coverage
    // =========================================================================

    @Test
    @DisplayName("findCases - filters by date range")
    void findCases_withDateRange_appliesDateFilter() {
        CaseFilter filter = new CaseFilter();
        filter.setRequestDtFrom("2024-01-01");
        filter.setRequestDtTo("2024-12-31");
        filter.setPageSize(10);
        filter.setPageNumber(0);

        Page<org.nnnn.ddd.entity.dddCase> page = new PageImpl<>(Collections.emptyList());
        when(caseRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(cdwRepository.getArrestInfoSummary(anyList())).thenReturn(Collections.emptyList());
        when(caseRepository.countByDueDtBeforeAndStatus_IdNot(any(), anyInt())).thenReturn(0);
        when(caseRepository.countByDueDtBetweenAndStatus_IdNot(any(), any(), anyInt())).thenReturn(0);

        caseService.findCases(filter);

        verify(caseRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    @DisplayName("findCases - filters by due date range")
    void findCases_withDueDateRange_appliesDueDateFilter() {
        CaseFilter filter = new CaseFilter();
        filter.setDueDtFrom("2024-01-01");
        filter.setDueDtTo("2024-12-31");
        filter.setPageSize(10);
        filter.setPageNumber(0);

        Page<org.nnnn.ddd.entity.dddCase> page = new PageImpl<>(Collections.emptyList());
        when(caseRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(cdwRepository.getArrestInfoSummary(anyList())).thenReturn(Collections.emptyList());
        when(caseRepository.countByDueDtBeforeAndStatus_IdNot(any(), anyInt())).thenReturn(0);
        when(caseRepository.countByDueDtBetweenAndStatus_IdNot(any(), any(), anyInt())).thenReturn(0);

        caseService.findCases(filter);

        verify(caseRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    @DisplayName("findCases - filters by categoryIds")
    void findCases_withCategoryIds_appliesCategoryFilter() {
        CaseFilter filter = new CaseFilter();
        filter.setCategoryIds(Arrays.asList(1, 2));
        filter.setPageSize(10);
        filter.setPageNumber(0);

        Page<org.nnnn.ddd.entity.dddCase> page = new PageImpl<>(Collections.emptyList());
        when(caseRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(cdwRepository.getArrestInfoSummary(anyList())).thenReturn(Collections.emptyList());
        when(caseRepository.countByDueDtBeforeAndStatus_IdNot(any(), anyInt())).thenReturn(0);
        when(caseRepository.countByDueDtBetweenAndStatus_IdNot(any(), any(), anyInt())).thenReturn(0);

        caseService.findCases(filter);

        verify(caseRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    @DisplayName("findCases - filters by statusIds")
    void findCases_withStatusIds_appliesStatusFilter() {
        CaseFilter filter = new CaseFilter();
        filter.setStatusIds(Arrays.asList(1, 2));
        filter.setPageSize(10);
        filter.setPageNumber(0);

        Page<org.nnnn.ddd.entity.dddCase> page = new PageImpl<>(Collections.emptyList());
        when(caseRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(cdwRepository.getArrestInfoSummary(anyList())).thenReturn(Collections.emptyList());
        when(caseRepository.countByDueDtBeforeAndStatus_IdNot(any(), anyInt())).thenReturn(0);
        when(caseRepository.countByDueDtBetweenAndStatus_IdNot(any(), any(), anyInt())).thenReturn(0);

        caseService.findCases(filter);

        verify(caseRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    @DisplayName("findCases - filters by tagIds")
    void findCases_withTagIds_appliesTagFilter() {
        CaseFilter filter = new CaseFilter();
        filter.setTagIds(Arrays.asList(1, 2));
        filter.setPageSize(10);
        filter.setPageNumber(0);

        Page<org.nnnn.ddd.entity.dddCase> page = new PageImpl<>(Collections.emptyList());
        when(caseRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(cdwRepository.getArrestInfoSummary(anyList())).thenReturn(Collections.emptyList());
        when(caseRepository.countByDueDtBeforeAndStatus_IdNot(any(), anyInt())).thenReturn(0);
        when(caseRepository.countByDueDtBetweenAndStatus_IdNot(any(), any(), anyInt())).thenReturn(0);

        caseService.findCases(filter);

        verify(caseRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    @DisplayName("findCases - filters by assignedNm")
    void findCases_withAssignedNm_appliesAssignedNmFilter() {
        CaseFilter filter = new CaseFilter();
        filter.setAssignedNm("jdoe");
        filter.setPageSize(10);
        filter.setPageNumber(0);

        Page<org.nnnn.ddd.entity.dddCase> page = new PageImpl<>(Collections.emptyList());
        when(caseRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(cdwRepository.getArrestInfoSummary(anyList())).thenReturn(Collections.emptyList());
        when(caseRepository.countByDueDtBeforeAndStatus_IdNot(any(), anyInt())).thenReturn(0);
        when(caseRepository.countByDueDtBetweenAndStatus_IdNot(any(), any(), anyInt())).thenReturn(0);

        caseService.findCases(filter);

        verify(caseRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    @DisplayName("findCases - filters by proactiveFlg")
    void findCases_withProactiveFlg_appliesProactiveFilter() {
        CaseFilter filter = new CaseFilter();
        filter.setProactiveFlg(1);
        filter.setPageSize(10);
        filter.setPageNumber(0);

        Page<org.nnnn.ddd.entity.dddCase> page = new PageImpl<>(Collections.emptyList());
        when(caseRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(cdwRepository.getArrestInfoSummary(anyList())).thenReturn(Collections.emptyList());
        when(caseRepository.countByDueDtBeforeAndStatus_IdNot(any(), anyInt())).thenReturn(0);
        when(caseRepository.countByDueDtBetweenAndStatus_IdNot(any(), any(), anyInt())).thenReturn(0);

        caseService.findCases(filter);

        verify(caseRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    @DisplayName("findCases - supervisor with dddOfficeIds gets office-specific stats")
    void findCases_supervisorWithOfficeIds_getsOfficeStats() {
        CaseFilter filter = new CaseFilter();
        filter.setArrId("ARR001");
        filter.setdddOfficeIds(Arrays.asList(1, 2));
        filter.setPageSize(10);
        filter.setPageNumber(0);

        when(authenticationService.isSupervisor()).thenReturn(true);

        Page<org.nnnn.ddd.entity.dddCase> page = new PageImpl<>(Collections.emptyList());
        when(caseRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(cdwRepository.getArrestInfoSummary(anyList())).thenReturn(Collections.emptyList());

        // Stats with office ids
        when(caseRepository.countByDueDtBeforeAndddd_IdInAndStatus_IdNot(any(), any(), anyInt())).thenReturn(2);
        when(caseRepository.countByDueDtBetweenAndddd_IdInAndStatus_IdNot(any(), any(), any(), anyInt())).thenReturn(1);

        CaseListResponse result = caseService.findCases(filter);

        assertThat(result.getCaseStats()).isNotNull();
        assertThat(result.getCaseStats().getOverdueCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("findCases - ascending sort order applied correctly")
    void findCases_withAscendingSortOrder_appliesAscSort() {
        CaseFilter filter = new CaseFilter();
        filter.setArrId("ARR001");
        filter.setPageSize(10);
        filter.setPageNumber(0);
        filter.setSortBy(List.of("dueDt"));
        filter.setSortOrder(List.of(0)); // 0 = ascending

        Page<org.nnnn.ddd.entity.dddCase> page = new PageImpl<>(Collections.emptyList());
        when(caseRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(cdwRepository.getArrestInfoSummary(anyList())).thenReturn(Collections.emptyList());
        when(caseRepository.countByDueDtBeforeAndStatus_IdNot(any(), anyInt())).thenReturn(0);
        when(caseRepository.countByDueDtBetweenAndStatus_IdNot(any(), any(), anyInt())).thenReturn(0);

        caseService.findCases(filter);

        verify(caseRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    @DisplayName("findCases - multiple sort fields applied")
    void findCases_withMultipleSortFields_appliesMultiSort() {
        CaseFilter filter = new CaseFilter();
        filter.setArrId("ARR001");
        filter.setPageSize(10);
        filter.setPageNumber(0);
        filter.setSortBy(List.of("dueDt", "requestDt"));
        filter.setSortOrder(List.of(1, 0)); // desc, asc

        Page<org.nnnn.ddd.entity.dddCase> page = new PageImpl<>(Collections.emptyList());
        when(caseRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(cdwRepository.getArrestInfoSummary(anyList())).thenReturn(Collections.emptyList());
        when(caseRepository.countByDueDtBeforeAndStatus_IdNot(any(), anyInt())).thenReturn(0);
        when(caseRepository.countByDueDtBetweenAndStatus_IdNot(any(), any(), anyInt())).thenReturn(0);

        caseService.findCases(filter);

        verify(caseRepository).findAll(any(Specification.class), any(Pageable.class));
    }
