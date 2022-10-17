package com.example.one.service;

import com.example.one.jwt.JwtTokenProvider;
import com.example.one.model.UserInfo;
import com.example.one.repository.OneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public String checkUser(String id) {
        Optional<UserInfo> user = oneRepository.findById(id);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 ID입니다.");
        }
        return jwtTokenProvider.createAccessToken(user.get());
    }
}
