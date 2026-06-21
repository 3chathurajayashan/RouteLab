package com.backend.rootLab.DTOS.Projects;

import com.backend.rootLab.models.ProjectStatus;
import lombok.Data;

import java.util.List;

@Data
public class ProjectRequestDTO {
    private String name;
    private String description;
    private List<String> teamMembers;
    private ProjectStatus status;
}
