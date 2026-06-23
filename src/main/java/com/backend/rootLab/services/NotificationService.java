package com.backend.rootLab.services;



import com.backend.rootLab.DTOS.Notifications.NotificationRequestDTO;
import com.backend.rootLab.DTOS.Notifications.NotificationResponseDTO;

import java.util.List;

public interface NotificationService {

    NotificationResponseDTO createNotification(NotificationRequestDTO request);

    List<NotificationResponseDTO> getAllNotifications();

    NotificationResponseDTO getNotificationById(String id);

    List<NotificationResponseDTO> getNotificationsByUser(String userId);

    NotificationResponseDTO markAsRead(String id);

    void deleteNotification(String id);
}