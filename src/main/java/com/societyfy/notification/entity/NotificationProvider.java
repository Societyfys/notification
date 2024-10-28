package com.societyfy.notification.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification_provider")
public class NotificationProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    /* e.g., SENDGRID, TWILIO */
    @Column(name = "provider_name")
    String providerName;

    /* 'email', 'sms', 'whatsapp' */
    @Column(name = "notification_delivery_type_id")
    String notificationDeliveryTypeId;

    @Column(name = "api_key")
    String apiKey;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "host")
    String host;

    @Column(name = "port")
    Integer port;

    @Column(name = "active")
    Boolean active;

    @Column(name = "create_at")
    Long createAt;

    @Column(name = "update_at")
    Long updateAt;
}
