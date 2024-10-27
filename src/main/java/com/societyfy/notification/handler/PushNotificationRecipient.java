package com.societyfy.notification.handler;

import com.societyfy.notification.entity.Notification;
import com.societyfy.notification.entity.NotificationRecipient;
import com.societyfy.notification.entity.User;

import java.util.List;

public class PushNotificationRecipient implements NotificationHandler{
    @Override
    public void send(List<User> recipients, Notification notification) {

    }

    @Override
    public void send(User recipient, Notification notification) {

    }
}
