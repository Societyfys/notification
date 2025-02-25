package com.societyfy.notification.service;

import com.societyfy.notification.entity.Notification;
import com.societyfy.notification.entity.NotificationDeliveryType;
import com.societyfy.notification.entity.NotificationType;
import com.societyfy.notification.exception.SocietyfyException;
import com.societyfy.notification.model.CreateNotification;
import com.societyfy.notification.model.GetNotificationsResponse;
import com.societyfy.notification.model.NotificationModel;
import com.societyfy.notification.repository.NotificationDeliveryTypeRepository;
import com.societyfy.notification.repository.NotificationRepository;
import com.societyfy.notification.repository.NotificationTypeRepository;
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
    private final NotificationDeliveryTypeRepository notificationDeliveryTypeRepository;
    private final NotificationTypeRepository notificationTypeRepository;

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

    @Override
    public NotificationModel addNotification(String correlationId, CreateNotification body) {
        NotificationType notificationType = this.findNotificationType(correlationId, body);

        NotificationDeliveryType notificationDeliveryType = this.findNotificationDeliveryType(correlationId, body);

        Notification notification = new Notification(null,
                body.getTitle(),
                body.getEventType(),
                body.getMessage(),
                notificationType,
                notificationDeliveryType);

        notificationRepository.save(notification);

        return this.convertNotificationToResponseModel(notification);
    }

    @Override
    public NotificationModel updateNotification(String correlationId, CreateNotification body) {
        if (body.getId() == null) {
            throw new SocietyfyException(
                    HttpStatus.BAD_REQUEST,
                    NotificationExceptionConstants.NOTIFICATION_NOT_FOUND,
                    env.getRequiredProperty(NotificationExceptionConstants.NOTIFICATION_NOT_FOUND),
                    correlationId);
        }

        Notification notification = notificationRepository
                .findById(UUID.fromString(body.getId()))
                .orElseThrow(() -> new SocietyfyException(
                        HttpStatus.NOT_FOUND,
                        NotificationExceptionConstants.NOTIFICATION_NOT_FOUND,
                        env.getRequiredProperty(NotificationExceptionConstants.NOTIFICATION_NOT_FOUND),
                        correlationId));

        if(body.getTitle()!=null)
            notification.setTitle(body.getTitle());
        if(body.getMessage()!=null)
            notification.setMessage(body.getMessage());
        if(body.getEventType()!=null)
            notification.setEventType(body.getEventType());
        if(body.getNotificationTypeId()!=null){
            NotificationType notificationType = this.findNotificationType(correlationId, body);
            notification.setNotificationType(notificationType);
        }
        if(body.getNotificationDeliveryTypeId()!=null){
            NotificationDeliveryType notificationDeliveryType = this.findNotificationDeliveryType(correlationId, body);
            notification.setNotificationDeliveryType(notificationDeliveryType);
        }

        notificationRepository.save(notification);

        return this.convertNotificationToResponseModel(notification);
    }


    private NotificationModel convertNotificationToResponseModel(Notification notification) {
        return new NotificationModel()
                .id(notification.getId().toString())
                .message(notification.getMessage())
                .title(notification.getTitle())
                .eventType(notification.getEventType())
                .notificationDeliveryType(notification
                        .getNotificationDeliveryType()
                        .getDeliveryType()
                        .toString())
                .notificationType(notification
                        .getNotificationType()
                        .getType())
                .updateAt(notification.getUpdateAt())
                .createAt(notification.getCreateAt());
    }

    private NotificationType findNotificationType(String correlationId, CreateNotification body) {
        return notificationTypeRepository
                .findById(UUID.fromString(body.getNotificationTypeId()))
                .orElseThrow(() -> new SocietyfyException(
                        HttpStatus.NOT_FOUND,
                        NotificationExceptionConstants.NOTIFICATION_TYPE_NOT_FOUNT,
                        env.getRequiredProperty(NotificationExceptionConstants.NOTIFICATION_TYPE_NOT_FOUNT),
                        correlationId));
    }

    private NotificationDeliveryType findNotificationDeliveryType(String correlationId, CreateNotification body) {
        return notificationDeliveryTypeRepository
                .findById(UUID.fromString(body.getNotificationDeliveryTypeId()))
                .orElseThrow(() -> new SocietyfyException(
                        HttpStatus.NOT_FOUND,
                        NotificationExceptionConstants.NOTIFICATION_DELIVERY_TYPE_NOT_FOUNT,
                        env.getRequiredProperty(NotificationExceptionConstants.NOTIFICATION_DELIVERY_TYPE_NOT_FOUNT),
                        correlationId));
    }
}
