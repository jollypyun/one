package com.example.one.repository;

import com.example.one.model.entity.Element;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.one.model.entity.QElement.element;
import static com.querydsl.jpa.JPAExpressions.selectFrom;

@Repository
public class ElementCustomRepositoryImpl implements ElementCustomRepository{


    @Override
    public List<Element> findAllByKeyword(String keyword) {
        return selectFrom(element)
                .where(element.fullname.like("%" + keyword + "%"))
                .fetch();
    }
}
