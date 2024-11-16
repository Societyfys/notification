package com.societyfy.notification.repository;

import com.societyfy.notification.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface RolePermissionRepository extends JpaRepository<RolePermission, UUID> {

    @Query(value = "select pm.name from role_permission rp " +
            "left join permission pm on rp.permission_id = pm.id " +
            "left join role r on r.id = rp.role_id " +
            "where r.name in :roles",nativeQuery = true)
    Set<String> findRolePermission(Collection<String> roles);
}
