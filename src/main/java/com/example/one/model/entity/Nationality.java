package com.example.one.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "nationality")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Nationality {
    @Id
    @Column(name = "nation_id")
    private String nationId;

    @Column(name = "name")
    private String name;

    @Column(name = "capital")
    private String capital;

    @Column(name = "national_code")
    private String nationalCode;

    @Column(name = "isd")
    private String isd;
}
