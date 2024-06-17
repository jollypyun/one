package com.example.one.repository;

import com.example.one.model.entity.Element;

import java.util.List;

public interface ElementCustomRepository {
    List<Element> findAllByKeyword(String keyword);
}
