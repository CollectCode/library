package com.example.storage.config;

import com.example.storage.repository.UsersRepository;
import com.example.storage.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UsersRepository usersRepository;
    private final ObjectMapper objectMapper;
    private final JwtService jwtService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        // 커스텀 로그인 필터 생성 및 경로 변경
        CustomUsernamePasswordAuthenticationFilter customFilter =
                new CustomUsernamePasswordAuthenticationFilter(authenticationManager, usersRepository, objectMapper, jwtService);
        customFilter.setFilterProcessesUrl("/api/login"); // 여기를 /api/login으로 변경

        http
                .securityMatcher("/api/**") // /api/** 경로만 처리
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration corsConfig = new CorsConfiguration();
                    corsConfig.setAllowedOrigins(List.of("http://localhost:5173"));
                    corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfig.setAllowedHeaders(List.of("*"));
                    corsConfig.setAllowCredentials(true);
                    return corsConfig;
                }))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/uploads/**",
                                "/api/files/**",
                                "/api/user/add",
                                "/api/book/title/**",
                                "/api/book/author/**",
                                "/api/book/publish/**",
                                "/api/login"  // 로그인 요청도 여기서 허용
                        ).permitAll()
                        .requestMatchers("/api/book/**", "/api/user/**", "/api/loan/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 커스텀 로그인 필터 위치 지정 (UsernamePasswordAuthenticationFilter 자리에 삽입)
                .addFilterAt(customFilter, UsernamePasswordAuthenticationFilter.class)
                // JWT 인증 필터는 커스텀 로그인 필터 뒤에 위치
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
