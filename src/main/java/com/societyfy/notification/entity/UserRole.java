package com.societyfy.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole extends CommonDateFields{
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
}
