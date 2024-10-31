package com.societyfy.notification.repository;

import com.societyfy.notification.entity.NotificationDeliveryType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationDeliveryTypeRepository extends JpaRepository<NotificationDeliveryType,String> {
}
