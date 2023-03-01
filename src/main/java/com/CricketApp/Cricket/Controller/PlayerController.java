package com.CricketApp.Cricket.Controller;

import com.CricketApp.Cricket.Data.Player;
import com.CricketApp.Cricket.Services.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")

public class PlayerController {

    @Autowired
    private PlayerService playerService;

    //give player information for given id
    @GetMapping("{playerId}")
    public Player getPlayer(@PathVariable String playerId){
        return playerService.getPlayer(playerId);
    }

    // add player into database
    @PostMapping
    public void addPlayer(@Valid @RequestBody Player player){
        playerService.addPlayer(player);
    }


}
