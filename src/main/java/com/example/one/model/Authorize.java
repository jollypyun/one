package com.example.one.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "authorize")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Authorize {
    @Id
    @Column(name = "role_name", length = 25)
    private String roleName;
}
