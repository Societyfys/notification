package com.societyfy.notification.helper.pushNotificationProvider;

import com.societyfy.notification.entity.NotificationProvider;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PushNotificationProviderFactory {
    private static final Map<String, PushNotificationProvider> pushNotificationProviderMap = new HashMap<>();

    private final FcmPushNotificationProvider fcmPushNotificationProvider;

    @PostConstruct
    void loadDataInMap() {
        pushNotificationProviderMap.put("FIREBASE", fcmPushNotificationProvider);
    }

    public static PushNotificationProvider getPushNotificationProvider(NotificationProvider provider) {
        PushNotificationProvider pushNotificationProvider = pushNotificationProviderMap
                .get(provider.getProviderName().toUpperCase());
        pushNotificationProvider.setProvider(provider);

        return pushNotificationProvider;
    }
}
