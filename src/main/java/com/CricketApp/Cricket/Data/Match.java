package com.CricketApp.Cricket.Data;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.*;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

// store match information
public class Match {

    @Id
    private String matchId;

    @NotNull
    private String firstTeamId;

    @NotNull
    private String secondTeamId;

    private Integer totalOver;

    private Inning firstInning;
    private Inning secondInning;

    private String winnerTeam;
    private LocalDateTime matchDate;


}
