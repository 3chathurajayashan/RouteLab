package com.backend.rootLab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "sprints")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SprintModel {
    @Id
    private String id;

    private String name;
    private String projectId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String createdBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private List<String> taskIds;
}
