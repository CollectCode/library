package com.example.storage.service;

import com.example.storage.domain.UsersEntity;
import com.example.storage.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocalUserDetailService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // 유저 찾기
        UsersEntity user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        log.info("user : {}", user);

        // 권한 부여할 리스트 생성
        List<GrantedAuthority> auths = new ArrayList<>();

        // 권한 부여
        auths.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        // 로그 찍어보기
        for(GrantedAuthority auth : auths){
            log.info("auth : {}", auth);
        }

        // UserDetails 객체 생성 및 반환
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(auths)
                .build();
    }
}
