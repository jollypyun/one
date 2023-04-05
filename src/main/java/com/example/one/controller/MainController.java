package com.example.one.controller;

import com.example.one.jwt.JwtFilter;
import com.example.one.request.UserRequest;
import com.example.one.service.OneService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class MainController {
    private final OneService oneService;

    @GetMapping(value = "/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping(value = "/join", produces = "application/json")
    public ResponseEntity<String> join(@RequestBody UserRequest info) {
        oneService.addUser(info.getId(), info.getPassword());
        return ResponseEntity.ok("join");
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<HttpHeaders> login(@RequestBody UserRequest user, HttpServletRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String token = oneService.loginCheckUser(user.getId(), user.getPassword());
        httpHeaders.add(JwtFilter.AUTHORIZATION, "Bearer " + token);
        return ResponseEntity.ok(httpHeaders);
    }

    @GetMapping(value="/main", produces = "application/json")
    public ResponseEntity<LocalDateTime> main(HttpServletRequest request) {
        String token = request.getHeader(JwtFilter.AUTHORIZATION).substring(7);
        LocalDateTime date = oneService.enterMain(token);
        return ResponseEntity.ok(date);
    }
}
