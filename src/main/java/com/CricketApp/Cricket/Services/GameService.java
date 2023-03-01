package com.CricketApp.Cricket.Services;

import com.CricketApp.Cricket.Data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private PlayerService playerService;

    //perform toss
    public String performToss(String firstTeamId, String secondTeamId) {
        int toss = (int)(Math.random()*2);
        if(toss == 1)
            return firstTeamId;
        return secondTeamId;
    }

    //choose batting or bowling after winning toss
    public String chooseBatOrBowl() {
        int decision = (int) (Math.random()*2);
        if(decision == 1)
            return "Batting";
        return "Bowling";
    }

    //choose bowler for bowling
    private Integer chooseBowler(Integer except){
        Integer bowler = (int)(Math.random()*5);

        if(bowler.equals(except))
            bowler = (bowler+1) % 5;

        return (bowler+6);
    }

    //runs on every ball based on batter and bowler ability
    private int giveRuns(Player batter , Player bowler){
        int abilityDiff = batter.getBattingAbility() - bowler.getBowlingAbility();
        int run = (int)(Math.random()*100);

        if(abilityDiff >= 0){

            //probability for run is 84 and for wicker 16
            if(abilityDiff <= 10)
                return (run/12);

            //probability for run is 91 and for wicker 9
            return (run/13);
        }
        else{

            abilityDiff = abilityDiff*-1;
            //probability for run is 77 and for wicker 23
            if(abilityDiff <= 10)
                return (run/11);

            //probability for run is 70 and for wicker 30
            return (run/10);
        }

    }


    public Inning Batting(List<Player> battingTeam , List<Player> bowlingTeam , int over, int target) {

        int striker = 0 , nonStriker = 1 , newBatsman = 2 , bowler = 10;
        int totalBalls=0 , totalRuns = 0, totalWickets = 0;

        //storing each ball record
        List<String> everyBallRecord = new ArrayList<>();

        //Storing each batsman individual contribution
        List<Batsman> batsmen = new ArrayList<>(11);
        for(int i=0;i<11;i++)
            batsmen.add(new Batsman(battingTeam.get(i).getId()));

        //Storing each bowler individual contribution
        List<Bowler> bowlers = new ArrayList<>(11);
        for(int i=0;i<11;i++)
            bowlers.add(new Bowler(bowlingTeam.get(i).getId()));

        // inning continue until over is finish or all wicket is fallen or target is achieve
        while(totalWickets < 10 && totalBalls < (over*6) && totalRuns <= target ){


            if(totalBalls % 6 == 0)
                bowler = chooseBowler(bowler);

            //run on current ball
            totalBalls++;
            batsmen.get(striker).balls += 1;
            int run = giveRuns(battingTeam.get(striker) , bowlingTeam.get(bowler));


            //batsman out
            if(run >= 7){
                everyBallRecord.add("W");
                totalWickets++;

                //saving out batsman detail
                batsmen.get(striker).StrikeRate = (batsmen.get(striker).runs * 100 ) / batsmen.get(striker).balls;

                //saving bowler detail
                bowlers.get(bowler).wickets += 1;

                striker = newBatsman++;
            }
            //run score
            else{
                everyBallRecord.add(String.valueOf(run));
                //updating batsman and bowler current state
                batsmen.get(striker).runs += run;
                batsmen.get(striker).StrikeRate = (batsmen.get(striker).runs * 100 ) / batsmen.get(striker).balls;

                bowlers.get(bowler).runs += run;
                totalRuns += run;

                //striker change
                if(run % 2 != 0){
                    int temp = striker;
                    striker = nonStriker;
                    nonStriker = temp;
                }

            }


            //updating bowler economy
            bowlers.get(bowler).balls +=1;
            bowlers.get(bowler).Economy = (bowlers.get(bowler).runs * 6) / bowlers.get(bowler).balls;


        }

        //updating player carrier state
        for(int i=0;i<11;i++){
            Batsman better = batsmen.get(i);
            Player player = battingTeam.get(i);

            player.setTotalRun(player.getTotalRun() + better.runs);
            if(better.runs > player.getHighestScore())
                player.setHighestScore(better.runs);
            player.setTotalMatch(player.getTotalMatch() + 1);
            player.setBattingAverage(player.getTotalRun() / player.getTotalMatch());
            playerService.save(player);

            Bowler bowler1 = bowlers.get(i);
            player = bowlingTeam.get(i);

            player.setTotalWicket(player.getTotalWicket() + bowler1.wickets);
        }




        return new Inning(totalRuns,totalWickets,totalBalls/6,batsmen,bowlers,everyBallRecord);
    }
}
