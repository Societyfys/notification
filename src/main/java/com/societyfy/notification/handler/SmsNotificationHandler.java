package com.societyfy.notification.handler;

import com.societyfy.notification.entity.DeliveryType;
import com.societyfy.notification.entity.Notification;
import com.societyfy.notification.entity.NotificationProvider;
import com.societyfy.notification.entity.User;
import com.societyfy.notification.smsProvider.SmsProvider;
import com.societyfy.notification.smsProvider.SmsProviderFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SmsNotificationHandler implements NotificationHandler{
    private final ProviderFactory providerFactory;

    @Override
    public void send(List<User> recipients, Notification notification) {

    }

    @Override
    public void send(User recipient, Notification notification) {
        SmsProvider smsProvider = getSmsProvider();
        smsProvider.sent(recipient.getPhoneNumber(),notification.getMessage());
    }

    private SmsProvider getSmsProvider(){
        NotificationProvider provider = providerFactory.getProvider(DeliveryType.SMS);
        return SmsProviderFactory.getSmsProvider(provider);
    }
}
