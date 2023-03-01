package com.CricketApp.Cricket.Controller;

import com.CricketApp.Cricket.Services.*;
import com.CricketApp.Cricket.Data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    //initiate Match
    @PostMapping("{firstTeamId}/{secondTeamId}")
    public void initiateMatch(@PathVariable String firstTeamId,@PathVariable String secondTeamId){
        matchService.initiateMatch(firstTeamId,secondTeamId);
    }

}
