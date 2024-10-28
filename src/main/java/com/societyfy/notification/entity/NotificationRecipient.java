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

    public NotificationRecipient(String userId,String notificationId){
        this.notificationId=notificationId;
        this.userId=userId;
        this.status=NotificationRecipientStatus.UNREAD;
        this.sentAt= Instant.now().toEpochMilli();
    }
}
