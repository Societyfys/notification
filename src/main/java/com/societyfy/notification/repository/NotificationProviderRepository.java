package com.societyfy.notification.repository;

import com.societyfy.notification.entity.DeliveryType;
import com.societyfy.notification.entity.NotificationProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface NotificationProviderRepository extends JpaRepository<NotificationProvider, UUID> {
    @Query("select n from NotificationProvider n " +
            "left join NotificationDeliveryType delivery on delivery.id=n.notificationDeliveryTypeId " +
            "where delivery.deliveryType = ?1 and n.active = ?2")
    NotificationProvider findByNotificationDeliveryTypeAndActive(DeliveryType deliveryType, Boolean active);
}
