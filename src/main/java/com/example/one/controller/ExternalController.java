package com.example.one.controller;

import com.example.one.model.response.TradeCountryResponse;
import com.example.one.service.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/external")
public class ExternalController {
    private final ExternalService externalService;

    @GetMapping("/info")
    public void getInfo() {
        externalService.getInfo();
    }
}
