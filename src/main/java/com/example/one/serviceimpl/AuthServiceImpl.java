package com.example.one.serviceimpl;

import com.example.one.model.entity.Member;
import com.example.one.model.request.JoinMember;
import com.example.one.model.request.LoginMember;
import com.example.one.repository.AuthRepository;
import com.example.one.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    @Override
    public void insertMember(JoinMember joinMember) {
        Member member = joinMember.to();
        authRepository.save(member);
    }

    @Override
    public void loginMember(LoginMember loginMember) {

    }
}
