package com.critchlow.footballdynasty.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.UUID;

@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(name = "gameWithSchedule",
                attributeNodes = {
                        @NamedAttributeNode("schedule"),
                        @NamedAttributeNode("homeTeam"),
                        @NamedAttributeNode("awayTeam")

                }),
})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public UUID id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    public Team homeTeam;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    public Team awayTeam;
    @JoinColumn(name = "date")
    public Date date;
    @JoinColumn(name = "home_score")
    public int homeScore;
    @JoinColumn(name = "away_score")
    public int awayScore;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    public Schedule schedule;
}
