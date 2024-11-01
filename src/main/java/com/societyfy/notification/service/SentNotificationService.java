package com.societyfy.notification.service;

import com.societyfy.notification.model.SentNotificationRequest;

public interface SentNotificationService {
    void sentNotification(String correlationId, SentNotificationRequest body);
}
