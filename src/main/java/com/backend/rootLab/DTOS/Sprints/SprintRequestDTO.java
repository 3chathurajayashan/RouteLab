package com.backend.rootLab.DTOS.Sprints;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SprintRequestDTO {


    private String name;
    private String projectId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> taskIds;
}
