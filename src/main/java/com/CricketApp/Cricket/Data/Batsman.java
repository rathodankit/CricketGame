package com.CricketApp.Cricket.Data;

import lombok.Data;

//Storing batsmen perform in every match
@Data
public class Batsman{
    public String player_id;
    public int runs;
    public int balls;
    public int StrikeRate;

    public Batsman(String  id){
        player_id = id;
        runs=0;
        balls=0;
        StrikeRate=0;
    }
}