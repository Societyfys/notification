package com.societyfy.notification.handler;

import com.societyfy.notification.entity.User;

import java.util.List;

public class PushNotificationRecipient implements NotificationHandler {
    @Override
    public void send(List<User> recipients, String message, String title) {

    }

    @Override
    public void send(User recipient, String message, String title) {

    }
}
