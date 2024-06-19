package com.critchlow.footballdynasty.repository;

import com.critchlow.footballdynasty.dto.Standings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StandingsRepository extends JpaRepository <Standings, UUID> {

}
