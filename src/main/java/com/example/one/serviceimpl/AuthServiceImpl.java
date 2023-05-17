package com.example.one.serviceimpl;

import com.example.one.jwt.JwtTokenProvider;
import com.example.one.model.request.JoinMember;
import com.example.one.model.request.LoginMember;
import com.example.one.model.response.JoinResponse;
import com.example.one.model.response.LoginResponse;
import com.example.one.repository.AuthRepository;
import com.example.one.service.AuthService;
import com.example.one.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    @Override
    public JoinResponse insertMember(JoinMember joinMember) {
        var encodedPassword = passwordEncoder.encode(joinMember.password());
        var member = joinMember.to(encodedPassword);
        if(authRepository.findById(member.getUserId()).isPresent()) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        if(authRepository.findByNickname(member.getNickname()).isPresent()) {
            throw new RuntimeException("이미 있는 닉네임입니다.");
        }

        return authRepository.save(member).of();
    }

    @Override
    public LoginResponse loginMember(LoginMember loginMember) {
        var user = userService.loadUserByUsername(loginMember.userId());

        if(!passwordEncoder.matches(loginMember.password(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginMember.userId(), loginMember.password());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createAccessToken(authentication);

        return new LoginResponse(user.getUsername(), token);
    }
}
