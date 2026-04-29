package com.nyw.cateringsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className SecurityConfig
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // 开发环境禁用 CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // 所有请求无需登录
                )
                .httpBasic().disable()  // 禁用 Basic Auth
                .formLogin().disable();  // 禁用表单登录

        return http.build();
    }
}
