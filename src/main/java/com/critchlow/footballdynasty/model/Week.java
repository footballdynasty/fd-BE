package com.critchlow.footballdynasty.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Week {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    public int weekNumber;
    public int year;
}
