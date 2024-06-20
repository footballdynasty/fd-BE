package com.critchlow.footballdynasty.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Standings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @OneToMany(mappedBy = "id")
    public List<Team> team;

    public int year;
}
