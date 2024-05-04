package com.example.one.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="element")
public record Element() {
    @Id
    @Column(name = "fullname")
    static String fullname;

    @Column(name = "abbr")
    static String abbr;

    @Column(name = "photon")
    static Integer photon;

    @Column(name = "neutron")
    static Integer neutron;

//    @Column(name = "electron")
//    static Integer electron;

    @Column(name = "mass")
    static Float mass;

    @Column(name = "mp")
    static Float mp;

    @Column(name = "bp")
    static Float bp;
}
