package com.critchlow.footballdynasty.model;

import jakarta.persistence.*;

import java.util.Objects;
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
    public String username;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Team team)) {
            return false;
        }
	    return isHuman == team.isHuman && Objects.equals(name, team.name) && Objects.equals(coach, team.coach) && Objects.equals(conference, team.conference) && Objects.equals(imageUrl, team.imageUrl) && Objects.equals(username, team.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coach, conference, imageUrl, isHuman, username);
    }
}
