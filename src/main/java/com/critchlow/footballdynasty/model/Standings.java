package com.critchlow.footballdynasty.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Standings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @OneToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    public Team team;
}
