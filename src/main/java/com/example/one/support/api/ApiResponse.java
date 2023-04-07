package com.example.one.support.api;

import com.example.one.support.code.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;

    public ApiResponse(StatusCode statusCode) {
        this(statusCode.getCode(), statusCode.getMessage(), null);
    }

    public ApiResponse(StatusCode code, String message, T data) {
        this.code = code.getCode();
        this.message = (message == null ? code.getMessage() : message);
        this.data = data;
    }
}
