package com.CricketApp.Cricket.Data;

import lombok.Data;

//storing bowling performance for every match
@Data
public class Bowler{
    private String player_id;

    private Integer runs;
    private Integer balls;
    private Integer wickets;
    private Integer Economy;

    public Bowler(String id){
        player_id = id;
        balls=0;
        runs=0;
        wickets=0;
        Economy=0;
    }
}
