package com.example.one.controller;

import com.example.one.service.OneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class MainController {
    private OneService oneService;
    @GetMapping(value = "/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @GetMapping(value = "/login")
    public ResponseEntity<String> login(@RequestParam String id, @RequestParam String password) {
        return ResponseEntity.ok("login");
    }
}
