package com.societyfy.notification.repository;

import com.societyfy.notification.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, UUID> {
    
}
