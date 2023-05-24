package com.example.one.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    MEMBER("MEMBER"),
    ADMIN("ADMIN");

    private final String value;
}
