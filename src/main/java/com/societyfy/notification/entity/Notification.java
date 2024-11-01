package com.societyfy.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "notification")
public class Notification extends CommonDateFields {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "title",
            unique = true,
            nullable = false)
    String title;

    @Column(name = "message")
    String message;

    @ManyToOne
    @JoinColumn(name = "notification_type_id",
            nullable = false)
    NotificationType notificationType;

    @ManyToOne
    @JoinColumn(name = "notification_delivery_type_id",
            nullable = false)
    NotificationDeliveryType notificationDeliveryType;
}
