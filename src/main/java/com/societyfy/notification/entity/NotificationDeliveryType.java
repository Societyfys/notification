package com.societyfy.notification.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification_delivery_type")
public class NotificationDeliveryType extends CommonDateFields {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_type",
            unique = true,
            nullable = false)
    DeliveryType deliveryType;

    @Column(name = "description")
    String description;
}
