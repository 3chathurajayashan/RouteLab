package com.backend.rootLab.repository;

import com.backend.rootLab.models.SprintModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SprintRepository extends MongoRepository<SprintModel, String> {
    List<SprintModel> findByProjectId(String projectId);
}
