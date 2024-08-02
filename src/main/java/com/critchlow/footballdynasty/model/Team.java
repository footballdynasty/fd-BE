package com.critchlow.footballdynasty.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    public String name;
    public String coach;
    public String conference;
    @JoinColumn(name = "image_url")
    public String imageUrl;
    @JoinColumn(name = "is_human")
    public boolean isHuman;

    public Team(String id, String name, String coach, String conference, String imageUrl, boolean isHuman) {
        this.id = UUID.fromString(id);
        this.name = name;
        this.coach = coach;
        this.conference = conference;
        this.imageUrl = imageUrl;
        this.isHuman = isHuman;
    }

    public Team() {
    }
}
