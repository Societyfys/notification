package com.societyfy.notification.service;

import com.societyfy.notification.model.CreateNotification;
import com.societyfy.notification.model.GetNotificationsResponse;
import com.societyfy.notification.model.NotificationModel;

public interface NotificationService {
    GetNotificationsResponse getAllNotifications(String correlationId);
    NotificationModel getNotification(String correlationId, String notificationId);
    NotificationModel addNotification(String correlationId, CreateNotification body);
    NotificationModel updateNotification(String correlationId, CreateNotification body);
}
