package com.example.one.controller;

import com.example.one.model.request.JoinMember;
import com.example.one.model.request.LoginMember;
import com.example.one.model.response.JoinResponse;
import com.example.one.model.response.LoginResponse;
import com.example.one.service.AuthService;
import com.example.one.service.CaptchaService;
import com.example.one.serviceimpl.OAuthServiceImpl;
import com.example.one.support.api.ApiResponse;
import com.example.one.support.api.ApiResponseGenerator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
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
    private final CaptchaService captchaService;
    @PostMapping("/join")
    public ApiResponse<JoinResponse> join(@Valid @RequestBody JoinMember joinMember, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ApiResponseGenerator.error(null);
        }
        return ApiResponseGenerator.success(authService.insertMember(joinMember));
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginMember loginMember, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ApiResponseGenerator.error(null);
        }
        if(!captchaService.verifyToken(loginMember.token())) {
            return ApiResponseGenerator.error(null);
        }
        return ApiResponseGenerator.success(authService.loginMember(loginMember));
    }
}
