package com.critchlow.footballdynasty.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    public String name;
}
