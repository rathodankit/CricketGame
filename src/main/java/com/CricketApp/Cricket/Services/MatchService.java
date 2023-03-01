package com.CricketApp.Cricket.Services;

import com.CricketApp.Cricket.Data.*;
import com.CricketApp.Cricket.Repository.MatchRepository;
import com.CricketApp.Cricket.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private GameService gameService;


    public void initiateMatch(String firstTeamId, String secondTeamId) {

        //checking if team exist and have proper playing 11.
        if(!teamService.isValidTeam(firstTeamId)){
            String message = firstTeamId + " Team is not exist or This Team does not have 11 player ";
            System.out.println(message);
            throw new ExceptionHandler(message);
        }
        if(!teamService.isValidTeam(secondTeamId)){
            String message = secondTeamId + " Team is not exist or This Team does not have 11 player ";
            System.out.println(message);
            throw new ExceptionHandler(message);
        }


        //perform Toss
        String TossWinner = gameService.performToss(firstTeamId,secondTeamId);

        //choose batting or bowling
        String TossDecision = gameService.chooseBatOrBowl();


        //making first batting team as Team1
        if((TossWinner.equals(firstTeamId) && TossDecision.equals("Bowling")) ||
                (TossWinner.equals(secondTeamId) && TossDecision.equals("Batting"))){
            String temp = firstTeamId;
            firstTeamId = secondTeamId;
            secondTeamId = temp;
        }

        //Create Match Object
        Match match = new Match();
        match.setFirstTeamId(firstTeamId);
        match.setSecondTeamId(secondTeamId);
        match.setTotalOver(20);
        match.setMatchDate(LocalDateTime.now());

        //creating playing11
        List<Player> team1Playing11 = teamService.getPlaying11(firstTeamId);
        List<Player> team2Playing11 = teamService.getPlaying11(secondTeamId);

        //first Inning batting
        Inning firstInning = gameService.Batting(team1Playing11,team2Playing11,match.getTotalOver(),Integer.MAX_VALUE);
        match.setFirstInning(firstInning);


        //second Inning batting
        Inning secondInning = gameService.Batting(team2Playing11,team1Playing11,match.getTotalOver(),firstInning.getTotalRuns());
        match.setSecondInning(secondInning);


        //match winner
        Team team1 = teamService.getTeam(secondTeamId);
        Team team2 = teamService.getTeam(firstTeamId);
        team1.setTotalMatchPlayed(team1.getTotalMatchPlayed() + 1);
        team2.setTotalMatchPlayed(team2.getTotalMatchPlayed() + 1);

        if(secondInning.getTotalRuns() > firstInning.getTotalRuns()){
            match.setWinnerTeam(secondTeamId);
            team1.setTotalMatchWin(team1.getTotalMatchWin() + 1);
        }
        else if(firstInning.getTotalRuns() > secondInning.getTotalRuns()){
            match.setWinnerTeam(firstTeamId);
            team2.setTotalMatchWin(team2.getTotalMatchWin() + 1);
        }
        else
            match.setWinnerTeam("Tie");

        //updating team information
        teamService.save(team1);
        teamService.save(team2);


        //saving match data
        matchRepository.save(match);
    }


}
