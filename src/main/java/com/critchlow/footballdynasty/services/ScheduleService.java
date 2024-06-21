package com.critchlow.footballdynasty.services;

import com.critchlow.footballdynasty.model.Game;
import com.critchlow.footballdynasty.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {
    private final GameRepository gameRepository;

    @Autowired
    public ScheduleService(GameRepository gameRepository){

        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public List<Game> getSchedule(Integer year) {
        if(year == null || year == 0){
            List<Game> games = gameRepository.findGames();
            return games;
        }
        List<Game> games =  gameRepository.findGames();
        return games.stream().filter(g -> g.schedule.year == year).toList();
    }
}
