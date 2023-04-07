package com.example.one.support.api;

import com.example.one.support.code.StatusCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApiResponseGenerator {

    public static <D> ApiResponse<D> success(D data) {
        return new ApiResponse<>(StatusCode.SUCCESS, null, data);
    }
}
