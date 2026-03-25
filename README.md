package org.nnnn.ddd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.nnnn.ddd.AppConstants;
import org.nnnn.ddd.entity.*;
import org.nnnn.ddd.exceptions.*;
import org.nnnn.ddd.model.*;
import org.nnnn.ddd.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("CaseService Tests")
class CaseServiceTest {

    @Mock private CaseRepository caseRepository;
    @Mock private CDWRepository cdwRepository;
    @Mock private AuthenticationService authenticationService;
    @Mock private ADSearchService adSearchService;
    @Mock private ModelMapper modelMapper;
    @Mock private dddOfficeListRepository dddOfficeListRepository;
    @Mock private StatusListRepository statusListRepository;
    @Mock private CategoryListRepository categoryListRepository;
    @Mock private ADAListRepository adaListRepository;
    @Mock private TagListRepository tagListRepository;
    @Mock private ItemListRepository itemListRepository;
    @Mock private CaseUploadRepository caseUploadRepository;
    @Mock private ReferenceDataService referenceDataService;
    @Mock private DellS3Service s3Service;

    @InjectMocks
    private CaseService caseService;

    private org.nnnn.ddd.entity.dddCase entityCase;
    private org.nnnn.ddd.model.dddCase dtoCase;
    private ArrestInfo arrestInfo;

    @BeforeEach
    void setUp() {
        entityCase = new org.nnnn.ddd.entity.dddCase();
        entityCase.setId(100);
        entityCase.setArrId("ARR001");
        entityCase.setAssignedNm("jdoe");

        dtoCase = new org.nnnn.ddd.model.dddCase();
        dtoCase.setId(100);
        dtoCase.setArrId("ARR001");

        arrestInfo = new ArrestInfo();
        arrestInfo.setArrId("ARR001");
        arrestInfo.setArrSealedFlg("N");
        arrestInfo.setDvFlg(0);
        arrestInfo.setFelonyFlg(0);
        arrestInfo.setIndexCrimeFlg(0);

        lenient().when(authenticationService.getUsername()).thenReturn("jdoe");
        lenient().when(authenticationService.isSupervisor()).thenReturn(true);
        lenient().when(authenticationService.hasSealedAccess()).thenReturn(true);
    }

    // FIX: Use Integer (not int) to match saveCase/saveNote which call findById(Integer)
    // Also use both int and Integer variants to cover all callers
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void mockFindById_found(int id) {
        java.util.Optional opt = java.util.Optional.of(entityCase);
        doReturn(opt).when(caseRepository).findById(id);           // int variant (loadCase)
        doReturn(opt).when(caseRepository).findById((Integer) id); // Integer variant (saveCase/saveNote)
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void mockFindById_empty(int id) {
        java.util.Optional opt = java.util.Optional.empty();
        doReturn(opt).when(caseRepository).findById(id);
        doReturn(opt).when(caseRepository).findById((Integer) id);
    }

    // =========================================================================
    // getCaseStats() — no args
    // =========================================================================

    @Test
    @DisplayName("getCaseStats - supervisor gets all case stats")
    void getCaseStats_supervisor_returnsAllStats() {
        when(authenticationService.isSupervisor()).thenReturn(true);
        when(caseRepository.countByDueDtBeforeAndStatus_IdNot(any(), anyInt())).thenReturn(3);
        when(caseRepository.countByDueDtBetweenAndStatus_IdNot(any(), any(), anyInt())).thenReturn(2);

        dddCaseStats stats = caseService.getCaseStats();

        assertThat(stats.getOverdueCount()).isEqualTo(3);
        assertThat(stats.getComingDueCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("getCaseStats - non-supervisor gets own stats only")
    void getCaseStats_nonSupervisor_returnsOwnStats() {
        when(authenticationService.isSupervisor()).thenReturn(false);
        when(authenticationService.getUsername()).thenReturn("jdoe");
        when(caseRepository.countByDueDtBeforeAndAssignedNmAndStatus_IdNot(any(), eq("jdoe"), anyInt())).thenReturn(1);
        when(caseRepository.countByDueDtBetweenAndAssignedNmAndStatus_IdNot(any(), any(), eq("jdoe"), anyInt())).thenReturn(4);

        dddCaseStats stats = caseService.getCaseStats();

        assertThat(stats.getOverdueCount()).isEqualTo(1);
        assertThat(stats.getComingDueCount()).isEqualTo(4);
    }

    // =========================================================================
    // getCaseStats(Collection<Integer>)
    // =========================================================================

    @Test
    @DisplayName("getCaseStats(officeIds) - returns stats for given offices")
    void getCaseStats_withOfficeIds_returnsOfficeStats() {
        List<Integer> officeIds = Arrays.asList(1, 2);
        when(caseRepository.countByDueDtBeforeAndddd_IdInAndStatus_IdNot(any(), eq(officeIds), anyInt())).thenReturn(5);
        when(caseRepository.countByDueDtBetweenAndddd_IdInAndStatus_IdNot(any(), any(), eq(officeIds), anyInt())).thenReturn(3);

        dddCaseStats stats = caseService.getCaseStats(officeIds);

        assertThat(stats.getOverdueCount()).isEqualTo(5);
        assertThat(stats.getComingDueCount()).isEqualTo(3);
    }

    // =========================================================================
    // createCase()
    // =========================================================================

    @Test
    @DisplayName("createCase - creates new case successfully")
    void createCase_newCase_returnsNewCaseDto() {
        CreateCaseRequest request = new CreateCaseRequest();
        request.setArrId("ARR001");
        request.setdddOfficeId(1);
        request.setProactiveFlg(0);

        dddOfficeList office = new dddOfficeList();
        office.setId(1);
        StatusList notStarted = new StatusList();
        notStarted.setId(AppConstants.STATUS_NOT_STARTED);

        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(caseRepository.findByArrIdOrderByIdDesc("ARR001")).thenReturn(Collections.emptyList());
        when(dddOfficeListRepository.findById(1)).thenReturn(Optional.of(office));
        when(statusListRepository.findById(AppConstants.STATUS_NOT_STARTED)).thenReturn(Optional.of(notStarted));
        when(caseRepository.save(any())).thenAnswer(inv -> {
            org.nnnn.ddd.entity.dddCase c = inv.getArgument(0);
            c.setId(101);
            return c;
        });

        org.nnnn.ddd.model.dddCase result = caseService.createCase(request);

        assertThat(result.isNew()).isTrue();
        verify(caseRepository).save(any());
    }

    @Test
    @DisplayName("createCase - existing case returns existing case DTO")
    void createCase_existingCase_returnsExistingDto() {
        CreateCaseRequest request = new CreateCaseRequest();
        request.setArrId("ARR001");
        request.setdddOfficeId(1);
        request.setProactiveFlg(0);

        StatusList status = new StatusList();
        status.setId(AppConstants.STATUS_IN_PROGRESS);
        entityCase.setStatus(status);

        Status modelStatus = new Status();
        modelStatus.setId(AppConstants.STATUS_IN_PROGRESS);

        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(caseRepository.findByArrIdOrderByIdDesc("ARR001")).thenReturn(List.of(entityCase));
        when(modelMapper.map(any(StatusList.class), eq(Status.class))).thenReturn(modelStatus);

        org.nnnn.ddd.model.dddCase result = caseService.createCase(request);

        assertThat(result.getId()).isEqualTo(100);
        verify(caseRepository, never()).save(any());
    }

    @Test
    @DisplayName("createCase - sealed arrest with no sealed access throws SealedAccessException")
    void createCase_sealedArrest_noAccess_throwsSealedAccessException() {
        CreateCaseRequest request = new CreateCaseRequest();
        request.setArrId("ARR001");
        request.setdddOfficeId(1);
        request.setProactiveFlg(0);

        arrestInfo.setArrSealedFlg("Y");
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(false);

        assertThatThrownBy(() -> caseService.createCase(request))
                .isInstanceOf(SealedAccessException.class);
    }

    @Test
    @DisplayName("createCase - invalid arrest throws InvalidArrestException")
    void createCase_invalidArrest_throwsInvalidArrestException() {
        CreateCaseRequest request = new CreateCaseRequest();
        request.setArrId("INVALID");
        request.setdddOfficeId(1);
        request.setProactiveFlg(0);

        when(cdwRepository.getArrestInfo("INVALID")).thenThrow(new RuntimeException("Not found"));

        assertThatThrownBy(() -> caseService.createCase(request))
                .isInstanceOf(InvalidArrestException.class)
                .hasMessageContaining("INVALID");
    }

    @Test
    @DisplayName("createCase - invalid ddd office throws InvaliddddOfficeException")
    void createCase_invalidOffice_throwsInvaliddddOfficeException() {
        CreateCaseRequest request = new CreateCaseRequest();
        request.setArrId("ARR001");
        request.setdddOfficeId(999);
        request.setProactiveFlg(0);

        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(caseRepository.findByArrIdOrderByIdDesc("ARR001")).thenReturn(Collections.emptyList());
        when(dddOfficeListRepository.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> caseService.createCase(request))
                .isInstanceOf(InvaliddddOfficeException.class);
    }

    @Test
    @DisplayName("createCase - with requestDt and dueDt sets dates on entity")
    void createCase_withDates_setsDatesOnEntity() {
        CreateCaseRequest request = new CreateCaseRequest();
        request.setArrId("ARR001");
        request.setdddOfficeId(1);
        request.setProactiveFlg(0);
        request.setRequestDt(LocalDate.now());
        request.setDueDt(LocalDate.now().plusDays(30));

        dddOfficeList office = new dddOfficeList();
        StatusList notStarted = new StatusList();

        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(caseRepository.findByArrIdOrderByIdDesc("ARR001")).thenReturn(Collections.emptyList());
        when(dddOfficeListRepository.findById(1)).thenReturn(Optional.of(office));
        when(statusListRepository.findById(AppConstants.STATUS_NOT_STARTED)).thenReturn(Optional.of(notStarted));

        caseService.createCase(request);

        verify(caseRepository).save(argThat(c ->
                ((org.nnnn.ddd.entity.dddCase) c).getRequestDt() != null &&
                ((org.nnnn.ddd.entity.dddCase) c).getDueDt() != null
        ));
    }

    // =========================================================================
    // saveCase()
    // FIX: saveCase calls findById(Integer) — use mockFindById_found which
    // now stubs both int and Integer variants
    // =========================================================================

    @Test
    @DisplayName("saveCase - updates case successfully")
    void saveCase_validCase_returnsUpdatedDto() {
        dtoCase.setId(100);
        mockFindById_found(100);

        org.nnnn.ddd.model.dddCase result = caseService.saveCase(dtoCase);

        assertThat(result).isEqualTo(dtoCase);
        verify(caseRepository).saveAndFlush(any());
    }

    @Test
    @DisplayName("saveCase - case not found throws CaseNotFoundException")
    void saveCase_caseNotFound_throwsCaseNotFoundException() {
        dtoCase.setId(999);
        mockFindById_empty(999);

        assertThatThrownBy(() -> caseService.saveCase(dtoCase))
                .isInstanceOf(CaseNotFoundException.class);
    }

    @Test
    @DisplayName("saveCase - version mismatch throws OptimisticLockException")
    void saveCase_versionMismatch_throwsOptimisticLockException() {
        dtoCase.setId(100);
        dtoCase.setVersion(1L);
        entityCase.setVersion(2L);
        mockFindById_found(100);

        assertThatThrownBy(() -> caseService.saveCase(dtoCase))
                .isInstanceOf(jakarta.persistence.OptimisticLockException.class);
    }

    @Test
    @DisplayName("saveCase - updates category when provided")
    void saveCase_withCategory_updatesCategory() {
        dtoCase.setId(100);
        org.nnnn.ddd.model.Category cat = new org.nnnn.ddd.model.Category();
        cat.setId(1);
        dtoCase.setCategory(cat);

        CategoryList catEntity = new CategoryList();
        catEntity.setId(1);

        mockFindById_found(100);
        when(categoryListRepository.findById(1)).thenReturn(Optional.of(catEntity));

        caseService.saveCase(dtoCase);

        verify(categoryListRepository).findById(1);
    }

    @Test
    @DisplayName("saveCase - clears category when null")
    void saveCase_nullCategory_clearsCategory() {
        dtoCase.setId(100);
        dtoCase.setCategory(null);

        CategoryList existingCat = new CategoryList();
        entityCase.setCategory(existingCat);

        mockFindById_found(100);

        caseService.saveCase(dtoCase);

        assertThat(entityCase.getCategory()).isNull();
    }

    @Test
    @DisplayName("saveCase - updates status when provided")
    void saveCase_withStatus_updatesStatus() {
        dtoCase.setId(100);
        Status status = new Status();
        status.setId(AppConstants.STATUS_COMPLETED);
        dtoCase.setStatus(status);

        StatusList statusEntity = new StatusList();
        mockFindById_found(100);
        when(statusListRepository.findById(AppConstants.STATUS_COMPLETED)).thenReturn(Optional.of(statusEntity));

        caseService.saveCase(dtoCase);

        verify(statusListRepository).findById(AppConstants.STATUS_COMPLETED);
    }

    // =========================================================================
    // findCases()
    // =========================================================================

    @Test
    @DisplayName("findCases - returns empty response when no filters provided")
    void findCases_noFilters_returnsEmptyResponse() {
        CaseFilter filter = new CaseFilter();

        CaseListResponse result = caseService.findCases(filter);

        assertThat(result.getCaseSummaries()).isNull();
        verifyNoInteractions(caseRepository);
    }

    @Test
    @DisplayName("findCases - filters by arrId executes query")
    void findCases_withArrId_executesQuery() {
        CaseFilter filter = new CaseFilter();
        filter.setArrId("ARR001");
        filter.setPageSize(10);
        filter.setPageNumber(0);

        Page<org.nnnn.ddd.entity.dddCase> page = new PageImpl<>(Collections.emptyList());
        when(caseRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(cdwRepository.getArrestInfoSummary(anyList())).thenReturn(Collections.emptyList());
        when(caseRepository.countByDueDtBeforeAndStatus_IdNot(any(), anyInt())).thenReturn(0);
        when(caseRepository.countByDueDtBetweenAndStatus_IdNot(any(), any(), anyInt())).thenReturn(0);

        CaseListResponse result = caseService.findCases(filter);

        assertThat(result.getCaseSummaries()).isEmpty();
        verify(caseRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    @DisplayName("findCases - non-supervisor filters to allowed offices")
    void findCases_nonSupervisor_filtersToAllowedOffices() {
        CaseFilter filter = new CaseFilter();
        filter.setArrId("ARR001");
        filter.setPageSize(10);
        filter.setPageNumber(0);

        when(authenticationService.isSupervisor()).thenReturn(false);
        when(authenticationService.getUsername()).thenReturn("jdoe");

        dddOffice office = new dddOffice();
        office.setId(1);
        when(referenceDataService.getdddOffices("jdoe")).thenReturn(List.of(office));

        Page<org.nnnn.ddd.entity.dddCase> page = new PageImpl<>(Collections.emptyList());
        when(caseRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(cdwRepository.getMaskedArrestInfoSummary(anyList())).thenReturn(Collections.emptyList());
        when(caseRepository.countByDueDtBeforeAndAssignedNmAndStatus_IdNot(any(), any(), anyInt())).thenReturn(0);
        when(caseRepository.countByDueDtBetweenAndAssignedNmAndStatus_IdNot(any(), any(), any(), anyInt())).thenReturn(0);

        caseService.findCases(filter);

        verify(referenceDataService).getdddOffices("jdoe");
    }

    @Test
    @DisplayName("findCases - with sorting applies sort order")
    void findCases_withSorting_appliesSortOrder() {
        CaseFilter filter = new CaseFilter();
        filter.setArrId("ARR001");
        filter.setPageSize(10);
        filter.setPageNumber(0);
        filter.setSortBy(List.of("requestDt"));
        filter.setSortOrder(List.of(1));

        Page<org.nnnn.ddd.entity.dddCase> page = new PageImpl<>(Collections.emptyList());
        when(caseRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(cdwRepository.getArrestInfoSummary(anyList())).thenReturn(Collections.emptyList());
        when(caseRepository.countByDueDtBeforeAndStatus_IdNot(any(), anyInt())).thenReturn(0);
        when(cacheRepository.countByDueDtBetweenAndStatus_IdNot(any(), any(), anyInt())).thenReturn(0);

        caseService.findCases(filter);

        verify(caseRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    // =========================================================================
    // saveNote()
    // FIX: saveNote calls caseRepository.findById(caseId) where caseId is Integer
    // =========================================================================

    @Test
    @DisplayName("saveNote - creates note without file successfully")
    void saveNote_textOnly_createsNote() {
        // saveNote uses findById(Integer caseId) — stub Integer version
        doReturn(Optional.of(entityCase)).when(caseRepository).findById(Integer.valueOf(100));
        when(caseRepository.save(any())).thenReturn(entityCase);
        when(modelMapper.map(any(org.nnnn.ddd.entity.dddCase.class),
                eq(org.nnnn.ddd.model.dddCase.class))).thenReturn(dtoCase);

        org.nnnn.ddd.model.dddCase result = caseService.saveNote(100, "Test note", null, null);

        assertThat(result).isNotNull();
        verify(caseRepository).save(any());
    }

    @Test
    @DisplayName("saveNote - creates note with file uploads to S3")
    void saveNote_withFile_uploadsToS3() throws Exception {
        MultipartFile mockFile = mock(MultipartFile.class);
        CaseUpload upload = new CaseUpload();
        upload.setId(1);

        doReturn(Optional.of(entityCase)).when(caseRepository).findById(Integer.valueOf(100));
        when(caseUploadRepository.saveAndFlush(any())).thenReturn(upload);
        when(caseRepository.save(any())).thenReturn(entityCase);
        when(modelMapper.map(any(org.nnnn.ddd.entity.dddCase.class),
                eq(org.nnnn.ddd.model.dddCase.class))).thenReturn(dtoCase);

        caseService.saveNote(100, "Note with file", "test.pdf", mockFile);

        verify(s3Service).uploadMultipartFile(anyString(), eq(mockFile));
    }

    @Test
    @DisplayName("saveNote - S3 upload failure throws FileUploadException")
    void saveNote_s3Failure_throwsFileUploadException() throws Exception {
        MultipartFile mockFile = mock(MultipartFile.class);
        CaseUpload upload = new CaseUpload();
        upload.setId(1);

        doReturn(Optional.of(entityCase)).when(caseRepository).findById(Integer.valueOf(100));
        when(caseUploadRepository.saveAndFlush(any())).thenReturn(upload);
        doThrow(new RuntimeException("S3 error")).when(s3Service).uploadMultipartFile(anyString(), any());

        assertThatThrownBy(() -> caseService.saveNote(100, "Note", "file.pdf", mockFile))
                .isInstanceOf(FileUploadException.class)
                .hasMessageContaining("S3");
    }

    @Test
    @DisplayName("saveNote - case not found throws CaseNotFoundException")
    void saveNote_caseNotFound_throwsCaseNotFoundException() {
        doReturn(Optional.empty()).when(caseRepository).findById(Integer.valueOf(999));

        assertThatThrownBy(() -> caseService.saveNote(999, "Note", null, null))
                .isInstanceOf(CaseNotFoundException.class)
                .hasMessageContaining("999");
    }

    // =========================================================================
    // getFile()
    // =========================================================================

    @Test
    @DisplayName("getFile - returns filename and resource when upload found")
    void getFile_uploadFound_returnsFileNameAndResource() {
        CaseUpload upload = new CaseUpload();
        upload.setId(1);
        upload.setFileNm("document.pdf");

        when(caseUploadRepository.findById(1)).thenReturn(Optional.of(upload));
        when(s3Service.downloadFile(anyString())).thenReturn(mock(org.springframework.core.io.Resource.class));

        Object[] result = caseService.getFile(100, 1);

        assertThat(result[0]).isEqualTo("document.pdf");
        assertThat(result[1]).isNotNull();
        verify(s3Service).downloadFile("uploads/case/100/1");
    }

    @Test
    @DisplayName("getFile - upload not found still downloads from S3")
    void getFile_uploadNotFound_stillDownloadsFromS3() {
        when(caseUploadRepository.findById(99)).thenReturn(Optional.empty());
        when(s3Service.downloadFile(anyString())).thenReturn(mock(org.springframework.core.io.Resource.class));

        Object[] result = caseService.getFile(100, 99);

        assertThat(result[0]).isNull();
        verify(s3Service).downloadFile("uploads/case/100/99");
    }

    // =========================================================================
    // deleteFile()
    // =========================================================================

    @Test
    @DisplayName("deleteFile - marks upload as deleted then throws FileDeleteException")
    void deleteFile_uploadFound_marksDeletedThenThrows() {
        CaseUpload upload = new CaseUpload();
        upload.setId(1);
        upload.setDeletedFlg((short) 0);

        when(caseUploadRepository.findById(1)).thenReturn(Optional.of(upload));

        assertThatThrownBy(() -> caseService.deleteFile(100, 1))
                .isInstanceOf(FileDeleteException.class);

        verify(caseUploadRepository).save(argThat(u ->
                ((CaseUpload) u).getDeletedFlg() == (short) 1
        ));
    }

    @Test
    @DisplayName("deleteFile - upload not found throws FileDeleteException")
    void deleteFile_uploadNotFound_throwsFileDeleteException() {
        when(caseUploadRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> caseService.deleteFile(100, 99))
                .isInstanceOf(FileDeleteException.class)
                .hasMessageContaining("99");
    }
}
