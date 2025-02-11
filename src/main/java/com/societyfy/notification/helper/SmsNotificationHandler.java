package com.societyfy.notification.helper;

import com.societyfy.notification.entity.DeliveryType;
import com.societyfy.notification.entity.NotificationProvider;
import com.societyfy.notification.entity.User;
import com.societyfy.notification.helper.smsProvider.SmsProvider;
import com.societyfy.notification.helper.smsProvider.SmsProviderFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SmsNotificationHandler implements NotificationHandler {
    private final ProviderFactory providerFactory;

    @Override
    public void send(List<User> recipients, String message, String title) {

    }

    @Override
    public void send(User recipient, String message, String title) {
        SmsProvider smsProvider = getSmsProvider();
        smsProvider.send(recipient.getPhoneNumber(), message);
    }

    private SmsProvider getSmsProvider() {
        NotificationProvider provider = providerFactory.getProvider(DeliveryType.SMS);
        return SmsProviderFactory.getSmsProvider(provider);
    }
}
