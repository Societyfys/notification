package com.societyfy.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User extends CommonDateFields{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "role_id")
    UUID roleId;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "email")
    String email;
}
