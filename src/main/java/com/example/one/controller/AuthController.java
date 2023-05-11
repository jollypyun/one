package com.example.one.controller;

import com.example.one.model.request.JoinMember;
import com.example.one.model.request.LoginMember;
import com.example.one.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController {
    private final AuthService authService;
    @PostMapping("/join")
    public void join(@RequestBody JoinMember joinMember) {
        log.info(joinMember.userId());
        authService.insertMember(joinMember);
    }

    @PostMapping("/login")
    public void login(@RequestBody @Valid LoginMember loginMember) {
        authService.loginMember(loginMember);
    }
}
