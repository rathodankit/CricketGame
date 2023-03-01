package com.CricketApp.Cricket.Data;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

//store Team information

@Data
@AllArgsConstructor
@Document
public class Team {

    @Id
    private String id;

    @NotNull
    private String teamName;
    private List<String> playerIds;

    private int totalMatchPlayed;
    private int totalMatchWin;

    public Team(){
        totalMatchPlayed = 0;
        totalMatchWin =0;
        playerIds = new ArrayList<>();
    }
    public void addPlayerInTeam(String playerId){
        playerIds.add(playerId);
    }
}
