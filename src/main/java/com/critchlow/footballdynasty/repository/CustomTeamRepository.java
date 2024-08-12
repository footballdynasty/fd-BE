package com.critchlow.footballdynasty.repository;

import com.critchlow.footballdynasty.model.Team;

import java.util.List;

public interface CustomTeamRepository {
	List<Team> findUserTeams();
}
