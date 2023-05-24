package com.example.one.support.security;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
       if(registrationId.equals("google")) {
           return OAuthAttributes.builder()
                   .name(String.valueOf(attributes.get("name")))
                   .email(String.valueOf(attributes.get("email")))
                   .attributes(attributes)
                   .nameAttributeKey(userNameAttributeName)
                   .build();
       }

       return OAuthAttributes.builder().build();
    }
}
