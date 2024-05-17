package com.example.one.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="element")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Element {
    @Id
    @Column(name = "fullname")
    String fullname;

    @Column(name = "abbr")
    String abbr;

    @Column(name = "photon")
    Integer photon;

    @Column(name = "neutron")
    Integer neutron;

    @Column(name = "mass")
    Float mass;

    @Column(name = "mp")
    Float mp;

    @Column(name = "bp")
    Float bp;
}
