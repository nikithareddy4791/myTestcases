package org.nnnn.ddd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.nnnn.ddd.AppConstants;
import org.nnnn.ddd.entity.dddCase;
import org.nnnn.ddd.model.User;
import org.nnnn.ddd.repository.CaseRepository;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("EmailService Tests")
class EmailServiceTest {

    @Mock private CaseRepository caseRepository;
    @Mock private ADSearchService adSearchService;
    @Mock private JavaMailSenderImpl mailSender;
    @Mock private Environment env;

    @InjectMocks
    private EmailService emailService;

    private User regularUser;
    private User testUser;
    private dddCase overdueCase;
    private dddCase comingDueCase;

    @BeforeEach
    void setUp() {
        // Regular user
        regularUser = new User();
        regularUser.setUsername("jdoe");
        regularUser.setFirstName("John");
        regularUser.setLastName("Doe");
        regularUser.setEmail("jdoe@nnnn.org");
        regularUser.setRank("Detective");

        // Test/service account user (starts with T-SG or V-)
        testUser = new User();
        testUser.setUsername("T-SG-testuser");
        testUser.setEmail("testuser@nnnn.org");

        // Overdue case
        overdueCase = new dddCase();
        overdueCase.setId(100);
        overdueCase.setArrId("ARR001");
        overdueCase.setAssignedNm("jdoe");
        overdueCase.setRequestDt(java.sql.Date.valueOf(LocalDate.now().minusDays(30)));
        overdueCase.setDueDt(java.sql.Date.valueOf(LocalDate.now().minusDays(1)));

        // Coming due case
        comingDueCase = new dddCase();
        comingDueCase.setId(101);
        comingDueCase.setArrId("ARR002");
        comingDueCase.setAssignedNm("jdoe");
        comingDueCase.setRequestDt(java.sql.Date.valueOf(LocalDate.now().minusDays(10)));
        comingDueCase.setDueDt(java.sql.Date.valueOf(LocalDate.now().plusDays(2)));

        // Default env stubs
        lenient().when(env.acceptsProfiles(Profiles.of("local"))).thenReturn(false);
        lenient().when(env.acceptsProfiles(Profiles.of("prod"))).thenReturn(false);

        // Default mail session stub
        jakarta.mail.Session mockSession = mock(jakarta.mail.Session.class);
        lenient().when(mailSender.getSession()).thenReturn(mockSession);
    }

    // =========================================================================
    // sendOverdueEmail() — no cases
    // =========================================================================

    @Test
    @DisplayName("sendOverdueEmail - does nothing when no overdue or coming due cases")
    void sendOverdueEmail_noCases_doesNotSendEmail() {
        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), eq(AppConstants.STATUS_COMPLETED)))
                .thenReturn(Collections.emptyList());
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), eq(AppConstants.STATUS_COMPLETED)))
                .thenReturn(Collections.emptyList());

        emailService.sendOverdueEmail();

        verify(mailSender, never()).send(any(SimpleMailMessage.class));
        verifyNoInteractions(adSearchService);
    }

    // =========================================================================
    // sendOverdueEmail() — local profile (log only, no email sent)
    // =========================================================================

    @Test
    @DisplayName("sendOverdueEmail - local profile logs instead of sending email")
    void sendOverdueEmail_localProfile_logsInsteadOfSending() {
        when(env.acceptsProfiles(Profiles.of("local"))).thenReturn(true);
        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(List.of(overdueCase));
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(Collections.emptyList());
        when(adSearchService.findUser("jdoe")).thenReturn(regularUser);

        emailService.sendOverdueEmail();

        // On local profile, no email is sent
        verify(mailSender, never()).send(any(SimpleMailMessage.class));
    }

    // =========================================================================
    // sendOverdueEmail() — non-local profile sends email
    // =========================================================================

    @Test
    @DisplayName("sendOverdueEmail - non-local profile sends email to regular user")
    void sendOverdueEmail_nonLocalProfile_sendsEmailToUser() {
        when(env.acceptsProfiles(Profiles.of("local"))).thenReturn(false);
        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(List.of(overdueCase));
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(Collections.emptyList());
        when(adSearchService.findUser("jdoe")).thenReturn(regularUser);

        emailService.sendOverdueEmail();

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender, times(1)).send(captor.capture());

        SimpleMailMessage sent = captor.getValue();
        assertThat(sent.getTo()).contains("jdoe@nnnn.org");
        assertThat(sent.getFrom()).isEqualTo("ddd-Notification@nnnn.org");
    }

    @Test
    @DisplayName("sendOverdueEmail - email has CC when sent to regular user")
    void sendOverdueEmail_regularUser_emailHasCc() {
        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(List.of(overdueCase));
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(Collections.emptyList());
        when(adSearchService.findUser("jdoe")).thenReturn(regularUser);

        emailService.sendOverdueEmail();

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());

        assertThat(captor.getValue().getCc()).contains("rtcc_support_applications@nnnn.org");
    }

    @Test
    @DisplayName("sendOverdueEmail - test user (T-SG) email goes to support only")
    void sendOverdueEmail_testUserTSG_emailGoesToSupportOnly() {
        overdueCase.setAssignedNm("T-SG-testuser");

        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(List.of(overdueCase));
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(Collections.emptyList());
        when(adSearchService.findUser("T-SG-testuser")).thenReturn(testUser);

        emailService.sendOverdueEmail();

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());

        SimpleMailMessage sent = captor.getValue();
        assertThat(sent.getTo()).contains("rtcc_support_applications@nnnn.org");
        assertThat(sent.getCc()).isNull();
    }

    @Test
    @DisplayName("sendOverdueEmail - test user (V-) email goes to support only")
    void sendOverdueEmail_testUserV_emailGoesToSupportOnly() {
        User vUser = new User();
        vUser.setUsername("V-vuser");
        vUser.setEmail("vuser@nnnn.org");
        overdueCase.setAssignedNm("V-vuser");

        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(List.of(overdueCase));
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(Collections.emptyList());
        when(adSearchService.findUser("V-vuser")).thenReturn(vUser);

        emailService.sendOverdueEmail();

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());

        assertThat(captor.getValue().getTo()).contains("rtcc_support_applications@nnnn.org");
    }

    // =========================================================================
    // sendOverdueEmail() — subject line
    // =========================================================================

    @Test
    @DisplayName("sendOverdueEmail - prod profile uses production subject")
    void sendOverdueEmail_prodProfile_usesProdSubject() {
        when(env.acceptsProfiles(Profiles.of("prod"))).thenReturn(true);
        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(List.of(overdueCase));
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(Collections.emptyList());
        when(adSearchService.findUser("jdoe")).thenReturn(regularUser);

        emailService.sendOverdueEmail();

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());

        assertThat(captor.getValue().getSubject()).isEqualTo("ddd Case Due Notification");
    }

    @Test
    @DisplayName("sendOverdueEmail - non-prod profile uses NON-PROD subject")
    void sendOverdueEmail_nonProdProfile_usesNonProdSubject() {
        when(env.acceptsProfiles(Profiles.of("prod"))).thenReturn(false);
        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(List.of(overdueCase));
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(Collections.emptyList());
        when(adSearchService.findUser("jdoe")).thenReturn(regularUser);

        emailService.sendOverdueEmail();

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());

        assertThat(captor.getValue().getSubject()).isEqualTo("[NON-PROD] ddd Case Due Notification");
    }

    // =========================================================================
    // sendOverdueEmail() — user not found in AD
    // =========================================================================

    @Test
    @DisplayName("sendOverdueEmail - does not send email when user not found in AD")
    void sendOverdueEmail_userNotFoundInAD_doesNotSendEmail() {
        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(List.of(overdueCase));
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(Collections.emptyList());
        when(adSearchService.findUser("jdoe")).thenReturn(null); // user not found

        emailService.sendOverdueEmail();

        verify(mailSender, never()).send(any(SimpleMailMessage.class));
    }

    // =========================================================================
    // sendOverdueEmail() — coming due only (not in overdue)
    // =========================================================================

    @Test
    @DisplayName("sendOverdueEmail - sends email for coming due user not in overdue map")
    void sendOverdueEmail_comingDueOnly_sendsEmail() {
        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(Collections.emptyList()); // no overdue
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(List.of(comingDueCase)); // has coming due
        when(adSearchService.findUser("jdoe")).thenReturn(regularUser);

        emailService.sendOverdueEmail();

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    @DisplayName("sendOverdueEmail - does not send duplicate email for user in both overdue and coming due")
    void sendOverdueEmail_userInBothMaps_sendsOnlyOneEmail() {
        // Same user in both overdue and coming due
        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(List.of(overdueCase));
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(List.of(comingDueCase));
        when(adSearchService.findUser("jdoe")).thenReturn(regularUser);

        emailService.sendOverdueEmail();

        // Should only send ONE email combining both overdue and coming due
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    // =========================================================================
    // generateEmailBody() — email body content
    // =========================================================================

    @Test
    @DisplayName("sendOverdueEmail - email body contains overdue case info")
    void sendOverdueEmail_emailBody_containsOverdueCaseInfo() {
        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(List.of(overdueCase));
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(Collections.emptyList());
        when(adSearchService.findUser("jdoe")).thenReturn(regularUser);

        emailService.sendOverdueEmail();

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());

        String body = captor.getValue().getText();
        assertThat(body).contains("Case ID: 100");
        assertThat(body).contains("Arrest ID: ARR001");
        assertThat(body).contains("overdue case");
    }

    @Test
    @DisplayName("sendOverdueEmail - email body uses plural for multiple overdue cases")
    void sendOverdueEmail_multipleOverdueCases_usesPlural() {
        dddCase overdueCase2 = new dddCase();
        overdueCase2.setId(102);
        overdueCase2.setArrId("ARR003");
        overdueCase2.setAssignedNm("jdoe");

        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(Arrays.asList(overdueCase, overdueCase2));
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(Collections.emptyList());
        when(adSearchService.findUser("jdoe")).thenReturn(regularUser);

        emailService.sendOverdueEmail();

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());

        assertThat(captor.getValue().getText()).contains("2 overdue cases");
    }

    @Test
    @DisplayName("sendOverdueEmail - email body contains rank and last name for regular user")
    void sendOverdueEmail_regularUser_bodyContainsRankAndLastName() {
        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(List.of(overdueCase));
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(Collections.emptyList());
        when(adSearchService.findUser("jdoe")).thenReturn(regularUser);

        emailService.sendOverdueEmail();

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());

        assertThat(captor.getValue().getText()).contains("Detective Doe");
    }

    @Test
    @DisplayName("sendOverdueEmail - test user body does not contain rank/name")
    void sendOverdueEmail_testUser_bodyDoesNotContainRankName() {
        overdueCase.setAssignedNm("T-SG-testuser");

        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(List.of(overdueCase));
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(Collections.emptyList());
        when(adSearchService.findUser("T-SG-testuser")).thenReturn(testUser);

        emailService.sendOverdueEmail();

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());

        // Test users don't get rank/name in body
        assertThat(captor.getValue().getText()).doesNotContain("Detective");
    }

    @Test
    @DisplayName("sendOverdueEmail - email body contains coming due case info")
    void sendOverdueEmail_comingDueCases_bodyContainsComingDueInfo() {
        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(Collections.emptyList());
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(List.of(comingDueCase));
        when(adSearchService.findUser("jdoe")).thenReturn(regularUser);

        emailService.sendOverdueEmail();

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());

        String body = captor.getValue().getText();
        assertThat(body).contains("Case ID: 101");
        assertThat(body).contains("coming due");
    }

    @Test
    @DisplayName("sendOverdueEmail - multiple users each get their own email")
    void sendOverdueEmail_multipleUsers_eachGetOwnEmail() {
        dddCase case2 = new dddCase();
        case2.setId(200);
        case2.setArrId("ARR200");
        case2.setAssignedNm("asmith");

        User asmith = new User();
        asmith.setUsername("asmith");
        asmith.setEmail("asmith@nnnn.org");
        asmith.setRank("Sergeant");
        asmith.setLastName("Smith");

        when(caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(any(), anyInt()))
                .thenReturn(Arrays.asList(overdueCase, case2));
        when(caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(any(), any(), anyInt()))
                .thenReturn(Collections.emptyList());
        when(adSearchService.findUser("jdoe")).thenReturn(regularUser);
        when(adSearchService.findUser("asmith")).thenReturn(asmith);

        emailService.sendOverdueEmail();

        // Two separate emails — one per user
        verify(mailSender, times(2)).send(any(SimpleMailMessage.class));
    }
}
