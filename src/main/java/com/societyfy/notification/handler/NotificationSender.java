package com.societyfy.notification.handler;

import com.societyfy.notification.entity.*;
import com.societyfy.notification.repository.NotificationRecipientRepository;
import com.societyfy.notification.utils.MessageFormatter;
import com.societyfy.notification.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationSender {
    private final NotificationRecipientRepository recipientRepository;

    public void send(
            User user,
            Notification notification,
            Map<String, Object> variables) {

        variables.putAll(Utils.convertToSnakeCaseMap(user));
        String formatted = MessageFormatter.format(notification.getMessage(), variables);
        DeliveryType deliveryType = notification.getNotificationDeliveryType().getDeliveryType();
        NotificationHandler notificationHandler = NotificationHandlerFactory.getNotificationHandler(deliveryType);
        notificationHandler.send(user, formatted, notification.getTitle());

        if (DeliveryType.PUSH_NOTIFICATION.equals(deliveryType))
            saveNotificationRecipient(
                    user.getId(),
                    notification.getId(),
                    formatted);
    }

    public void send(List<User> users, Notification notification) {
        DeliveryType deliveryType = notification.getNotificationDeliveryType().getDeliveryType();
        NotificationHandler notificationHandler = NotificationHandlerFactory.getNotificationHandler(deliveryType);
        notificationHandler.send(users, notification.getMessage(), notification.getTitle());

        if (DeliveryType.PUSH_NOTIFICATION.equals(deliveryType))
            saveNotificationRecipient(
                    users.stream().map(User::getId).toList(),
                    notification.getId(),
                    notification.getMessage());
    }

    private void saveNotificationRecipient(
            UUID userId,
            UUID notificationId,
            String message) {

        NotificationRecipient recipient = new NotificationRecipient();
        recipient.setNotificationId(notificationId.toString());
        recipient.setStatus(NotificationRecipientStatus.UNREAD);
        recipient.setMessage(message);
        recipient.setSentAt(Instant.now().toEpochMilli());
        recipient.setUserId(userId.toString());
        recipientRepository.save(recipient);
    }

    private void saveNotificationRecipient(
            List<UUID> userIds,
            UUID notificationId,
            String message) {

        List<NotificationRecipient> recipients = new ArrayList<>();

        for (UUID userId : userIds) {
            NotificationRecipient recipient = new NotificationRecipient();
            recipient.setNotificationId(notificationId.toString());
            recipient.setStatus(NotificationRecipientStatus.UNREAD);
            recipient.setMessage(message);
            recipient.setSentAt(Instant.now().toEpochMilli());
            recipient.setUserId(userId.toString());
            recipients.add(recipient);
        }

        recipientRepository.saveAll(recipients);
    }
}
