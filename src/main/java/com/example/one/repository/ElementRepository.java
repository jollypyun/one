package com.example.one.repository;

import com.example.one.model.entity.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementRepository extends JpaRepository<Element, String>, ElementCustomRepository {
    List<Element> findAll();
    Element findByPhotonAndNeutron(Integer photon, Integer Neutron);
}
