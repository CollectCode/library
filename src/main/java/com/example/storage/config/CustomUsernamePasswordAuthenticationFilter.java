package com.example.storage.config;

import com.example.storage.domain.UsersEntity;
import com.example.storage.repository.UsersRepository;
import com.example.storage.service.JwtService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UsersRepository usersRepository;
    private final ObjectMapper objectMapper;
    private final JwtService jwtService;

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager,
                                                      UsersRepository usersRepository,
                                                      ObjectMapper objectMapper,
                                                      JwtService jwtService) {
        super.setAuthenticationManager(authenticationManager);
        this.usersRepository = usersRepository;
        this.objectMapper = objectMapper;
        this.jwtService = jwtService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        log.info("Called attemptAuthentication in CustomUsernamePasswordAuthenticationFilter");

        // POST 메서드 확인
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        // Application/JSON으로 왔는지 확인
        if (!request.getContentType().equalsIgnoreCase("application/json")) {
            throw new AuthenticationServiceException("Content-Type must be application/json");
        }

        try {
            // JSON 요청 본문을 String으로 읽기
            String requestBody = new BufferedReader(request.getReader()).lines().collect(Collectors.joining("\n"));

            // JSON 파싱
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            String username = jsonNode.get("username").asText();
            String password = jsonNode.get("password").asText();

            // ✅ 인증 토큰을 만들고 AuthenticationManager에게 인증 요청
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
    
            // 실제 인증 처리
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (IOException e) {
            throw new AuthenticationServiceException("Failed to parse JSON request body", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        log.info("Called successfulAuthentication in CustomUsernamePasswordAuthenticationFilter");

        // 인증 객체에서 username 추출
        String username = authResult.getName();
        
        // 사용자 정보 추출
        UsersEntity user = usersRepository.findByUsername(username).orElseThrow(() -> new AuthenticationServiceException("User not found"));

        log.info("get user in successfulAuthentication : {}", user);

        // 사용자 정보 기반으로 토큰제작 및 쿠키에 담기
        Map<String, ResponseCookie> cookies = jwtService.login(user);

        // 쿠키를 header에 담기
        for (ResponseCookie cookie : cookies.values()) {
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }

        // 인증 객체를 context에 저장
        SecurityContextHolder.getContext().setAuthentication(authResult);

        // 요청 Origin 가져오기
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // JSON 응답 반환
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\":true,\"message\":\"로그인 성공\"}");
    }
}