package com.example.one.controller;

import com.example.one.model.request.ElementRequest;
import com.example.one.service.Interact;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/atom")
@RestController
@RequiredArgsConstructor
public class ElementController {
    private final Interact interact;

    @Operation(summary = "전체 조회")
    @GetMapping("/all")
    public void getAll(ElementRequest request) {
        interact.getAll();
    }
}
