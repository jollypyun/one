package com.example.one.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OneNationRequest(
        @NotBlank
        String name,
        @NotBlank
        String capital,
        @NotBlank
        @Size(min = 3)
        String nationalCode,
        @NotBlank
        String isd
) {

}
