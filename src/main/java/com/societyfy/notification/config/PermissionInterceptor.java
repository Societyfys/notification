package com.societyfy.notification.config;

import com.societyfy.notification.handler.JwtService;
import com.societyfy.notification.handler.UserRolePermissionCacheManager;
import com.societyfy.notification.repository.RolePermissionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PermissionInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(PermissionInterceptor.class);
    private final RolePermissionRepository rolePermissionRepository;
    private final UserRolePermissionCacheManager userRolePermissionCacheManager;
    private final JwtService jwtService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RequiredPermission requiredPermission = handlerMethod.getMethodAnnotation(RequiredPermission.class);

            if (requiredPermission != null) {
                final String token = request.getHeader("Authorization").substring(7);
                String userId = jwtService.getUserIdFromToken(token);
                Set<String> roles = userRolePermissionCacheManager
                        .getUserRoles(userId)
                        .stream().map(Object::toString)
                        .collect(Collectors.toSet());

                Set<String> permissions = rolePermissionRepository.findRolePermission(roles);
                String requiredPermissionValue = requiredPermission.value();

                if (!requiredPermissionValue.isEmpty() && !permissions.contains(requiredPermissionValue)) {
                    log.warn("Access denied for userId: {}. Required Permission: {}", userId, requiredPermissionValue);
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource.");
                    return false;
                }
            }
        }
        return true;
    }
}
