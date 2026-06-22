package com.backend.rootLab.DTOS.Tasks;

import com.backend.rootLab.models.Priority;
import com.backend.rootLab.models.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequestDTO {
    private String title;
    private String description;
    private String projectId;
    private String sprintId;
    private String assignedUserId;
    private TaskStatus status;
    private Priority priority;
    private LocalDate dueDate;
}
