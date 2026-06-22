package com.backend.rootLab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "Tasks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel {
    @Id
    private String id;
    private String title;
    private String description;
    private String projectId;
    private String assignedUserId;
    private TaskStatus status;
    private Priority priority;
    private LocalDate dueDate;
    private String sprintId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
