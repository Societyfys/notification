package com.societyfy.notification.handler.smsProvider;

import com.societyfy.notification.entity.NotificationProvider;
import com.societyfy.notification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
public abstract class SmsProvider {
    @Setter
    protected NotificationProvider provider;

    private final UserRepository userRepository;

    public void send(String toPhoneNumber, String message) {
        String phoneNumber = provider.getProviderSpecificConfig().get("phone_number").toString();
        send(toPhoneNumber, phoneNumber, message);
    }

    public void send(List<String> toPhoneNumbers, String message) {
        String phoneNumber = provider.getProviderSpecificConfig().get("phone_number").toString();
        send(toPhoneNumbers, phoneNumber, message);
    }

    abstract void send(String toPhoneNumber, String fromPhoneNumber, String message);

    abstract void send(List<String> toPhoneNumbers, String fromPhoneNumber, String message);
}
