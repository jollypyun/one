package com.example.one.repository;

import com.example.one.model.entity.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalityRepository extends JpaRepository<Nationality, String> {
    Nationality findByName(String name);
}
