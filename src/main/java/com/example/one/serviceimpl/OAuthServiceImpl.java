package com.example.one.serviceimpl;

import com.example.one.model.Role;
import com.example.one.model.entity.Member;
import com.example.one.repository.AuthRepository;
import com.example.one.support.security.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final AuthRepository authRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName =  userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        log.info("attribute name : " + userNameAttributeName);

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Member member = saveOrUpdate(attributes);
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(member.getRole().getValue())), attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    private Member saveOrUpdate(OAuthAttributes attributes) {
        String email = attributes.getEmail();
        Optional<Member> member = authRepository.findById(email);
        if(member.isPresent()) {
            return member.get();
        }
        String name = attributes.getName();
        String uuid = UUID.randomUUID().toString();
        return authRepository.save(Member.builder()
                .userId(email)
                .nickname(name)
                .name(name)
                .password(new BCryptPasswordEncoder().encode(uuid))
                .role(Role.MEMBER)
                .build());
    }
}
