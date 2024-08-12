package com.critchlow.footballdynasty.repository;

import com.critchlow.footballdynasty.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID>, CustomTeamRepository {
    Team findTeamByName(String name);
}
