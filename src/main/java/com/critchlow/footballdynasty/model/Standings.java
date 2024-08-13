package com.critchlow.footballdynasty.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Standings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    public Team team;

    public int year;
    public int wins;
    public int losses;
    public int conference_wins;
    public int conference_losses;
    public Integer rank;
    public Integer receiving_votes;
}
