package com.backend.rootLab.services;

 

import com.backend.rootLab.DTOS.Notifications.NotificationRequestDTO;
import com.backend.rootLab.DTOS.Notifications.NotificationResponseDTO;
import com.backend.rootLab.models.NotificationModel;
import com.backend.rootLab.repository.NotificationRepository;
import com.backend.rootLab.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public NotificationResponseDTO createNotification(NotificationRequestDTO request) {

        NotificationModel notification = NotificationModel.builder()
                .userId(request.getUserId())
                .message(request.getMessage())
                .read(false)
                .createdAt(LocalDateTime.now())
                .build();

        return mapToDTO(notificationRepository.save(notification));
    }

    @Override
    public List<NotificationResponseDTO> getAllNotifications() {

        return notificationRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public NotificationResponseDTO getNotificationById(String id) {

        NotificationModel notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        return mapToDTO(notification);
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsByUser(String userId) {

        return notificationRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public NotificationResponseDTO markAsRead(String id) {

        NotificationModel notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);

        return mapToDTO(notificationRepository.save(notification));
    }

    @Override
    public void deleteNotification(String id) {

        notificationRepository.deleteById(id);
    }

    private NotificationResponseDTO mapToDTO(NotificationModel notification) {

        return NotificationResponseDTO.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .message(notification.getMessage())
                .read(notification.isRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}