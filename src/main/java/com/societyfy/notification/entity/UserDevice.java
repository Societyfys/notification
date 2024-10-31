package com.societyfy.notification.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_device")
public class UserDevice extends CommonDateFields{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "user_id",
            nullable = false)
    String userId;

    @Column(name = "device_token",
            nullable = false,
            unique = true)
    String deviceToken;

    @Column(name = "platform",
            nullable = false)
    @Enumerated(EnumType.STRING)
    Platform platform;

    @Column(name = "status",
            nullable = false)
    @Enumerated(EnumType.STRING)
    DeviceStatus status;

    @Column(name = "last_login")
    Long lastLogin;
}
