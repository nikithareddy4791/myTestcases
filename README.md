package org.nypd.dlu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.nypd.dlu.AppConstants;
import org.nypd.dlu.exceptions.CaseAccessException;
import org.nypd.dlu.exceptions.CaseNotFoundException;
import org.nypd.dlu.exceptions.SealedAccessException;
import org.nypd.dlu.model.ArrestInfo;
import org.nypd.dlu.model.Status;
import org.nypd.dlu.model.User;
import org.nypd.dlu.repository.CaseRepository;
import org.nypd.dlu.repository.CDWRepository;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CaseService - loadCase Tests")
class CaseServiceLoadCaseTest {

    @Mock private CaseRepository caseRepository;
    @Mock private CDWRepository cdwRepository;
    @Mock private AuthenticationService authenticationService;
    @Mock private ADSearchService adSearchService;
    @Mock private ModelMapper modelMapper;

    @InjectMocks
    private CaseService caseService;

    // Fully qualified to avoid entity vs model name clash
    private org.nypd.dlu.entity.dluCase entityCase;
    private org.nypd.dlu.model.dluCase dtoCase;
    private ArrestInfo arrestInfo;

    @BeforeEach
    void setUp() {
        entityCase = new org.nypd.dlu.entity.dluCase();
        entityCase.setId(100);
        entityCase.setArrId("ARR001");
        entityCase.setAssignedNm("jdoe");

        dtoCase = new org.nypd.dlu.model.dluCase();
        dtoCase.setId(100);
        dtoCase.setArrId("ARR001");

        arrestInfo = new ArrestInfo();
        arrestInfo.setArrId("ARR001");
        arrestInfo.setArrSealedFlg("N");
        arrestInfo.setDeftFrstNm("John");
        arrestInfo.setDeftLastNm("Doe");
    }

    // =========================================================================
    // Helper: stub findById using doReturn to bypass Optional type ambiguity
    // caused by CaseRepository importing both entity.dluCase and model.dluCase
    // =========================================================================

    private void stubFindById_found(int id) {
        doReturn(java.util.Optional.of(entityCase))
                .when(caseRepository).findById(id);
    }

    private void stubFindById_notFound(int id) {
        doReturn(java.util.Optional.empty())
                .when(caseRepository).findById(id);
    }

    // =========================================================================
    // Case Not Found
    // =========================================================================

    @Test
    @DisplayName("loadCase - throws CaseNotFoundException when case does not exist")
    void loadCase_caseNotFound_throwsCaseNotFoundException() {
        stubFindById_notFound(999);

        assertThatThrownBy(() -> caseService.loadCase(999))
                .isInstanceOf(CaseNotFoundException.class)
                .hasMessageContaining("999");

        verify(caseRepository, times(1)).findById(999);
        verifyNoInteractions(cdwRepository);
    }

    // =========================================================================
    // Active Flag Logic
    // =========================================================================

    @Test
    @DisplayName("loadCase - sets activeFlg=1 when status is NOT_STARTED")
    void loadCase_statusNotStarted_setsActiveFlg1() {
        Status status = new Status();
        status.setId(AppConstants.STATUS_NOT_STARTED);
        dtoCase.setStatus(status);

        stubFindById_found(100);
        when(modelMapper.map(entityCase, org.nypd.dlu.model.dluCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);

        assertThat(caseService.loadCase(100).getActiveFlg()).isEqualTo(1);
    }

    @Test
    @DisplayName("loadCase - sets activeFlg=1 when status is IN_PROGRESS")
    void loadCase_statusInProgress_setsActiveFlg1() {
        Status status = new Status();
        status.setId(AppConstants.STATUS_IN_PROGRESS);
        dtoCase.setStatus(status);

        stubFindById_found(100);
        when(modelMapper.map(entityCase, org.nypd.dlu.model.dluCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);

        assertThat(caseService.loadCase(100).getActiveFlg()).isEqualTo(1);
    }

    @Test
    @DisplayName("loadCase - sets activeFlg=1 when status is WAITING")
    void loadCase_statusWaiting_setsActiveFlg1() {
        Status status = new Status();
        status.setId(AppConstants.STATUS_WAITING);
        dtoCase.setStatus(status);

        stubFindById_found(100);
        when(modelMapper.map(entityCase, org.nypd.dlu.model.dluCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);

        assertThat(caseService.loadCase(100).getActiveFlg()).isEqualTo(1);
    }

    @Test
    @DisplayName("loadCase - sets activeFlg=0 when status is COMPLETED")
    void loadCase_statusCompleted_setsActiveFlg0() {
        Status status = new Status();
        status.setId(AppConstants.STATUS_COMPLETED);
        dtoCase.setStatus(status);

        stubFindById_found(100);
        when(modelMapper.map(entityCase, org.nypd.dlu.model.dluCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);

        assertThat(caseService.loadCase(100).getActiveFlg()).isEqualTo(0);
    }

    @Test
    @DisplayName("loadCase - sets activeFlg=0 when status is null")
    void loadCase_statusNull_setsActiveFlg0() {
        dtoCase.setStatus(null);

        stubFindById_found(100);
        when(modelMapper.map(entityCase, org.nypd.dlu.model.dluCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);

        assertThat(caseService.loadCase(100).getActiveFlg()).isEqualTo(0);
    }

    // =========================================================================
    // Sealed Access Logic
    // =========================================================================

    @Test
    @DisplayName("loadCase - unsealed arrest loads without sealed access check")
    void loadCase_unsealedArrest_loadsSuccessfully() {
        arrestInfo.setArrSealedFlg("N");
        dtoCase.setStatus(null);

        stubFindById_found(100);
        when(modelMapper.map(entityCase, org.nypd.dlu.model.dluCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);

        assertThat(caseService.loadCase(100)).isNotNull();
        verify(authenticationService, never()).hasSealedAccess();
    }

    @Test
    @DisplayName("loadCase - sealed arrest, no sealed access throws SealedAccessException")
    void loadCase_sealedArrest_noSealedAccess_throwsSealedAccessException() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);

        stubFindById_found(100);
        when(modelMapper.map(entityCase, org.nypd.dlu.model.dluCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(false);

        assertThatThrownBy(() -> caseService.loadCase(100))
                .isInstanceOf(SealedAccessException.class)
                .hasMessageContaining("no sealed access");
    }

    @Test
    @DisplayName("loadCase - sealed arrest, supervisor loads successfully")
    void loadCase_sealedArrest_supervisorWithSealedAccess_loadsSuccessfully() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);

        stubFindById_found(100);
        when(modelMapper.map(entityCase, org.nypd.dlu.model.dluCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);
        when(authenticationService.isSupervisor()).thenReturn(true);

        assertThat(caseService.loadCase(100)).isNotNull();
        verify(authenticationService, never()).hasRole(any());
    }

    @Test
    @DisplayName("loadCase - sealed arrest, non-supervisor assigned to case loads successfully")
    void loadCase_sealedArrest_nonSupervisor_assignedToCase_loadsSuccessfully() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);
        entityCase.setAssignedNm("jdoe");

        stubFindById_found(100);
        when(modelMapper.map(entityCase, org.nypd.dlu.model.dluCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);
        when(authenticationService.isSupervisor()).thenReturn(false);
        when(authenticationService.getUsername()).thenReturn("jdoe");

        assertThat(caseService.loadCase(100)).isNotNull();
        verify(authenticationService, never()).hasRole(any());
    }

    @Test
    @DisplayName("loadCase - sealed arrest, non-supervisor not assigned, has office role loads successfully")
    void loadCase_sealedArrest_nonSupervisor_notAssigned_hasOfficeRole_loadsSuccessfully() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);
        entityCase.setAssignedNm("otherUser");

        org.nypd.dlu.entity.dluOfficeList office = new org.nypd.dlu.entity.dluOfficeList();
        office.setAdSgNm("ROLE_MANHATTAN");
        entityCase.setdlu(office);

        stubFindById_found(100);
        when(modelMapper.map(entityCase, org.nypd.dlu.model.dluCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);
        when(authenticationService.isSupervisor()).thenReturn(false);
        when(authenticationService.getUsername()).thenReturn("jdoe");
        when(authenticationService.hasRole("ROLE_MANHATTAN")).thenReturn(true);

        assertThat(caseService.loadCase(100)).isNotNull();
    }

    @Test
    @DisplayName("loadCase - sealed arrest, non-supervisor not assigned, no office throws CaseAccessException")
    void loadCase_sealedArrest_nonSupervisor_notAssigned_nodluOffice_throwsCaseAccessException() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);
        entityCase.setAssignedNm("otherUser");
        entityCase.setdlu(null);

        stubFindById_found(100);
        when(modelMapper.map(entityCase, org.nypd.dlu.model.dluCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);
        when(authenticationService.isSupervisor()).thenReturn(false);
        when(authenticationService.getUsername()).thenReturn("jdoe");

        assertThatThrownBy(() -> caseService.loadCase(100))
                .isInstanceOf(CaseAccessException.class)
                .hasMessageContaining("no access to case");
    }

    @Test
    @DisplayName("loadCase - sealed arrest, non-supervisor not assigned, wrong office throws CaseAccessException")
    void loadCase_sealedArrest_nonSupervisor_notAssigned_wrongOfficeRole_throwsCaseAccessException() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);
        entityCase.setAssignedNm("otherUser");

        org.nypd.dlu.entity.dluOfficeList office = new org.nypd.dlu.entity.dluOfficeList();
        office.setAdSgNm("ROLE_MANHATTAN");
        entityCase.setdlu(office);

        stubFindById_found(100);
        when(modelMapper.map(entityCase, org.nypd.dlu.model.dluCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);
        when(authenticationService.isSupervisor()).thenReturn(false);
        when(authenticationService.getUsername()).thenReturn("jdoe");
        when(authenticationService.hasRole("ROLE_MANHATTAN")).thenReturn(false);

        assertThatThrownBy(() -> caseService.loadCase(100))
                .isInstanceOf(CaseAccessException.class)
                .hasMessageContaining("no access to case");
    }

    // =========================================================================
    // AssignedNm / AD User lookup
    // =========================================================================

    @Test
    @DisplayName("loadCase - populates assignedNmInfo when assignedNm is set")
    void loadCase_withAssignedNm_populatesAssignedNmInfo() {
        entityCase.setAssignedNm("jdoe");
        dtoCase.setStatus(null);
        arrestInfo.setArrSealedFlg("N");

        User mockUser = new User();
        mockUser.setUsername("jdoe");
        mockUser.setFirstName("John");

        stubFindById_found(100);
        when(modelMapper.map(entityCase, org.nypd.dlu.model.dluCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(adSearchService.findUser("jdoe")).thenReturn(mockUser);

        org.nypd.dlu.model.dluCase result = caseService.loadCase(100);

        assertThat(result.getAssignedNmInfo()).isNotNull();
        assertThat(result.getAssignedNmInfo().getUsername()).isEqualTo("jdoe");
        verify(adSearchService, times(1)).findUser("jdoe");
    }

    @Test
    @DisplayName("loadCase - skips AD lookup when assignedNm is null")
    void loadCase_noAssignedNm_skipsAdLookup() {
        entityCase.setAssignedNm(null);
        dtoCase.setStatus(null);
        arrestInfo.setArrSealedFlg("N");

        stubFindById_found(100);
        when(modelMapper.map(entityCase, org.nypd.dlu.model.dluCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);

        caseService.loadCase(100);

        verifyNoInteractions(adSearchService);
    }

    // =========================================================================
    // Arrest Info
    // =========================================================================

    @Test
    @DisplayName("loadCase - arrest info is attached to returned DTO")
    void loadCase_arrestInfoIsAttachedToDto() {
        dtoCase.setStatus(null);
        arrestInfo.setArrSealedFlg("N");
        arrestInfo.setDeftFrstNm("Jane");
        arrestInfo.setDeftLastNm("Smith");

        stubFindById_found(100);
        when(modelMapper.map(entityCase, org.nypd.dlu.model.dluCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);

        org.nypd.dlu.model.dluCase result = caseService.loadCase(100);

        assertThat(result.getArrest()).isNotNull();
        assertThat(result.getArrest().getDeftFrstNm()).isEqualTo("Jane");
        assertThat(result.getArrest().getDeftLastNm()).isEqualTo("Smith");
        verify(cdwRepository, times(1)).getArrestInfo("ARR001");
    }
}
