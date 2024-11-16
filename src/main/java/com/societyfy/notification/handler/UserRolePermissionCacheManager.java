package com.societyfy.notification.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserRolePermissionCacheManager {
    private final CacheManager<String, String> cacheManager;

    public boolean isTokenExist(String userId, String token) {
        String userTokenKey = "user:" + userId + ":tokens";
        return cacheManager.hashOperationsHasKey(userTokenKey, token);
    }

    // Retrieve user roles
    public Set<String> getUserRoles(String userId) {
        String userRolesKey = "user:" + userId + ":roles";
        return cacheManager.hashOperationsKeys(userRolesKey);
    }

    public void storeUserToken(String userId, String token) {
        String userTokenKey = "user:" + userId + ":tokens";
        cacheManager.hashOperationSave(userTokenKey, token, System.currentTimeMillis());
    }

    // Store user roles
    public void storeUserRoles(String userId, Set<String> roles) {
        String userRolesKey = "user:" + userId + ":roles";
        for (String role : roles) {
            cacheManager.hashOperationSave(userRolesKey, role, true);
        }
    }

    void deleteUserToken(String userId, String token) {
        String userTokenKey = "user:" + userId + ":tokens";
        cacheManager.hashOperationsDelete(userTokenKey, token);
    }
}