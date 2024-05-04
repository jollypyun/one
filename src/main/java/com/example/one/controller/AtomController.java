package com.example.one.controller;

import com.example.one.model.entity.Element;
import com.example.one.service.Interact;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/atom")
@RestController
@RequiredArgsConstructor
public class AtomController {
    private final Interact interact;

    @GetMapping("/")
    public void get() {
        interact.add(new Element());
    }
}
