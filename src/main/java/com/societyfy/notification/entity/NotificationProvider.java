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
    private String id;
    private String providerName; // e.g., SENDGRID, TWILIO
    private String notificationDeliveryType; // 'email', 'sms', 'whatsapp'
    private String apiKey;
    private String username;
    private String password; // Encrypt in a real-world scenario
    private String host;
    private Integer port;
    private Boolean active;
    private Long createAt;
    private Long updateAt;
}
