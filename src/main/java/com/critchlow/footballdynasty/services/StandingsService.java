package com.critchlow.footballdynasty.services;

import com.critchlow.footballdynasty.model.Standings;
import com.critchlow.footballdynasty.repository.StandingsRepository;
import org.apache.commons.lang3.StringUtils;
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

    public List<Standings> getStandings(Integer year){
        if(year == null || year == 0){
            return standingsRepository.findAll();
        }
        List<Standings> standings =  standingsRepository.findAll();
        return standings.stream().filter(s -> s.year == year).toList();
    }
}
