package com.societyfy.notification.helper;

import com.societyfy.notification.entity.User;

import java.util.List;

public interface NotificationHandler {
    void send(List<User> recipients, String message, String title);

    void send(User recipient, String message, String title);
}
