package com.example.one.service;

import com.example.one.jwt.JwtTokenProvider;
import com.example.one.model.UserInfo;
import com.example.one.repository.OneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OneService {
    private final OneRepository oneRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void addUser(String id, String password) {
        Optional<UserInfo> existed = oneRepository.findById(id);
        if (existed.isPresent()) {
            throw new RuntimeException("This id is not available");
        }
        String encodePassword = passwordEncoder.encode(password);
        oneRepository.save(UserInfo.builder()
                .id(id)
                .password(encodePassword)
                .role("USER")
                .build());
    }

    public String loginCheckUser(String id, String password) {
        Optional<UserInfo> user = oneRepository.findById(id);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 ID입니다.");
        }
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new BadCredentialsException("비밀번호 틀림");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(id, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.createAccessToken(user.get());
    }

    public LocalDateTime enterMain(String token) {
        return jwtTokenProvider.getExpiration(token);
    }
}
