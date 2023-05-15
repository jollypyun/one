package com.example.one.serviceimpl;

import com.example.one.model.request.JoinMember;
import com.example.one.model.request.LoginMember;
import com.example.one.model.response.JoinResponse;
import com.example.one.repository.AuthRepository;
import com.example.one.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;

    @Override
    public JoinResponse insertMember(JoinMember joinMember) {
        var member = joinMember.to();
        if(authRepository.findById(member.getUserId()).isPresent()) {
            return new JoinResponse("","","");
        }
        return authRepository.save(member).of();
    }

    @Override
    public void loginMember(LoginMember loginMember) {

    }
}
