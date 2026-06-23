package com.backend.rootLab.repository;


import com.backend.rootLab.models.NotificationModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<NotificationModel, String> {

    List<NotificationModel> findByUserId(String userId);

    List<NotificationModel> findByRead(boolean read);
}