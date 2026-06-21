package com.backend.rootLab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notifications")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationModel {
    @Id
    private String id;
    private String userId;
    private String title;
    private String message;
    private boolean read;
    private LocalDateTime createdAt;
}
