package com.example.one.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "player")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @Column(name = "player_id")
    private String playerId;

    @Column(name = "name")
    private String name;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "back_number")
    private Integer backNumber;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "joined_at")
    private LocalDate joinedAt;
}
