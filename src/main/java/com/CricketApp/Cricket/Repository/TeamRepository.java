package com.CricketApp.Cricket.Repository;

import com.CricketApp.Cricket.Data.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team,String> {


}
