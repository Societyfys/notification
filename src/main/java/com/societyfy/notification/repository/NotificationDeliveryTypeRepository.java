package com.societyfy.notification.repository;

import com.societyfy.notification.entity.DeliveryType;
import com.societyfy.notification.entity.NotificationDeliveryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationDeliveryTypeRepository extends JpaRepository<NotificationDeliveryType,String> {
}
