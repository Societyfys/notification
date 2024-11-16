package com.societyfy.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "role_permission")
public class RolePermission extends CommonDateFields{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "role_id")
    UUID roleId;

    @Column(name = "permission_id")
    UUID permissionId;
}
