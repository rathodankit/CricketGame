package com.CricketApp.Cricket.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//store player information
@Data
@Document
@AllArgsConstructor


public class Player {

    @Id
    private String id;

    @Size(min = 2,message = "min size is 2")
    @NotNull
    private String playerName;

    private Integer totalMatch;
    private Integer totalRun;
    private Integer totalWicket;

    @NotNull
    @Max(100)
    private Integer battingAbility;

    @NotNull
    @Max(100)
    private Integer bowlingAbility;


    private Integer battingAverage;
    private Integer highestScore;

    @NotNull
    private String teamId;

    public Player(){
        totalMatch = 0;
        totalRun =0;
        totalWicket=0;
        battingAverage=0;
        highestScore=0;
    }
}
