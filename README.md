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
@Profile({ "local", "dev", "uat" })
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
    //@Scheduled(cron = "0 * * * * ?")
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
            User user = adSearchService.findUser(username);
            if (user != null) {
                sendMail(user, generateEmailBody(user, overdueMap.get(username), comingDueMap.get(username)));
            }
        }
        for (String username : comingDueMap.keySet()) {
            if (overdueMap.get(username) == null) {
                User user = adSearchService.findUser(username);
                if (user != null) {
                    sendMail(user, generateEmailBody(user, overdueMap.get(username), comingDueMap.get(username)));
                }
            }
        }
    }

    private void sendMail(final User user, final String body) {
        boolean isTest = user.getUsername().startsWith("T-SG") || user.getUsername().startsWith("V-");
        if (env.acceptsProfiles(Profiles.of("local"))) {
            log.info("Sending mail to " + user.getEmail());
            log.info(body);
        } else {
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
                message.setSubject("[NON-PROD] ddd Case Due Notification");
            }
            message.setText(body);
            mailSender.send(message);
        }
    }

    private String generateEmailBody(final User user, List<dddCase> overdueCases, List<dddCase> comingDueCases) {
        boolean isTest = user.getUsername().startsWith("T-SG") || user.getUsername().startsWith("V-");
        StringBuilder body = new StringBuilder();
        if (!isTest) {
            body.append(user.getRank() + " " + user.getLastName());
        }
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
        }
        if (comingDueCases != null) {
            if (comingDueCases.size() > 1) {
                body.append("\n").append("You have " + comingDueCases.size() + " cases coming due:").append("\n\n");
            } else {
                body.append("\n").append("You have " + comingDueCases.size() + " case coming due:").append("\n\n");
            }
            for (dddCase comingDue : comingDueCases) {
                appendCaseInfo(body, comingDue);
                body.append("\n");
            }
        }
        return body.toString();
    }

    private void appendCaseInfo(StringBuilder body, dddCase caseInfo) {
        body.append("Case ID: " + caseInfo.getId()).append("\n");
        body.append("Arrest ID: " + caseInfo.getArrId()).append("\n");
        if (caseInfo.getAda() != null) {
            body.append("ADA: " + caseInfo.getAda().getJobTitleDesc() + " " + caseInfo.getAda().getFrstNm()
                    + " " + caseInfo.getAda().getLastNm()).append("\n");
        }
        body.append("Request Dt: " + caseInfo.getRequestDt()).append("\n");
        body.append("Due Dt: " + caseInfo.getDueDt()).append("\n");
    }
}
