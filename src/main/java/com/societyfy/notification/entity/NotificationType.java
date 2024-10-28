package com.societyfy.notification.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "notification_type")
public class NotificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "type")
    String type;

    @Column(name = "description")
    String description;

    @Column(name = "create_at")
    Long createAt;

    @Column(name = "update_at")
    Long updateAt;
}
