package com.example.one.service;

import com.example.one.model.entity.Element;
import com.example.one.model.response.Atom;

import java.util.List;

public interface Interact {
    List<Atom> getAll();
    Atom get(Integer photon, Integer neutron);
    Element add(Element element);
    void distract();
}
