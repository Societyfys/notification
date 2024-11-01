package com.societyfy.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification_provider")
public class NotificationProvider extends CommonDateFields{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    /* e.g., SENDGRID, TWILIO */
    @Column(name = "provider_name")
    String providerName;

    /* 'email', 'sms', 'whatsapp' */
    @Column(name = "notification_delivery_type_id")
    UUID notificationDeliveryTypeId;

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
}
