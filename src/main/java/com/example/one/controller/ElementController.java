package com.example.one.controller;

import com.example.one.model.request.ElementRequest;
import com.example.one.model.response.Atom;
import com.example.one.service.Interact;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/element")
@RestController
@RequiredArgsConstructor
public class ElementController {
    private final Interact interact;

    @GetMapping("/all")
    public void getAll() {
        interact.getAll();
    }

    @GetMapping("/choose")
    public Atom choose(ElementRequest request) {
        return interact.get(request.photon(), request.neutron());
    }

    @GetMapping("/search")
    public List<Atom> search(String keyword) {
        return interact.search(keyword);
    }
}
