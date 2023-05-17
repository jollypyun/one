package com.example.one.service;

import com.example.one.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var member = authRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("사용자가 없음"));
        List<GrantedAuthority> list = List.of(new SimpleGrantedAuthority("MEMBER"));

        return new User(member.getUserId(), member.getPassword(), list);
    }
}
