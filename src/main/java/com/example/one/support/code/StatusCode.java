package com.example.one.support.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum StatusCode {
    SUCCESS("2000", "OK"),

    PLAYER_NOT_FOUND("9000", "일치하는 선수가 없습니다");

    private final String code;
    private final String message;
}
