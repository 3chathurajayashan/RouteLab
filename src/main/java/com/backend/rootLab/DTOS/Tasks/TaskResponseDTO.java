package com.backend.rootLab.DTOS.Tasks;

import com.backend.rootLab.models.Priority;
import com.backend.rootLab.models.TaskStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder

public class TaskResponseDTO {

    private String id;
    private String title;
    private String description;
    private String sprintId;
    private String projectId;
    private String assignedUserId;
    private TaskStatus status;
    private Priority priority;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
