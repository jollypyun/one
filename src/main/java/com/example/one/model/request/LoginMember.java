package com.example.one.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record LoginMember(
        @NotBlank(message = "This is required.")
        @Min(6)@Max(20)
        String userId,

        @NotBlank(message = "This is required.")
        @Min(8)@Max(20)
        String password
) {
}
