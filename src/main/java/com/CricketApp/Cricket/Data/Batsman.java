package com.CricketApp.Cricket.Data;

import lombok.Data;

//Storing batsmen perform in every match
@Data
public class Batsman{
    private String player_id;
    private int runs;
    private int balls;
    private int StrikeRate;

    public Batsman(String  id){
        player_id = id;
        runs=0;
        balls=0;
        StrikeRate=0;
    }


}