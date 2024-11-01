package com.societyfy.notification.repository;

import com.societyfy.notification.entity.DeviceStatus;
import com.societyfy.notification.entity.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface UserDeviceRepository extends JpaRepository<UserDevice, UUID> {
    @Query("select u.deviceToken from UserDevice u " +
            "where u.userId = :userId " +
            "and u.status = :status")
    String findDeviceTokenByUserIdAndStatus(String userId, DeviceStatus status);

    @Query("select u.deviceToken from UserDevice u " +
            "where u.userId in :userIds " +
            "and u.status = :status")
    List<String> findDeviceTokenByUserIdInAndStatus(Collection<String> userIds, DeviceStatus status);
}
