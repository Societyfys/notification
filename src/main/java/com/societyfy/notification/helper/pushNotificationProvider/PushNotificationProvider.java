package com.societyfy.notification.helper.pushNotificationProvider;

import com.societyfy.notification.entity.NotificationProvider;
import lombok.Setter;

import java.util.List;

@Setter
public abstract class PushNotificationProvider {
    protected NotificationProvider provider;

    public abstract void send(String clientToken, String title, String message);
    public abstract void send(List<String> clientTokens, String title, String message);
    public abstract void sendByTopic(String topic, String title, String message);
}
