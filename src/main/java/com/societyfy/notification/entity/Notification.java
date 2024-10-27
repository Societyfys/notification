package com.societyfy.notification.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name="notification")
public class Notification {
    @Id
    String id;
    String title;
    String message;
    String notificationType;
    String notificationDeliveryType;
    long createAt;
    long updatedAT;
}
