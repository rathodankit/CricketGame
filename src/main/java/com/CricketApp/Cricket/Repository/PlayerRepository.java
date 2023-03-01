package com.CricketApp.Cricket.Repository;

import com.CricketApp.Cricket.Data.Player;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends MongoRepository<Player,String> {

    List<Player> findFirst6ByTeamId(String teamId, Sort battingAbility);

    List<Player> findFirst5ByTeamId(String teamId, Sort bowlingAbility);

    Optional<Object> findByPlayerName(String playerName);
}
