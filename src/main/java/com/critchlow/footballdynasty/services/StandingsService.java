package com.critchlow.footballdynasty.services;

import com.critchlow.footballdynasty.model.Standings;
import com.critchlow.footballdynasty.model.StandingsComparator;
import com.critchlow.footballdynasty.repository.StandingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StandingsService {

    private final StandingsRepository standingsRepository;

    @Autowired
    public StandingsService(StandingsRepository standingsRepository){

        this.standingsRepository = standingsRepository;
    }
    @Transactional(readOnly = true)
    public List<Standings> getStandings(Integer year){
        if(year == null || year == 0){
            return standingsRepository.findAll().stream().sorted(new StandingsComparator()).toList();
        }
        List<Standings> standings =  standingsRepository.findAll();
        return standings.stream().filter(s -> s.year == year).sorted(new StandingsComparator()).toList();
    }
}
