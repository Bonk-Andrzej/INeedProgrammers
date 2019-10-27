package com.bonkAndrzej.iNeedProgrammers.email;

import com.bonkAndrzej.iNeedProgrammers.user.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Slf4j
@Service @Validated
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final MessageSource messageSource;
    private final SpringTemplateEngine templateEngine;


    private static final String USER = "user";
    private static final String BASE_URL = "baseUrl";
    private static final String BASE_URL_PATH = "http://127.0.0.1:8080";
    private static final String EMAIL_FROM = "INeedProgrammers@localhost";


    public void sendActivationEmail(@NotNull User user) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "email/activationEmail", "email.activation.title", BASE_URL_PATH);
    }

    public void sendPasswordResetEMail(@NotNull User user) {
        log.debug("Sending reset email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "email/passwordResetEmail", "email.reset.title", BASE_URL_PATH);
    }

    private void sendEmailFromTemplate(User mailReceiver, String templateName, String titleKey, String baseUrl) {
        Locale locale = Locale.forLanguageTag("en");
        Context context = new Context(locale);
        context.setVariable(USER, mailReceiver);
        context.setVariable(BASE_URL, baseUrl);
        String content = templateEngine.process(templateName, context);
        String subject = messageSource.getMessage(titleKey, null, locale);
        sendEmail(mailReceiver.getEmail(), subject, content, false, true);
    }

    private void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                isMultipart, isHtml, to, subject, content);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom(EMAIL_FROM);
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", to);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.warn("Email could not be sent to user '{}'", to, e);
            } else {
                log.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
            }
        }
    }


}
