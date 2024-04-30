package com.example.one.serviceimpl;

import com.example.one.model.entity.Element;
import com.example.one.service.Interact;

public class InteractImpl implements Interact {
    @Override
    public Element add(Element element) {
        return new Element();
    }

    @Override
    public void distract() {

    }
}
