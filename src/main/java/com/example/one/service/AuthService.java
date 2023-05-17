package com.example.one.service;

import com.example.one.model.request.JoinMember;
import com.example.one.model.request.LoginMember;
import com.example.one.model.response.JoinResponse;
import com.example.one.model.response.LoginResponse;

public interface AuthService {
    JoinResponse insertMember(JoinMember joinMember);

    LoginResponse loginMember(LoginMember loginMember);
}
