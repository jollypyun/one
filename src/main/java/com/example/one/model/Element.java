package com.example.one.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="Element")
@Entity
public record Element() {
    @Id
    @Column
    static String fullname;

    @Column
    static String name;

    @Column
    static Integer photon;

    @Column
    static Integer neutron;

    @Column
    static Integer electron;

    @Column
    static Float mass;

    @Column
    static Float mp;

    @Column
    static Float bp;
}
