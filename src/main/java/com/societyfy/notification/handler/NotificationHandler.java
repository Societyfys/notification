package com.societyfy.notification.handler;

import com.societyfy.notification.entity.Notification;
import com.societyfy.notification.entity.NotificationRecipient;
import com.societyfy.notification.entity.User;

import java.util.List;

public interface NotificationHandler {
    void send(List<User> recipients, Notification notification);
    void send(User recipient,Notification notification);
}
