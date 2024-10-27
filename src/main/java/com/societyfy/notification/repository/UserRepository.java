package com.societyfy.notification.repository;

import com.societyfy.notification.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,String> {
    @Query("select u from User u " +
            "left join UserRole ur on ur.id=u.roleId " +
            "where ur.role = 'ADMIN'")
    User findAdmin();
}
