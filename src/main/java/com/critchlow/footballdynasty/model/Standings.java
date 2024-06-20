package com.critchlow.footballdynasty.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Standings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    public Team team;

    public int year;
}
