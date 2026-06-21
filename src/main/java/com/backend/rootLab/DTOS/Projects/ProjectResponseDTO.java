package com.backend.rootLab.DTOS.Projects;

import com.backend.rootLab.models.ProjectStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ProjectResponseDTO {
    private String id;
    private String name;
    private String ownerId;
    private String description;
    private List<String> teamMembers;
    private ProjectStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
