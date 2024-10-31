package com.societyfy.notification.handler.smsProvider;

import com.societyfy.notification.entity.NotificationProvider;
import com.societyfy.notification.entity.User;
import com.societyfy.notification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
public abstract class SmsProvider {
    @Setter
    protected NotificationProvider provider;

    private final UserRepository userRepository;

    public void send(String toPhoneNumber, String message){
        User admin = userRepository.findAdmin();
        send(toPhoneNumber, admin.getPhoneNumber(),message);
    }

    public void send(List<String> toPhoneNumbers,String message){
        User admin = userRepository.findAdmin();
        send(toPhoneNumbers, admin.getPhoneNumber(),message);
    }

    abstract void send(String toPhoneNumber,String fromPhoneNumber,String message);
    abstract void send(List<String> toPhoneNumbers, String fromPhoneNumber, String message);
}
