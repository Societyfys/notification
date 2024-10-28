package com.societyfy.notification.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "role",
            unique = true,
            nullable = false)
    @Enumerated(EnumType.STRING)
    Role role;

    @Column(name = "description")
    String description;

    @Column(name = "create_at")
    Long createAt;

    @Column(name = "update_at")
    Long updateAt;
}
