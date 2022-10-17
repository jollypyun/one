package com.example.one.controller;

import com.example.one.request.UserRequest;
import com.example.one.service.OneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> login(@RequestBody UserRequest user) {
        String str = oneService.checkUser(user.getId());
        return ResponseEntity.ok(str);
    }
}
