package com.CricketApp.Cricket.Data;

import lombok.Data;

//storing bowling performance for every match
@Data
public class Bowler{
    public String player_id;

    public Integer runs;
    public Integer balls;
    public Integer wickets;
    public Integer Economy;

    public Bowler(String id){
        player_id = id;
        balls=0;
        runs=0;
        wickets=0;
        Economy=0;
    }
}
