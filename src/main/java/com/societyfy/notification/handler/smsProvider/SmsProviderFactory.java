package com.societyfy.notification.handler.smsProvider;

import com.societyfy.notification.entity.NotificationProvider;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SmsProviderFactory {
    private static final Map<String,SmsProvider> smsProviderMap=new HashMap<>();

    private final TwilioSmsProvider twilioSmsProvider;

    @PostConstruct
    void loadDataInMap(){
        smsProviderMap.put("TWILIO",twilioSmsProvider);
    }

    public static SmsProvider getSmsProvider(NotificationProvider provider) {
        SmsProvider smsProvider = smsProviderMap.get(provider.getProviderName().toUpperCase());
        smsProvider.setProvider(provider);

        return smsProvider;
    }
}
