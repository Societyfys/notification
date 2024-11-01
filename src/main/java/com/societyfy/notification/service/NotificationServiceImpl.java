package com.societyfy.notification.service;

import com.societyfy.notification.entity.Notification;
import com.societyfy.notification.exception.SocietyfyException;
import com.societyfy.notification.model.GetNotificationsResponse;
import com.societyfy.notification.model.NotificationModel;
import com.societyfy.notification.repository.NotificationRepository;
import com.societyfy.notification.utils.NotificationExceptionConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final Environment env;

    @Override
    public GetNotificationsResponse getAllNotifications(String correlationId) {
        List<Notification> all = notificationRepository.findAll();
        List<NotificationModel> notificationModels = all.stream()
                .map(this::convertNotificationToResponseModel)
                .toList();

        return new GetNotificationsResponse()
                .notifications(notificationModels);
    }

    @Override
    public NotificationModel getNotification(String correlationId, String notificationId) {
        Optional<Notification> byId = notificationRepository.findById(UUID.fromString(notificationId));
        Notification notification = byId.orElseThrow(() ->
                new SocietyfyException(
                        HttpStatus.NOT_FOUND,
                        NotificationExceptionConstants.NOTIFICATION_NOT_FOUND,
                        env.getRequiredProperty(NotificationExceptionConstants.NOTIFICATION_NOT_FOUND),
                        correlationId));
        return convertNotificationToResponseModel(notification);
    }

    private NotificationModel convertNotificationToResponseModel(Notification notification) {
        return new NotificationModel()
                .id(notification.getId().toString())
                .message(notification.getMessage())
                .title(notification.getTitle())
                .notificationDeliveryType(notification
                        .getNotificationDeliveryType()
                        .getDeliveryType()
                        .toString())
                .notificationType(notification.getNotificationType().getType())
                .updateAt(notification.getUpdateAt())
                .createAt(notification.getCreateAt());
    }
}
