package com.critchlow.footballdynasty.repository;

import com.critchlow.footballdynasty.model.Standings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StandingsRepository extends JpaRepository <Standings, UUID> {
    @Override
    @Query("SELECT standings FROM Standings standings " +
    "JOIN FETCH standings.team team")
    List<Standings> findAll();
}
