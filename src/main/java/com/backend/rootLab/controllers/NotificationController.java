package com.backend.rootLab.controllers;



import com.backend.rootLab.DTOS.Notifications.NotificationRequestDTO;
import com.backend.rootLab.DTOS.Notifications.NotificationResponseDTO;
import com.backend.rootLab.services.NotificationService;
import com.backend.rootLab.services.NotificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationServiceImpl notificationService;

    @PostMapping
    public NotificationResponseDTO createNotification(
            @RequestBody NotificationRequestDTO request
    ) {
        return notificationService.createNotification(request);
    }

    @GetMapping
    public List<NotificationResponseDTO> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public NotificationResponseDTO getNotificationById(
            @PathVariable String id
    ) {
        return notificationService.getNotificationById(id);
    }

    @GetMapping("/user/{userId}")
    public List<NotificationResponseDTO> getNotificationsByUser(
            @PathVariable String userId
    ) {
        return notificationService.getNotificationsByUser(userId);
    }

    @PutMapping("/{id}/read")
    public NotificationResponseDTO markAsRead(
            @PathVariable String id
    ) {
        return notificationService.markAsRead(id);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(
            @PathVariable String id
    ) {
        notificationService.deleteNotification(id);
    }
}