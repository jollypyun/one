package com.example.one.service;

import com.example.one.model.entity.Element;

public interface Interact {
    Element get();
    Element add(Element element);
    void distract();
}
