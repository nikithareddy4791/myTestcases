package org.nnnn.ddd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nnnn.ddd.entity.dddAudit;
import org.nnnn.ddd.repository.AuditRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuditService Tests")
class AuditServiceTest {

    @Mock
    private AuditRepository auditRepository;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuditService auditService;

    @BeforeEach
    void setUp() {
        when(authenticationService.getUsername()).thenReturn("jdoe");
    }

    // =========================================================================
    // auditAction(String actionType, String actionDetail)
    // =========================================================================

    @Test
    @DisplayName("auditAction - saves audit record with correct fields")
    void auditAction_twoArgs_savesAuditWithCorrectFields() {
        auditService.auditAction("LOGIN", "User logged in");

        ArgumentCaptor<dddAudit> captor = ArgumentCaptor.forClass(dddAudit.class);
        verify(auditRepository, times(1)).save(captor.capture());

        dddAudit saved = captor.getValue();
        assertThat(saved.getUserNm()).isEqualTo("jdoe");
        assertThat(saved.getActionType()).isEqualTo("LOGIN");
        assertThat(saved.getActionDetail()).isEqualTo("User logged in");
        assertThat(saved.getCaseId()).isNull(); // no caseId for this overload
    }

    @Test
    @DisplayName("auditAction - calls getUsername to set userNm")
    void auditAction_twoArgs_callsGetUsername() {
        auditService.auditAction("VIEW", "Viewed dashboard");

        verify(authenticationService, times(1)).getUsername();
    }

    @Test
    @DisplayName("auditAction - saves exactly once")
    void auditAction_twoArgs_savesExactlyOnce() {
        auditService.auditAction("LOGOUT", "User logged out");

        verify(auditRepository, times(1)).save(any(dddAudit.class));
    }

    @Test
    @DisplayName("auditAction - works with null actionDetail")
    void auditAction_twoArgs_nullActionDetail_savesSuccessfully() {
        auditService.auditAction("VIEW", null);

        ArgumentCaptor<dddAudit> captor = ArgumentCaptor.forClass(dddAudit.class);
        verify(auditRepository).save(captor.capture());

        assertThat(captor.getValue().getActionDetail()).isNull();
        assertThat(captor.getValue().getActionType()).isEqualTo("VIEW");
    }

    @Test
    @DisplayName("auditAction - works with empty strings")
    void auditAction_twoArgs_emptyStrings_savesSuccessfully() {
        auditService.auditAction("", "");

        ArgumentCaptor<dddAudit> captor = ArgumentCaptor.forClass(dddAudit.class);
        verify(auditRepository).save(captor.capture());

        assertThat(captor.getValue().getActionType()).isEmpty();
        assertThat(captor.getValue().getActionDetail()).isEmpty();
    }

    // =========================================================================
    // auditAction(String actionType, String actionDetail, Integer caseId)
    // =========================================================================

    @Test
    @DisplayName("auditAction - saves audit record with caseId")
    void auditAction_threeArgs_savesAuditWithCaseId() {
        auditService.auditAction("CASE_VIEW", "Viewed case details", 100);

        ArgumentCaptor<dddAudit> captor = ArgumentCaptor.forClass(dddAudit.class);
        verify(auditRepository, times(1)).save(captor.capture());

        dddAudit saved = captor.getValue();
        assertThat(saved.getUserNm()).isEqualTo("jdoe");
        assertThat(saved.getActionType()).isEqualTo("CASE_VIEW");
        assertThat(saved.getActionDetail()).isEqualTo("Viewed case details");
        assertThat(saved.getCaseId()).isEqualTo(100);
    }

    @Test
    @DisplayName("auditAction - calls getUsername to set userNm when caseId provided")
    void auditAction_threeArgs_callsGetUsername() {
        auditService.auditAction("CASE_UPDATE", "Updated case", 100);

        verify(authenticationService, times(1)).getUsername();
    }

    @Test
    @DisplayName("auditAction - saves exactly once when caseId provided")
    void auditAction_threeArgs_savesExactlyOnce() {
        auditService.auditAction("CASE_CREATE", "Created case", 101);

        verify(auditRepository, times(1)).save(any(dddAudit.class));
    }

    @Test
    @DisplayName("auditAction - works with null caseId")
    void auditAction_threeArgs_nullCaseId_savesSuccessfully() {
        auditService.auditAction("CASE_VIEW", "Viewed case", null);

        ArgumentCaptor<dddAudit> captor = ArgumentCaptor.forClass(dddAudit.class);
        verify(auditRepository).save(captor.capture());

        assertThat(captor.getValue().getCaseId()).isNull();
        assertThat(captor.getValue().getActionType()).isEqualTo("CASE_VIEW");
    }

    @Test
    @DisplayName("auditAction - different users produce different userNm values")
    void auditAction_differentUsers_setsCorrectUserNm() {
        when(authenticationService.getUsername()).thenReturn("asmith");

        auditService.auditAction("LOGIN", "User logged in");

        ArgumentCaptor<dddAudit> captor = ArgumentCaptor.forClass(dddAudit.class);
        verify(auditRepository).save(captor.capture());

        assertThat(captor.getValue().getUserNm()).isEqualTo("asmith");
    }

    @Test
    @DisplayName("auditAction - two overloads create separate audit records")
    void auditAction_bothOverloads_createSeparateRecords() {
        auditService.auditAction("LOGIN", "User logged in");
        auditService.auditAction("CASE_VIEW", "Viewed case", 100);

        verify(auditRepository, times(2)).save(any(dddAudit.class));
    }
}
