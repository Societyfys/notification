package com.societyfy.notification.config;

import com.societyfy.notification.handler.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final com.societyfy.notification.handler.UserRolePermissionCacheManager UserRolePermissionCacheManager;
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        logRequestDetails(request);
        try {
            String authorizationHeader = request.getHeader("Authorization");

            if (pathMatcher.match("/v1/notification/send", request.getRequestURI())) {
                filterChain.doFilter(request, response);
                return;
            }

            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                log.error("Missing or invalid Authorization header");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing or invalid");
                return;
            }

            String token = authorizationHeader.substring(7);

            if (!jwtService.isTokenValid(token)) {
                log.error("Invalid token: {}", token);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }

            String userId = jwtService.getUserIdFromToken(token);

            Set<String> roles = UserRolePermissionCacheManager
                    .getUserRoles(userId)
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.toSet());

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userId, null, roles.stream().map(SimpleGrantedAuthority::new).toList());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            log.error("Authentication failed: {}", e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void logRequestDetails(HttpServletRequest request) {
        StringBuilder logMessage = new StringBuilder();

        logMessage.append("\n===== Incoming Request Details =====\n");

        logMessage.append("HTTP Method: ").append(request.getMethod()).append("\n");
        logMessage.append("Request URL: ").append(request.getRequestURL()).append("\n");

        logMessage.append("Query Parameters:\n");
        request.getParameterMap().forEach((key, value) ->
                logMessage.append("  - ").append(key).append(": ").append(String.join(", ", value)).append("\n")
        );

        logMessage.append("Headers:\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            logMessage.append("  - ").append(headerName).append(": ").append(request.getHeader(headerName)).append("\n");
        }

        logMessage.append("====================================");

        log.info(logMessage.toString());
    }
}
