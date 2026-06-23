package com.backend.rootLab.repository;



import com.backend.rootLab.models.CommentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<CommentModel,String> {

    List<CommentModel> findByTaskId(String taskId);

    List<CommentModel> findByUserId(String userId);

}