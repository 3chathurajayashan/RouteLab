package com.backend.rootLab.repository;

import com.backend.rootLab.models.TaskModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository  extends MongoRepository<TaskModel,String> {
    List<TaskModel> findByProjectId(String projectId);
    List<TaskModel> findByAssignedUserId(String userId);
    List<TaskModel> findByStatus(String status);
}
