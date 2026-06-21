package com.backend.rootLab.repository;

import com.backend.rootLab.models.ProjectModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<ProjectModel, String> {
    List<ProjectModel> findByOwnerId(String ownerId);
}
