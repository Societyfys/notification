package com.societyfy.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "notification_type")
public class NotificationType extends CommonDateFields{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "type")
    String type;

    @Column(name = "description")
    String description;
}
