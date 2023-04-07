package com.example.one.controller;

import com.example.one.model.response.PlayersResponse;
import com.example.one.service.PlayerService;
import com.example.one.support.api.ApiResponse;
import com.example.one.support.api.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class MainController {
    private final PlayerService playerService;
    @GetMapping("/players")
    public ApiResponse<PlayersResponse> getAllPlayer() {
        return ApiResponseGenerator.success(playerService.selectAllPlayer());
    }
}
