package com.example.one.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserResponse {
    private String subject;
    private String role;
    private String staDate;
    private Date expDate;
}
