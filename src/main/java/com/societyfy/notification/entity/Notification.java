package com.societyfy.notification.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "event_type", unique = true, nullable = false)
    String eventType;

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
