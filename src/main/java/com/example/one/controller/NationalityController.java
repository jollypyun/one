package com.example.one.controller;

import com.example.one.model.request.OneNationRequest;
import com.example.one.model.response.OneNationResponse;
import com.example.one.service.NationalityService;
import com.example.one.support.api.ApiResponse;
import com.example.one.support.api.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/nation")
@RestController
@RequiredArgsConstructor
public class NationalityController {
    private final NationalityService nationalityService;

    @GetMapping("/allNation")
    public ApiResponse<List<OneNationResponse>> getAllNation() {
        return ApiResponseGenerator.success(nationalityService.selectOneNation());
    }

    @PostMapping("/oneNation")
    public ApiResponse<OneNationResponse> postOneNation(@RequestBody OneNationRequest request) {
        return ApiResponseGenerator.success(nationalityService.insertOneNation(request));
    }

    @PutMapping("/oneNation")
    public ApiResponse<OneNationResponse> putOneNation(@RequestBody OneNationRequest request) {
        return ApiResponseGenerator.success(nationalityService.updateOneNation(request));
    }
}
