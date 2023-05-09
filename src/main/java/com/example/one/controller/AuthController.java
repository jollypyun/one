package com.example.one.controller;

import com.example.one.model.request.JoinMember;
import com.example.one.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/join")
    public void join(@RequestBody @Valid JoinMember joinMember) {
        authService.insertMember(joinMember);
    }

    @PostMapping("/login")
    public void login() {

    }
}