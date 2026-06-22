package com.backend.rootLab.DTOS.Sprints;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class SprintResponseDTO {

    private String id;

    private String name;
    private String projectId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> taskIds;
}
