package com.example.storage.config;

import com.example.storage.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Called JWT Authentication Filter");
        String requestUri = request.getRequestURI();

        // 로그인 요청일 경우
        if(requestUri.startsWith("/api/login"))  {
            log.info("Request URI: {}", requestUri);
            filterChain.doFilter(request, response);
            return;
        }

        // 파일 업로드 요청일 경우
        if(requestUri.startsWith("/api/files"))  {
            log.info("Request URI: {}", requestUri);
            filterChain.doFilter(request, response);
            return;
        }

        if(requestUri.startsWith("/uploads"))  {
            log.info("Request URI: {}", requestUri);
            filterChain.doFilter(request, response);
            return;
        }



        String token = getToken(request);

        log.info("Request URI: {}", requestUri);
        log.info("Token : {}", token);

        try {
            if(token != null)   {
                Authentication authentication = jwtService.verifyToken(token);
                log.info("authentication.getPrincipal() : {}", authentication.getPrincipal());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch(ExpiredJwtException | SignatureException | IllegalArgumentException | UsernameNotFoundException e){
            log.error("Error : {}", response, e);
        }

        filterChain.doFilter(request, response);
    }

    public String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("access_token"))  {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
