package com.societyfy.notification.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name="notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "title")
    String title;

    @Column(name = "message")
    String message;

    @Column(name = "notification_type_id")
    String notificationTypeId;

    @Column(name = "notification_delivery_type_id")
    String notificationDeliveryTypeId;

    @Column(name = "create_at")
    Long createAt;

    @Column(name = "update_at")
    Long updatedAT;
}
