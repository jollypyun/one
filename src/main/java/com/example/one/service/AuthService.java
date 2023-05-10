package com.example.one.service;

import com.example.one.model.request.JoinMember;
import com.example.one.model.request.LoginMember;

public interface AuthService {
    void insertMember(JoinMember joinMember);

    void loginMember(LoginMember loginMember);
}
