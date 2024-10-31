package com.societyfy.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification_recipient")
public class NotificationRecipient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "notification_id")
    String notificationId;

    @Column(name = "message")
    String message;

    @Column(name = "user_id")
    String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",
            nullable = false)
    NotificationRecipientStatus status;

    @Column(name = "sent_at")
    Long sentAt;

    @Column(name = "read_at")
    Long readAt;
}
