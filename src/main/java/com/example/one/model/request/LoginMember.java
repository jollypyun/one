package com.example.one.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record LoginMember(
        String token,
        @NotBlank(message = "This is required.")
        String userId,
        @NotBlank(message = "This is required.")
        String password
) {
}
