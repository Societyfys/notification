package com.societyfy.notification.helper;

import com.societyfy.notification.entity.DeliveryType;
import com.societyfy.notification.entity.NotificationProvider;
import com.societyfy.notification.repository.NotificationProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProviderFactory {
    private final NotificationProviderRepository repository;

    public NotificationProvider getProvider(DeliveryType deliveryType) {
        NotificationProvider providers =
                repository.findByNotificationDeliveryTypeAndActive(deliveryType, true);

        if (providers == null) {
            throw new RuntimeException("No active provider found for notification type: " + deliveryType);
        }

        return providers;
    }
}
