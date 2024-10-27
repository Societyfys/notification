package com.societyfy.notification.repository;

import com.societyfy.notification.entity.NotificationRecipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRecipientRepository extends JpaRepository<NotificationRecipient,String> {

}
