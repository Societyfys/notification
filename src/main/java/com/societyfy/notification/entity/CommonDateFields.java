package com.societyfy.notification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class CommonDateFields {
    @Column(name = "create_at")
    Long createAt;

    @Column(name = "update_at")
    Long updateAt;
}
