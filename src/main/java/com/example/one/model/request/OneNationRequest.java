package com.example.one.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OneNationRequest {
    private String name;
    private String capital;
    private String nationalCode;
    private String isd;
}
