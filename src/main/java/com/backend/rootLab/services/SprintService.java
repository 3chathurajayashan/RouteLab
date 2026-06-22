package com.backend.rootLab.services;

import com.backend.rootLab.DTOS.Sprints.SprintRequestDTO;
import com.backend.rootLab.DTOS.Sprints.SprintResponseDTO;

import java.util.List;

public interface SprintService {
    SprintResponseDTO createSprint(SprintRequestDTO sprintRequestDTO);
    List<SprintResponseDTO> getAllSprints();
    SprintResponseDTO getSprintById(String id);
    List<SprintResponseDTO> getSprintByProject(String projectId);
    SprintResponseDTO updateSprint(String id, SprintRequestDTO sprintRequestDTO);
    void deleteSprint(String id);

}
