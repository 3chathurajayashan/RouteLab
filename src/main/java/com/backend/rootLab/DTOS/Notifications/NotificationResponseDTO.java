package com.backend.rootLab.DTOS.Notifications;



import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponseDTO {

    private String id;

    private String userId;

    private String message;

    private boolean read;

    private LocalDateTime createdAt;
}