package com.societyfy.notification.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "notification")
public class Notification extends CommonDateFields {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

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
