package com.societyfy.notification.handler;

import com.societyfy.notification.entity.DeliveryType;
import com.societyfy.notification.entity.DeviceStatus;
import com.societyfy.notification.entity.NotificationProvider;
import com.societyfy.notification.entity.User;
import com.societyfy.notification.handler.pushNotificationProvider.PushNotificationProvider;
import com.societyfy.notification.handler.pushNotificationProvider.PushNotificationProviderFactory;
import com.societyfy.notification.repository.UserDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PushNotificationHandler implements NotificationHandler {
    private final ProviderFactory providerFactory;
    private final UserDeviceRepository userDeviceRepository;

    @Override
    public void send(List<User> recipients, String message, String title) {
        List<String> userIds = recipients.stream().map(user -> user.getId().toString()).toList();
        List<String> deviceToekenList = userDeviceRepository
                .findDeviceTokenByUserIdInAndStatus(userIds, DeviceStatus.ACTIVE);
        this.getPushNotificationProvider()
                .send(deviceToekenList, title, message);
    }

    @Override
    public void send(User recipient, String message, String title) {
        String deviceToken = userDeviceRepository
                .findDeviceTokenByUserIdAndStatus(recipient.getId().toString(), DeviceStatus.ACTIVE);
        this.getPushNotificationProvider()
                .send(deviceToken, title, message);
    }

    private PushNotificationProvider getPushNotificationProvider() {
        NotificationProvider provider = providerFactory.getProvider(DeliveryType.PUSH_NOTIFICATION);
        return PushNotificationProviderFactory.getPushNotificationProvider(provider);
    }
}
