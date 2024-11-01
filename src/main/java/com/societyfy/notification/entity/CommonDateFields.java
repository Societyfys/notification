package com.societyfy.notification.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.Instant;

@Data
public abstract class CommonDateFields {
    @Column(name = "create_at")
    Long createAt;

    @Column(name = "update_at")
    Long updatedAt;
}
