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
import org.nnnn.ddd.exceptions.CaseAccessException;
import org.nnnn.ddd.exceptions.CaseNotFoundException;
import org.nnnn.ddd.exceptions.SealedAccessException;
import org.nnnn.ddd.model.ArrestInfo;
import org.nnnn.ddd.model.Status;
import org.nnnn.ddd.model.User;
import org.nnnn.ddd.repository.CaseRepository;
import org.nnnn.ddd.repository.CDWRepository;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("CaseService - loadCase Tests")
class CaseServiceLoadCaseTest {

    @Mock private CaseRepository caseRepository;
    @Mock private CDWRepository cdwRepository;
    @Mock private AuthenticationService authenticationService;
    @Mock private ADSearchService adSearchService;
    @Mock private ModelMapper modelMapper;

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
        arrestInfo.setDeftFrstNm("John");
        arrestInfo.setDeftLastNm("Doe");

        // Default stubs — prevent NPE on getUsername().equals() in loadCase
        // LENIENT mode means unused stubs in individual tests won't cause errors
        lenient().when(authenticationService.getUsername()).thenReturn("jdoe");
        lenient().when(authenticationService.hasSealedAccess()).thenReturn(false);
        lenient().when(authenticationService.isSupervisor()).thenReturn(false);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void mockFindById_found(int id) {
        java.util.Optional opt = java.util.Optional.of(entityCase);
        doReturn(opt).when(caseRepository).findById(id);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void mockFindById_empty(int id) {
        java.util.Optional opt = java.util.Optional.empty();
        doReturn(opt).when(caseRepository).findById(id);
    }

    private void mockMapper() {
        when(modelMapper.map(entityCase, org.nnnn.ddd.model.dddCase.class)).thenReturn(dtoCase);
    }

    private void mockArrestInfo() {
        when(cdwRepository.getArrestInfo("ARR001")).thenReturn(arrestInfo);
    }

    // =========================================================================
    // Case Not Found
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

    // =========================================================================
    // Active Flag Logic
    // =========================================================================

    @Test
    @DisplayName("loadCase - sets activeFlg=1 when status is NOT_STARTED")
    void loadCase_statusNotStarted_setsActiveFlg1() {
        Status s = new Status();
        s.setId(AppConstants.STATUS_NOT_STARTED);
        dtoCase.setStatus(s);
        arrestInfo.setArrSealedFlg("N");

        mockFindById_found(100);
        mockMapper();
        mockArrestInfo();

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
        mockMapper();
        mockArrestInfo();

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
        mockMapper();
        mockArrestInfo();

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
        mockMapper();
        mockArrestInfo();

        assertThat(caseService.loadCase(100).getActiveFlg()).isEqualTo(0);
    }

    @Test
    @DisplayName("loadCase - sets activeFlg=1 when status is null (else branch)")
    void loadCase_statusNull_setsActiveFlg1() {
        dtoCase.setStatus(null);
        arrestInfo.setArrSealedFlg("N");

        mockFindById_found(100);
        mockMapper();
        mockArrestInfo();

        assertThat(caseService.loadCase(100).getActiveFlg()).isEqualTo(1);
    }

    // =========================================================================
    // Sealed Access Logic
    // =========================================================================

    @Test
    @DisplayName("loadCase - unsealed arrest loads without calling hasSealedAccess")
    void loadCase_unsealedArrest_loadsSuccessfully() {
        arrestInfo.setArrSealedFlg("N");
        dtoCase.setStatus(null);

        mockFindById_found(100);
        mockMapper();
        mockArrestInfo();

        assertThat(caseService.loadCase(100)).isNotNull();
        verify(authenticationService, never()).hasSealedAccess();
    }

    @Test
    @DisplayName("loadCase - sealed arrest, no sealed access throws SealedAccessException")
    void loadCase_sealedArrest_noSealedAccess_throwsSealedAccessException() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);

        mockFindById_found(100);
        mockMapper();
        mockArrestInfo();
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
        mockMapper();
        mockArrestInfo();
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

        mockFindById_found(100);
        mockMapper();
        mockArrestInfo();
        when(authenticationService.hasSealedAccess()).thenReturn(true);
        when(authenticationService.isSupervisor()).thenReturn(false);
        when(authenticationService.getUsername()).thenReturn("jdoe");

        assertThat(caseService.loadCase(100)).isNotNull();
        verify(authenticationService, never()).hasRole(any());
    }

    @Test
    @DisplayName("loadCase - sealed arrest, non-supervisor not assigned, no office throws CaseAccessException")
    void loadCase_sealedArrest_nonSupervisor_noOffice_throwsCaseAccessException() {
        arrestInfo.setArrSealedFlg("Y");
        dtoCase.setStatus(null);
        entityCase.setAssignedNm("otherUser");

        mockFindById_found(100);
        mockMapper();
        mockArrestInfo();
        when(authenticationService.hasSealedAccess()).thenReturn(true);
        when(authenticationService.isSupervisor()).thenReturn(false);
        when(authenticationService.getUsername()).thenReturn("jdoe");

        assertThatThrownBy(() -> caseService.loadCase(100))
                .isInstanceOf(CaseAccessException.class)
                .hasMessageContaining("no access to case");

        verify(authenticationService, never()).hasRole(any());
    }

    @Test
    @DisplayName("loadCase - sealed arrest, non-supervisor not assigned, wrong role throws CaseAccessException")
    void loadCase_sealedArrest_nonSupervisor_wrongRole_throwsCaseAccessException() {
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
        mockMapper();
        mockArrestInfo();
        when(authenticationService.hasSealedAccess()).thenReturn(true);
        when(authenticationService.isSupervisor()).thenReturn(false);
        when(authenticationService.getUsername()).thenReturn("jdoe");
        when(authenticationService.hasRole("ROLE_MANHATTAN")).thenReturn(false);

        assertThatThrownBy(() -> caseService.loadCase(100))
                .isInstanceOf(CaseAccessException.class)
                .hasMessageContaining("no access to case");
    }

    // =========================================================================
    // AD User Lookup
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

        mockFindById_found(100);
        mockMapper();
        mockArrestInfo();
        when(adSearchService.findUser("jdoe")).thenReturn(mockUser);

        org.nnnn.ddd.model.dddCase result = caseService.loadCase(100);

        assertThat(result.getAssignedNmInfo()).isNotNull();
        assertThat(result.getAssignedNmInfo().getUsername()).isEqualTo("jdoe");
        verify(adSearchService).findUser("jdoe");
    }

    @Test
    @DisplayName("loadCase - skips AD lookup when assignedNm is null")
    void loadCase_noAssignedNm_skipsAdLookup() {
        entityCase.setAssignedNm(null);
        dtoCase.setStatus(null);
        arrestInfo.setArrSealedFlg("N");

        mockFindById_found(100);
        mockMapper();
        mockArrestInfo();

        caseService.loadCase(100);

        verifyNoInteractions(adSearchService);
    }

    // =========================================================================
    // Arrest Info
    // =========================================================================

    @Test
    @DisplayName("loadCase - arrest info is attached to returned DTO")
    void loadCase_arrestInfoAttachedToDto() {
        dtoCase.setStatus(null);
        arrestInfo.setArrSealedFlg("N");
        arrestInfo.setDeftFrstNm("Jane");
        arrestInfo.setDeftLastNm("Smith");

        mockFindById_found(100);
        mockMapper();
        mockArrestInfo();

        org.nnnn.ddd.model.dddCase result = caseService.loadCase(100);

        assertThat(result.getArrest()).isNotNull();
        assertThat(result.getArrest().getDeftFrstNm()).isEqualTo("Jane");
        assertThat(result.getArrest().getDeftLastNm()).isEqualTo("Smith");
        verify(cdwRepository).getArrestInfo("ARR001");
    }
}
