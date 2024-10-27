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
    String notificationId;
    String userId;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    NotificationRecipientStatus status;

    Long sentAt;
    Long readAt;

    public NotificationRecipient(String userId,String notificationId){
        this.notificationId=notificationId;
        this.userId=userId;
        this.status=NotificationRecipientStatus.UNREAD;
        this.sentAt= Instant.now().toEpochMilli();
    }
}
