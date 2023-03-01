package com.CricketApp.Cricket.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//store Team Inning information
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inning {

    private Integer totalRuns;
    private Integer totalWicket;
    private Integer totalOver;
    private List<Batsman> batsmen = new ArrayList<Batsman>(11);
    private List<Bowler> bowler = new ArrayList<Bowler>(11);
    private List<String> everyBallRecord;


}
