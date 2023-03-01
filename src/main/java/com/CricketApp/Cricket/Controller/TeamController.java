package com.CricketApp.Cricket.Controller;

import com.CricketApp.Cricket.Data.Team;
import com.CricketApp.Cricket.Services.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    //get team detail for given teamId
    @GetMapping("{teamId}")
    public Team getTeam(@PathVariable String teamId){
        return teamService.getTeam(teamId);
    }

    //add team detail to database
    @PostMapping
    public void addTeam(@Valid @RequestBody Team team){
        teamService.addTeam(team);
    }


}
