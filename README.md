package org.nnnn.ddd.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.nnnn.ddd.AppConstants;
import org.nnnn.ddd.entity.dddCase;
import org.nnnn.ddd.model.User;
import org.nnnn.ddd.repository.CaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.mail.Session;

@Service
@Profile({ "dev", "uat", "prod" })
public class EmailService {
    @Autowired
    CaseRepository caseRepository;

    @Autowired
    ADSearchService adSearchService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Scheduled(cron = "0 0 0 * * ?")
    public void runScheduledTask() {
        sendOverdueEmail();
    }

    public void sendOverdueEmail() {
        LocalDate today = LocalDate.now();
        LocalDate threeDaysLater = today.plusDays(3);
        List<dddCase> overdueList = caseRepository.findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(today,
                AppConstants.STATUS_COMPLETED);
        List<dddCase> comingDueList = caseRepository.findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(today,
                threeDaysLater,
                AppConstants.STATUS_COMPLETED);
        Map<String, List<dddCase>> overdueMap = overdueList.stream()
                .collect(Collectors.groupingBy(dddCase::getAssignedNm));
        Map<String, List<dddCase>> comingDueMap = comingDueList.stream()
                .collect(Collectors.groupingBy(dddCase::getAssignedNm));
        for (String username : overdueMap.keySet()) {
            log.info(username + " has overdue cases");
            User user = adSearchService.findUser(username);
            if (user != null) {
                sendMail(user, generateEmailBody(user, overdueMap.get(username), comingDueMap.get(username)));
            }
        }
        for (String username : comingDueMap.keySet()) {
            log.info(username + " has coming due cases");
            if (overdueMap.get(username) == null) {
                User user = adSearchService.findUser(username);
                if (user != null) {
                    sendMail(user, generateEmailBody(user, overdueMap.get(username), comingDueMap.get(username)));
                }
            }
        }
    }

    public void sendMail(final User user, final String body) {
        boolean isTest = user.getUsername().startsWith("T-SG") || user.getUsername().startsWith("V-");

        log.info("Sending mail to " + user.getEmail());
        Session session = ((JavaMailSenderImpl) mailSender).getSession();
        // session.setDebug(true);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ddd-Notification@nnnn.org");
        if (isTest) {
            message.setTo("rtcc_support_applications@nnnn.org");
        } else {
            message.setTo(user.getEmail());
            message.setCc("rtcc_support_applications@nnnn.org");
        }
        if (env.acceptsProfiles(Profiles.of("prod"))) {
            message.setSubject("ddd Case Due Notification");
        } else {
            message.setSubject(
                    "[NON-PROD][" + env.getActiveProfiles()[0].toUpperCase() + "] ddd Case Due Notification");
        }
        message.setText(body);
        mailSender.send(message);

    }

    public String generateEmailBody(final User user, List<dddCase> overdueCases, List<dddCase> comingDueCases) {
        boolean isTest = user.getUsername().startsWith("T-SG") || user.getUsername().startsWith("V-");
        StringBuilder body = new StringBuilder();
        body.append(user.getRank() + " " + user.getLastName()).append("\n\n");
        if (overdueCases != null) {
            if (overdueCases.size() > 1) {
                body.append("You have " + overdueCases.size() + " overdue cases:").append("\n\n");
            } else {
                body.append("You have " + overdueCases.size() + " overdue case:").append("\n\n");
            }
            for (dddCase overdue : overdueCases) {
                appendCaseInfo(body, overdue);
                body.append("\n");
            }
            body.append("\n");
        }
        if (comingDueCases != null) {
            if (comingDueCases.size() > 1) {
                body.append("You have " + comingDueCases.size() + " cases coming due:").append("\n\n");
            } else {
                body.append("You have " + comingDueCases.size() + " case coming due:").append("\n\n");
            }
            for (dddCase comingDue : comingDueCases) {
                appendCaseInfo(body, comingDue);
                body.append("\n");
            }
        }
        return body.toString();
    }

    public void appendCaseInfo(StringBuilder body, dddCase caseInfo) {
        body.append("Case ID: " + caseInfo.getId()).append("\n");
        body.append("Arrest ID: " + caseInfo.getArrId()).append("\n");
        if (caseInfo.getAda() != null) {
            body.append("ADA: " + caseInfo.getAda().getJobTitleDesc() + " " + caseInfo.getAda().getFrstNm()
                    + " " + caseInfo.getAda().getLastNm()).append("\n");
        }
        body.append("Request Dt: " + caseInfo.getRequestDt()).append("\n");
        body.append("Case Status: " + caseInfo.getStatus().getStatusDesc()).append("\n");
        body.append("Due Dt: " + caseInfo.getDueDt()).append("\n");
    }
}



=============================

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

public class EmailServiceTest {

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

============================

package org.nnnn.ddd.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.nnnn.ddd.AppConstants;
import org.nnnn.ddd.entity.dddCase;
import org.nnnn.ddd.model.User;
import org.nnnn.ddd.repository.CaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.mail.Session;

@Service
@Profile({ "local" })
public class EmailServiceLocal extends EmailService {
    @Autowired
    private Environment env;

    private static final Logger log = LoggerFactory.getLogger(EmailServiceLocal.class);

    @Scheduled(cron = "0 * * * * ?")
    public void runScheduledTask() {
        sendOverdueEmail();
    }

    @Override
    public void sendMail(final User user, final String body) {
        log.info("Sending mail to " + user.getEmail());
        log.info("[NON-PROD][" + env.getActiveProfiles()[0].toUpperCase() + "] ddd Case Due Notification");
        log.info(body);
    }
}



