package com.critchlow.footballdynasty.model;

import java.util.Comparator;

public class GameComparator implements Comparator<Game> {
    @Override
    public int compare(Game g1, Game g2) {
        if(g1.homeTeam.username == null){
            return -1;
        }
        if(g2.homeTeam.username == null){
            return 1;
        }
        return g1.homeTeam.username.compareTo(g2.homeTeam.username);
    }
}
