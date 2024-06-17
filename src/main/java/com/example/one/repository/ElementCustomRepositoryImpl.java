package com.example.one.repository;

import com.example.one.model.entity.Element;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.one.model.entity.QElement.element;

@Repository
@RequiredArgsConstructor
public class ElementCustomRepositoryImpl implements ElementCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Element> findAllByKeyword(String keyword) {

//        return List.of();
        return jpaQueryFactory
                .selectFrom(element)
                .where(element.fullname.like("%" + keyword + "%"))
                .fetch();
    }
}
