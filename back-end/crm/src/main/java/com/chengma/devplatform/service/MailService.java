package com.chengma.devplatform.service;

import com.chengma.devplatform.domain.User;
import com.chengma.devplatform.service.dto.TlbAccountDTO;
import io.github.jhipster.config.JHipsterProperties;
import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * Service for sending emails.
 * <p>
 * We use the @Async annotation to send emails asynchronously.
 * </p>
 */
@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    private static final String USER = "user";

    private static final String BASE_URL = "baseUrl";

    private static final String ACCOUNT = "account";

    private final JHipsterProperties jHipsterProperties;

    private final JavaMailSender javaMailSender;

    private final MessageSource messageSource;

    private final SpringTemplateEngine templateEngine;

    public MailService(JHipsterProperties jHipsterProperties, JavaMailSender javaMailSender,
            MessageSource messageSource, SpringTemplateEngine templateEngine) {

        this.jHipsterProperties = jHipsterProperties;
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
            isMultipart, isHtml, to, subject, content);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setTo(to);
            message.setFrom(jHipsterProperties.getMail().getFrom());
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

    @Async
    public void sendEmailFromTemplate(User user, String templateName, String titleKey) {
        sendEmailFromTemplate(user, null, templateName, titleKey);
    }

    @Async
    public void sendEmailFromTemplate(User user, TlbAccountDTO tlbAccountDTO, String templateName, String titleKey) {
        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable(USER, user);
        if(tlbAccountDTO != null) {
            context.setVariable(ACCOUNT, tlbAccountDTO);
        }
        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
        String content = templateEngine.process(templateName, context);
        String subject = messageSource.getMessage(titleKey, null, locale);
        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendActivationEmail(User user) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "activationEmail", "email.activation.title");
    }

    @Async
    public void sendCreationEmail(User user) {
        log.debug("Sending creation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "creationEmail", "email.activation.title");
    }

    @Async
    public void sendPasswordResetMail(User user) {
        log.debug("Sending password reset email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "passwordResetEmail", "email.reset.title");
    }

    @Async
    public void sendCreateUserMail(User user) {
        log.debug("Sending create account email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "createAccountEmail", "email.createAccount.title");
    }

    @Async
    public void sendFundInMail(User user, TlbAccountDTO tlbAccountDTO) {
        log.debug("Sending in amount successful email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, tlbAccountDTO, "fundInEmail", "email.fundIn.title");
    }

    @Async
    public void sendFundOutMail(User user, TlbAccountDTO tlbAccountDTO) {
        log.debug("Sending out amount successful email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, tlbAccountDTO, "fundOutEmail", "email.fundOut.title");
    }

    @Async
    public void sendFundInnerMail(User user, TlbAccountDTO tlbAccountDTO) {
        log.debug("Sending inner amount successful email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, tlbAccountDTO, "fundInnerEmail", "email.fundInner.title");
    }

    @Async
    public void sendRejectFundInMail(User user, TlbAccountDTO tlbAccountDTO) {
        log.debug("Sending reject in amount email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, tlbAccountDTO, "rejectFundInEmail", "email.reject.fundIn.title");
    }

    @Async
    public void sendRejectFundOutMail(User user, TlbAccountDTO tlbAccountDTO) {
        log.debug("Sending reject out amount email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, tlbAccountDTO, "rejectFundOutEmail", "email.reject.fundOut.title");
    }

    @Async
    public void sendRejectFundInnerMail(User user, TlbAccountDTO tlbAccountDTO) {
        log.debug("Sending reject inner amount email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, tlbAccountDTO, "rejectFundInnerEmail", "email.reject.fundInner.title");
    }

    @Async
    public void sendCreateTlbAccountMail(User user, TlbAccountDTO tlbAccountDTO) {
        log.debug("Sending create account email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, tlbAccountDTO, "createTlbAccountEmail", "email.createTlbAccount.title");
    }

    @Async
    public void sendResetPasswordMail(User user) {
        log.debug("Sending reset password email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "resetPasswordEmail", "email.resetPassword.title");
    }

    @Async
    public void sendResetTlbAccountPasswordMail(User user, TlbAccountDTO tlbAccountDTO) {
        log.debug("Sending reset tlbAccount password email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, tlbAccountDTO, "resetTlbAccountPasswordEmail", "email.resetTlbAccountPassword.title");
    }

}
