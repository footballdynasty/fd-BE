package com.critchlow.footballdynasty.dtos;

import lombok.Data;

@Data
public class WinsLosses {
    private int teamWins = 0;
    private int teamLosses = 0;
    private int teamConferenceWins = 0;
    private int teamConferenceLosses = 0;
}
