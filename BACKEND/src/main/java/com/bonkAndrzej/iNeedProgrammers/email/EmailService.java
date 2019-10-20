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

    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                isMultipart, isHtml, to, subject, content);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom("bonkandrzej09@gmail.com");
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

    public void sendOrderListEmail(@NotNull User mailReceiver) {
        Locale locale = Locale.forLanguageTag("en");
        Context context = new Context(locale);
        context.setVariable(USER, mailReceiver);
//        context.setVariable("orderItemList", orderItemsDto);
//        context.setVariable("shopOrder", shopOrder);
        String content = templateEngine.process("email/orderListEmail", context);
        String subject = messageSource.getMessage("email.order.list.notification", null, locale);
        sendEmail(mailReceiver.getEmail(), subject, content, false, true);
    }


    public void sendPasswordResetEMail(@NotNull User mailReceiver) {
        Locale locale = Locale.forLanguageTag("en");
        Context context = new Context(locale);
        context.setVariable(USER, mailReceiver);
//        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
        String content = templateEngine.process("email/passwordResetEmail", context);
        String subject = messageSource.getMessage("email.reset.title", null, locale);
        sendEmail(mailReceiver.getEmail(), subject, content, false, true);
    }

    public void sendEmailFromTemplate(User mailReceiver, String templateName, String titleKey) {
        Locale locale = Locale.forLanguageTag("en");
        Context context = new Context(locale);
        context.setVariable(USER, mailReceiver);
//        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
        String content = templateEngine.process(templateName, context);
        String subject = messageSource.getMessage(titleKey, null, locale);
        sendEmail(mailReceiver.getEmail(), subject, content, false, true);
    }
}
