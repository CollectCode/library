package com.example.storage.service;

import com.example.storage.config.JwtProperties;
import com.example.storage.domain.RefreshTokenEntity;
import com.example.storage.domain.UsersEntity;
import com.example.storage.repository.RefreshTokenRepository;
import com.example.storage.repository.UsersRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    // Jwt 설정에 관한 정보
    private final JwtProperties jwtProperties;

    // SecretKey(AccessToken Key) 암호화
    private final SecretKey secretKey;

    // SecretKey(RefreshToken Key) 암호화
    private final SecretKey refreshSecretKey;

    // signature에 AccessToken Key 추가
    private final JwtParser jwtParser;

    // signature에 refreshToken Key 추가
    private final JwtParser refreshJwtParser;
    private final UserDetailsService userDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    private final UsersRepository usersRepository;

    // Jwt 설정파일, UserDetailsService, repository, SecretKey, Parser 초기화
    public JwtService(JwtProperties jwtProperties
            , UserDetailsService userDetailsService
            , RefreshTokenRepository refreshTokenRepository
            , UsersRepository usersRepository) {
        this.jwtProperties = jwtProperties;
        this.userDetailsService = userDetailsService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.usersRepository = usersRepository;
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtProperties.getSecretKey()));
        refreshSecretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtProperties.getRefreshKey()));
        jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
        refreshJwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    // 토큰 발행
    public String generateToken(String username, String type) {
        if(type == null || !type.equals(REFRESH_TOKEN)) { type = ACCESS_TOKEN; }

        Date now = new Date();
        Duration duration = Duration.ofMinutes(type.equals(ACCESS_TOKEN)? jwtProperties.getDuration(): jwtProperties.getRefreshDuration());
        Date expiration = new Date(now.getTime() + duration.toMillis());

        return Jwts.builder()
                .setIssuer(jwtProperties.getIssuer())
                .setSubject(username)
                .setExpiration(expiration)
                .signWith(type.equals(ACCESS_TOKEN) ? secretKey : refreshSecretKey)
                .compact();
    }

    // 토큰 정보를 기반으로 Context에 저장할 유저 정보 생성
    public Authentication verifyToken(String token) throws JwtException, UsernameNotFoundException {
        String username = jwtParser.parseClaimsJwt(token).getBody().getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
    
    // username으로 Token 생성 및 저장
    @Transactional
    public Map<String, String> getTokenByUsername(UsersEntity user) {
        Map<String, String> tokens = new HashMap<>();

        String accessToken = generateToken(user.getUsername(), ACCESS_TOKEN);
        String refreshToken = generateToken(user.getUsername(), REFRESH_TOKEN);

        RefreshTokenEntity rtToken = refreshTokenRepository.findByUser_username(user.getUsername()).orElse(null);

        if(rtToken == null) {
            rtToken = new RefreshTokenEntity();
        }

        rtToken.updateRefreshToken(refreshToken);
        rtToken.updateUser(user);

        RefreshTokenEntity savedRtToken = refreshTokenRepository.save(rtToken);

        tokens.put(ACCESS_TOKEN, accessToken);
        tokens.put(REFRESH_TOKEN, refreshToken);

        return tokens;
    }

    // RefreshToken으로 AccessToken 발급
    @Transactional
    public String getAccessTokenByRefreshToken(String token) throws JwtException,UsernameNotFoundException  {
        String username = jwtParser.parseClaimsJwt(token).getBody().getSubject();
        UsersEntity users = usersRepository.findByUsername(username).orElse(null);
        RefreshTokenEntity rtToken = refreshTokenRepository.findByUser_username(users.getUsername()).orElse(null);

        if(rtToken != null && !rtToken.getRtToken().equals(token))  {
            refreshTokenRepository.delete(rtToken);
            return null;
        }

        Map<String, String> tokens = getTokenByUsername(users);

        return tokens.get(ACCESS_TOKEN);
    }

    @Transactional
    public Map<String, ResponseCookie> login(UsersEntity user)  {

        Map<String, String> tokens = getTokenByUsername(user);
        Map<String, ResponseCookie> responseCookies = new HashMap<>();

        // 쿠키에 각 토큰을 저장
        ResponseCookie accessCookie = ResponseCookie.from("access_token", tokens.get(ACCESS_TOKEN))
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/")
                .maxAge(Duration.ofDays(10))
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refresh_token", tokens.get(REFRESH_TOKEN))
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/")
                .maxAge(Duration.ofDays(10))
                .build();

        responseCookies.put("access_token", accessCookie);
        responseCookies.put("refresh_token", refreshCookie);

        return responseCookies;
    }
}
