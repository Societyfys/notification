package com.societyfy.notification.service;

import com.societyfy.notification.entity.Notification;
import com.societyfy.notification.entity.User;
import com.societyfy.notification.exception.SocietyfyException;
import com.societyfy.notification.handler.NotificationSender;
import com.societyfy.notification.model.SentNotificationRequest;
import com.societyfy.notification.repository.NotificationRepository;
import com.societyfy.notification.repository.UserRepository;
import com.societyfy.notification.utils.NotificationExceptionConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SentNotificationServiceImpl implements SentNotificationService{
    private final NotificationSender notificationSender;
    private final Environment env;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(SentNotificationServiceImpl.class);

    @Override
    public void sentNotification(String correlationId, SentNotificationRequest body) {
        Notification notification = notificationRepository
                .findById(UUID.fromString(body.getNotificationId()))
                .orElseThrow(() -> new SocietyfyException(
                        HttpStatus.NOT_FOUND,
                        NotificationExceptionConstants.NOTIFICATION_NOT_FOUND,
                        env.getRequiredProperty(NotificationExceptionConstants.NOTIFICATION_NOT_FOUND),
                        correlationId));

        UUID id = UUID.fromString(body.getUserId());
        User user = userRepository.findById(id)
                .orElseThrow(() -> new SocietyfyException(
                        HttpStatus.NOT_FOUND,
                        NotificationExceptionConstants.USER_NOT_FOUND,
                        env.getRequiredProperty(NotificationExceptionConstants.USER_NOT_FOUND),
                        correlationId));

        notificationSender.send(user,notification,body.getVariables());
        log.info("notification sent");
    }
}
