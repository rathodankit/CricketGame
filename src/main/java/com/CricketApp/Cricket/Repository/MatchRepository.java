package com.CricketApp.Cricket.Repository;

import com.CricketApp.Cricket.Data.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends MongoRepository<Match,String> {

}
