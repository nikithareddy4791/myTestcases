package org.nnnn.ddd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.nnnn.ddd.AppConstants;
import org.nnnn.ddd.entity.dddCase;
import org.nnnn.ddd.exceptions.CaseAccessException;
import org.nnnn.ddd.exceptions.CaseNotFoundException;
import org.nnnn.ddd.exceptions.SealedAccessException;
import org.nnnn.ddd.model.ArrestInfo;
import org.nnnn.ddd.model.Status;
import org.nnnn.ddd.model.User;
import org.nnnn.ddd.repository.CaseRepository;
import org.nnnn.ddd.repository.CDWRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CaseService - loadCase Tests")
class CaseServiceLoadCaseTest {

    @Mock
    private CaseRepository caseRepository;

    @Mock
    private CDWRepository cdwRepository;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private ADSearchService adSearchService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CaseService caseService;

    private dddCase entityCase;
    private org.nnnn.ddd.model.dddCase dtoCase;
    private ArrestInfo arrestInfo;

    @BeforeEach
    void setUp() {
        // Entity (DB row)
        entityCase = new dddCase();
        entityCase.setId(100);
        entityCase.setArrId("ARR001");
        entityCase.setAssignedNm("jdoe");

        // DTO (what gets returned to the client)
        dtoCase = new org.nnnn.ddd.model.dddCase();
        dtoCase.setId(100);
        dtoCase.setArrId("ARR001");

        // Arrest info from CDW
        arrestInfo = new ArrestInfo();
        arrestInfo.setArrId("ARR001");
        arrestInfo.setArrSealedFlg("N");  // not sealed by default
        arrestInfo.setDeftFrstNm("John");
        arrestInfo.setDeftLastNm("Doe");
    }

    // =========================================================================
    // Case Not Found
    // =========================================================================

    @Test
    @DisplayName("loadCase - throws CaseNotFoundException when case does not exist")
    void loadCase_caseNotFound_throwsCaseNotFoundException() {
        when(caseRepository.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> caseService.loadCase(999))
                .isInstanceOf(CaseNotFoundException.class)
                .hasMessageContaining("999");

        verify(caseRepository, times(1)).findById(999);
        verifyNoInteractions(cdwRepository);
    }

    // =========================================================================
    // Active Flag Logic — covers the status branch in loadCase
    // =========================================================================

    @Test
    @DisplayName("loadCase - sets activeFlg=1 when status is NOT_STARTED")
    void loadCase_statusNotStarted_setsActiveFlg1() {
        Status status = new Status();
        status.setId(AppConstants.STATUS_NOT_STARTED);
        dtoCase.setStatus(status);

        when(caseRepository.findById(100)).thenReturn(Optional.of(entityCase));
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);

        org.nnnn.ddd.model.dddCase result = caseService.loadCase(100);

        assertThat(result.getActiveFlg()).isEqualTo(1);
    }

    @Test
    @DisplayName("loadCase - sets activeFlg=1 when status is IN_PROGRESS")
    void loadCase_statusInProgress_setsActiveFlg1() {
        Status status = new Status();
        status.setId(AppConstants.STATUS_IN_PROGRESS);
        dtoCase.setStatus(status);

        when(caseRepository.findById(100)).thenReturn(Optional.of(entityCase));
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);

        org.nnnn.ddd.model.dddCase result = caseService.loadCase(100);

        assertThat(result.getActiveFlg()).isEqualTo(1);
    }

    @Test
    @DisplayName("loadCase - sets activeFlg=1 when status is WAITING")
    void loadCase_statusWaiting_setsActiveFlg1() {
        Status status = new Status();
        status.setId(AppConstants.STATUS_WAITING);
        dtoCase.setStatus(status);

        when(caseRepository.findById(100)).thenReturn(Optional.of(entityCase));
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);

        org.nnnn.ddd.model.dddCase result = caseService.loadCase(100);

        assertThat(result.getActiveFlg()).isEqualTo(1);
    }

    @Test
    @DisplayName("loadCase - sets activeFlg=0 when status is COMPLETED")
    void loadCase_statusCompleted_setsActiveFlg0() {
        Status status = new Status();
        status.setId(AppConstants.STATUS_COMPLETED);
        dtoCase.setStatus(status);

        when(caseRepository.findById(100)).thenReturn(Optional.of(entityCase));
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);

        org.nnnn.ddd.model.dddCase result = caseService.loadCase(100);

        assertThat(result.getActiveFlg()).isEqualTo(0);
    }

    @Test
    @DisplayName("loadCase - sets activeFlg=0 when status is null")
    void loadCase_statusNull_setsActiveFlg0() {
        dtoCase.setStatus(null);  // null status → falls into the "else" branch → activeFlg=0

        when(caseRepository.findById(100)).thenReturn(Optional.of(entityCase));
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);

        org.nnnn.ddd.model.dddCase result = caseService.loadCase(100);

        assertThat(result.getActiveFlg()).isEqualTo(0);
    }

    // =========================================================================
    // Sealed Access Logic — the most complex branch in loadCase
    // =========================================================================

    @Test
    @DisplayName("loadCase - unsealed arrest loads successfully without sealed access check")
    void loadCase_unsealedArrest_loadsSuccessfully() {
        arrestInfo.setArrSealedFlg("N");
        dtoCase.setStatus(null);

        when(caseRepository.findById(100)).thenReturn(Optional.of(entityCase));
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);

        org.nnnn.ddd.model.dddCase result = caseService.loadCase(100);

        assertThat(result).isNotNull();
        assertThat(result.getArrest()).isEqualTo(arrestInfo);
        // hasSealedAccess should NOT be called for unsealed cases
        verify(authenticationService, never()).hasSealedAccess();
    }

    @Test
    @DisplayName("loadCase - sealed arrest throws SealedAccessException when user has no sealed access")
    void loadCase_sealedArrest_noSealedAccess_throwsSealedAccessException() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);

        when(caseRepository.findById(100)).thenReturn(Optional.of(entityCase));
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(false);  // no sealed access

        assertThatThrownBy(() -> caseService.loadCase(100))
                .isInstanceOf(SealedAccessException.class)
                .hasMessageContaining("no sealed access");

        verify(authenticationService, times(1)).hasSealedAccess();
    }

    @Test
    @DisplayName("loadCase - sealed arrest, supervisor with sealed access loads successfully")
    void loadCase_sealedArrest_supervisorWithSealedAccess_loadsSuccessfully() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);

        when(caseRepository.findById(100)).thenReturn(Optional.of(entityCase));
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);
        when(authenticationService.isSupervisor()).thenReturn(true);  // supervisor skips further checks

        org.nnnn.ddd.model.dddCase result = caseService.loadCase(100);

        assertThat(result).isNotNull();
        // isSupervisor=true means no CaseAccessException is thrown
        verify(authenticationService, never()).hasRole(any());
    }

    @Test
    @DisplayName("loadCase - sealed arrest, non-supervisor assigned to case loads successfully")
    void loadCase_sealedArrest_nonSupervisor_assignedToCase_loadsSuccessfully() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);
        entityCase.setAssignedNm("jdoe");

        when(caseRepository.findById(100)).thenReturn(Optional.of(entityCase));
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);
        when(authenticationService.isSupervisor()).thenReturn(false);
        when(authenticationService.getUsername()).thenReturn("jdoe");  // user IS the assigned person

        org.nnnn.ddd.model.dddCase result = caseService.loadCase(100);

        assertThat(result).isNotNull();
        // assigned user — no CaseAccessException
        verify(authenticationService, never()).hasRole(any());
    }

    @Test
    @DisplayName("loadCase - sealed arrest, non-supervisor not assigned, belongs to ddd office loads successfully")
    void loadCase_sealedArrest_nonSupervisor_notAssigned_hasOfficeRole_loadsSuccessfully() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);
        entityCase.setAssignedNm("otherUser");

        org.nnnn.ddd.entity.dddOfficeList dddOffice = new org.nnnn.ddd.entity.dddOfficeList();
        dddOffice.setAdSgNm("ROLE_MANHATTAN");
        entityCase.setddd(dddOffice);

        when(caseRepository.findById(100)).thenReturn(Optional.of(entityCase));
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);
        when(authenticationService.isSupervisor()).thenReturn(false);
        when(authenticationService.getUsername()).thenReturn("jdoe");  // not assigned
        when(authenticationService.hasRole("ROLE_MANHATTAN")).thenReturn(true);  // but has office role

        org.nnnn.ddd.model.dddCase result = caseService.loadCase(100);

        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("loadCase - sealed arrest, non-supervisor not assigned, no ddd office set throws CaseAccessException")
    void loadCase_sealedArrest_nonSupervisor_notAssigned_noDddOffice_throwsCaseAccessException() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);
        entityCase.setAssignedNm("otherUser");
        entityCase.setddd(null);  // no ddd office on the case

        when(caseRepository.findById(100)).thenReturn(Optional.of(entityCase));
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);
        when(authenticationService.isSupervisor()).thenReturn(false);
        when(authenticationService.getUsername()).thenReturn("jdoe");  // not assigned

        assertThatThrownBy(() -> caseService.loadCase(100))
                .isInstanceOf(CaseAccessException.class)
                .hasMessageContaining("no access to case");
    }

    @Test
    @DisplayName("loadCase - sealed arrest, non-supervisor not assigned, wrong ddd office throws CaseAccessException")
    void loadCase_sealedArrest_nonSupervisor_notAssigned_wrongOfficeRole_throwsCaseAccessException() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);
        entityCase.setAssignedNm("otherUser");

        org.nnnn.ddd.entity.dddOfficeList dddOffice = new org.nnnn.ddd.entity.dddOfficeList();
        dddOffice.setAdSgNm("ROLE_MANHATTAN");
        entityCase.setddd(dddOffice);

        when(caseRepository.findById(100)).thenReturn(Optional.of(entityCase));
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(authenticationService.hasSealedAccess()).thenReturn(true);
        when(authenticationService.isSupervisor()).thenReturn(false);
        when(authenticationService.getUsername()).thenReturn("jdoe");  // not assigned
        when(authenticationService.hasRole("ROLE_MANHATTAN")).thenReturn(false);  // wrong office

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

        when(caseRepository.findById(100)).thenReturn(Optional.of(entityCase));
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
        when(adSearchService.findUser("jdoe")).thenReturn(mockUser);

        org.nnnn.ddd.model.dddCase result = caseService.loadCase(100);

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

        when(caseRepository.findById(100)).thenReturn(Optional.of(entityCase));
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);

        caseService.loadCase(100);

        // AD service should NOT be called if no assignedNm
        verifyNoInteractions(adSearchService);
    }

    // =========================================================================
    // Arrest Info
    // =========================================================================

    @Test
    @DisplayName("loadCase - arrest info is set on the returned DTO")
    void loadCase_arrestInfoIsAttachedToDto() {
        dtoCase.setStatus(null);
        arrestInfo.setArrSealedFlg("N");
        arrestInfo.setDeftFrstNm("Jane");
        arrestInfo.setDeftLastNm("Smith");

        when(caseRepository.findById(100)).thenReturn(Optional.of(entityCase));
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);

        org.nnnn.ddd.model.dddCase result = caseService.loadCase(100);

        assertThat(result.getArrest()).isNotNull();
        assertThat(result.getArrest().getDeftFrstNm()).isEqualTo("Jane");
        assertThat(result.getArrest().getDeftLastNm()).isEqualTo("Smith");
        verify(cdwRepository, times(1)).getArrestInfo("ARR001");
    }
}
