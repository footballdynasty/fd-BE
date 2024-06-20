package com.critchlow.footballdynasty.model;

import jakarta.persistence.*;
import org.apache.logging.log4j.util.Lazy;

import java.util.List;
import java.util.UUID;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    public String name;
}
