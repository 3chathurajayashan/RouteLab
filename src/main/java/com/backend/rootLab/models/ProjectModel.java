package com.backend.rootLab.models;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "projects")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectModel {
    @Id
    private String id;
    @NotBlank
    private String name;
    private String ownerId;
    @NotBlank
    private String description;
    private List<String> teamMembers;
    private ProjectStatus status = ProjectStatus.PLANNING;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
