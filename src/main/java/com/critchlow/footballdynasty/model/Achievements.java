package com.critchlow.footballdynasty.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Achievements {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    public String description;
    public String reward;
    public Long date_completed;
}
