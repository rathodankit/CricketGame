package com.CricketApp.Cricket.Services;

import com.CricketApp.Cricket.Data.Player;
import com.CricketApp.Cricket.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamService teamService;

    //give player information for given playerId
    public Player getPlayer(String playerId) {
        Optional<Player> optPlayer = playerRepository.findById(playerId);
        if(optPlayer.isPresent())
            return optPlayer.get();
        return null;
    }

    //add player into database
    public void addPlayer(Player player) {

        //checking if player is already exist
        if(playerRepository.findByPlayerName(player.getPlayerName()).isPresent()){
            String message = "Player is already present";
            System.out.println(message);
            throw new ExceptionHandler(message);
        }

        //checking if Team is exist or not
        if(! teamService.isExist(player.getTeamId())){
            String message = "Team is not exist so first Create a Team and then Add player to it";
            System.out.println(message);
            throw new ExceptionHandler(message);
        }


        //Add player in player Database
        playerRepository.save(player);

        teamService.addPlayerToTeam(player.getTeamId(),player);
    }


    public void save(Player player) {
        playerRepository.save(player);
    }
}
