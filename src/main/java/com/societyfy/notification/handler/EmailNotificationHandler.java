package com.societyfy.notification.handler;

import com.societyfy.notification.entity.DeliveryType;
import com.societyfy.notification.entity.NotificationProvider;
import com.societyfy.notification.entity.User;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailNotificationHandler implements NotificationHandler {
    private final ProviderFactory providerFactory;

    @Override
    public void send(List<User> recipients, String message, String title) {

    }

    @Override
    public void send(User recipient, String message, String title) {
        try {
            JavaMailSenderImpl mailSender = getMailSender();

            /* Create and send the email */
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient.getEmail()));
            mimeMessage.setSubject(title);
            mimeMessage.setContent(message, "text/html; charset=utf-8");

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("Error : " + ExceptionUtils.getStackTrace(e));
        }
    }

    private JavaMailSenderImpl getMailSender() {
        NotificationProvider provider = providerFactory.getProvider(DeliveryType.EMAIL);

        /* Create a JavaMailSender with the fetched configuration */
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(provider.getHost());
        mailSender.setPort(provider.getPort());
        mailSender.setUsername(provider.getUsername());
        mailSender.setPassword(provider.getPassword());

        /* Set additional properties (e.g., SMTP authentication) */
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);

        return mailSender;
    }
}
