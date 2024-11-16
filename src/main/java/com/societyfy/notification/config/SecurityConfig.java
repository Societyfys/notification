package com.societyfy.notification.config;

import com.societyfy.notification.handler.JwtService;
import com.societyfy.notification.handler.UserRolePermissionCacheManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtService jwtService;
    private final UserRolePermissionCacheManager userRolePermissionCacheManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        (auth) -> auth.requestMatchers(
                                "/v1/notification/send").permitAll()
                                .anyRequest()
                                .authenticated())
                .addFilterBefore(
                        new JwtAuthenticationFilter(
                                jwtService,
                                userRolePermissionCacheManager),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
