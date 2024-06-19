package com.critchlow.footballdynasty.services;

import com.critchlow.footballdynasty.dto.Standings;
import com.critchlow.footballdynasty.repository.StandingsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandingsService {

    private final StandingsRepository standingsRepository;

    @Autowired
    public StandingsService(StandingsRepository standingsRepository){
        this.standingsRepository = standingsRepository;
    }

    public List<Standings> getStandings(){
        return standingsRepository.findAll();
    }
}
