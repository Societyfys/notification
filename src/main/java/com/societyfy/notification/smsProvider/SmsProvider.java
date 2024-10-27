package com.societyfy.notification.smsProvider;

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

    public void sent(String toPhoneNumber, String message){
        User admin = userRepository.findAdmin();
        sent(toPhoneNumber, admin.getPhoneNumber(),message);
    }

    public void sent(List<String> toPhoneNumbers,String message){
        User admin = userRepository.findAdmin();
        sent(toPhoneNumbers, admin.getPhoneNumber(),message);
    }

    abstract void sent(String toPhoneNumber,String fromPhoneNumber,String message);
    abstract void sent(List<String> toPhoneNumbers, String fromPhoneNumber, String message);
}
