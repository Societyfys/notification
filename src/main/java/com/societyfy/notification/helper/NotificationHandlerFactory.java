package com.societyfy.notification.helper;

import com.societyfy.notification.entity.DeliveryType;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class NotificationHandlerFactory {
    private final SmsNotificationHandler smsNotificationHandler;
    private final PushNotificationHandler pushNotificationHandler;
    private final EmailNotificationHandler emailNotificationHandler;
    private static final Map<DeliveryType, NotificationHandler> notificationHandlerMap = new HashMap<>();

    @PostConstruct
    void init(){
        notificationHandlerMap.put(DeliveryType.SMS, smsNotificationHandler);
        notificationHandlerMap.put(DeliveryType.EMAIL, emailNotificationHandler);
        notificationHandlerMap.put(DeliveryType.PUSH_NOTIFICATION, pushNotificationHandler);
    }

    public static NotificationHandler getNotificationHandler(DeliveryType deliveryType) {
        return notificationHandlerMap.get(deliveryType);
    }
}
