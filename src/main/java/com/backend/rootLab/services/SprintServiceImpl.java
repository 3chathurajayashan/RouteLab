package com.backend.rootLab.services;

import com.backend.rootLab.DTOS.Sprints.SprintRequestDTO;
import com.backend.rootLab.DTOS.Sprints.SprintResponseDTO;
import com.backend.rootLab.models.SprintModel;
import com.backend.rootLab.repository.SprintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService {

    private final SprintRepository sprintRepository;


    @Override
    public SprintResponseDTO createSprint(SprintRequestDTO request) {

        SprintModel sprint = SprintModel.builder()
                .name(request.getName())
                .projectId(request.getProjectId())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .taskIds(request.getTaskIds())
                .build();

        return mapToDTO(sprintRepository.save(sprint));
    }

    @Override
    public List<SprintResponseDTO> getAllSprints() {
        return sprintRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public SprintResponseDTO getSprintById(String id) {
        SprintModel sprint = sprintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sprint not found"));

        return mapToDTO(sprint);
    }

    @Override
    public List<SprintResponseDTO> getSprintByProject(String projectId) {
        return sprintRepository.findByProjectId(projectId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public SprintResponseDTO updateSprint(String id, SprintRequestDTO request) {

        SprintModel sprint = sprintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sprint not found"));

        sprint.setName(request.getName());
        sprint.setProjectId(request.getProjectId());
        sprint.setStartDate(request.getStartDate());
        sprint.setEndDate(request.getEndDate());
        sprint.setTaskIds(request.getTaskIds());

        return mapToDTO(sprintRepository.save(sprint));
    }

    @Override
    public void deleteSprint(String id) {
        sprintRepository.deleteById(id);
    }

    private SprintResponseDTO mapToDTO(SprintModel sprint) {

        return SprintResponseDTO.builder()
                .id(sprint.getId())
                .name(sprint.getName())
                .projectId(sprint.getProjectId())
                .startDate(sprint.getStartDate())
                .endDate(sprint.getEndDate())
                .taskIds(sprint.getTaskIds())
                .build();
    }

}
