package com.backend.rootLab.repository;

import com.backend.rootLab.models.TeamModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<TeamModel, String> {
    List<TeamModel> findByTeamLeadId(String teamLeadId);
}
