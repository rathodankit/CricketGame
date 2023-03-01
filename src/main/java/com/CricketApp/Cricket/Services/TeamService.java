package com.CricketApp.Cricket.Services;

import com.CricketApp.Cricket.Data.Player;
import com.CricketApp.Cricket.Data.Team;
import com.CricketApp.Cricket.Repository.PlayerRepository;
import com.CricketApp.Cricket.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.io.*;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    //give Team information for given teamId
    public Team getTeam(String teamId) {
        Optional<Team> optTeam =  teamRepository.findById(teamId);
        if(optTeam.isPresent())
            return optTeam.get();
        return null;
    }

    //add Team into database
    public void addTeam(Team team) {
        teamRepository.save(team);
    }

    //checking if team is exit and have at least 11 player
    public boolean isValidTeam(String teamId) {
        if(teamRepository.findById(teamId).isEmpty())
            return false;

        Team team = teamRepository.findById(teamId).get();
        if(team.getPlayerIds().size() < 11)
            return false;

        return true;
    }

    //checking if team is exit
    public boolean isExist(String teamId){
        return teamRepository.findById(teamId).isPresent();
    }


    //adding player to his team
    public void addPlayerToTeam(String teamId, Player player) {
        Team team = teamRepository.findById(teamId).get();
        team.addPlayerInTeam(player.getId());
        teamRepository.save(team);
    }

    //give proper playing 11 with 6 batsman and 5 bowler
    public List<Player> getPlaying11(String teamId) {
        List<Player> players = new ArrayList<>();

        List<Player> batsman = playerRepository.findFirst6ByTeamId(teamId, Sort.by(Sort.Direction.DESC, "battingAbility"));
        List<Player> bowler = playerRepository.findFirst5ByTeamId(teamId, Sort.by(Sort.Direction.DESC, "bowlingAbility"));
        Collections.reverse(bowler);

        players.addAll(batsman);
        players.addAll(bowler);
        return players;
    }

    //updating team record
    public void save(Team team) {
        teamRepository.save(team);
    }
}
