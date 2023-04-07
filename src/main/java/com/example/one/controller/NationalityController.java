package com.example.one.controller;

import com.example.one.model.response.OneNationResponse;
import com.example.one.service.NationalityService;
import com.example.one.support.api.ApiResponse;
import com.example.one.support.api.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/nation")
@RestController
@RequiredArgsConstructor
public class NationalityController {
    private final NationalityService nationalityService;

    @GetMapping("/randomOne")
    public ApiResponse<OneNationResponse> getRandomNation() {
        return ApiResponseGenerator.success(nationalityService.selectRandomNation());
    }
}
