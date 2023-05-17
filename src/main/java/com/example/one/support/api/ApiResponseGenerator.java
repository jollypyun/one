package com.example.one.support.api;

import com.example.one.support.code.StatusCode;

public class ApiResponseGenerator {

    private ApiResponseGenerator() {

    }
    public static <D> ApiResponse<D> success(D data) {
        return new ApiResponse<>(StatusCode.SUCCESS, null, data);
    }
    public static <D> ApiResponse<D> error(D data) {return new ApiResponse<>(StatusCode.BAD_FORMAT, null, data);}
}
