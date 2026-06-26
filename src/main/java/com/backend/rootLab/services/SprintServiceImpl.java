package com.backend.rootLab.services;

import com.backend.rootLab.DTOS.Sprints.SprintRequestDTO;
import com.backend.rootLab.DTOS.Sprints.SprintResponseDTO;
import com.backend.rootLab.models.SprintModel;
import com.backend.rootLab.models.UserModel;
import com.backend.rootLab.repository.ProjectRepository;
import com.backend.rootLab.repository.SprintRepository;
import com.backend.rootLab.security.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService {

    private final SprintRepository sprintRepository;
    private final CurrentUserService currentUserService;
    private final ProjectRepository projectRepository;


    @Override
    public SprintResponseDTO createSprint(SprintRequestDTO request) {

        UserModel currentUser = currentUserService.getCurrentUser();

        projectRepository.findById(request.getProjectId())
                .orElseThrow(() ->
                        new RuntimeException("Project not found"));

        if(request.getStartDate().isAfter(request.getEndDate())){
            throw new RuntimeException("Start date cannot be after end date");
        }

        SprintModel sprint = SprintModel.builder()
                .name(request.getName())
                .projectId(request.getProjectId())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .taskIds(request.getTaskIds())

                .createdBy(currentUser.getId())

                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())

                .build();

        return mapToDTO(
                sprintRepository.save(sprint)
        );
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
    public SprintResponseDTO updateSprint(
            String id,
            SprintRequestDTO request
    ) {

        UserModel currentUser =
                currentUserService.getCurrentUser();

        SprintModel sprint = sprintRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sprint not found"));

        if(!sprint.getCreatedBy().equals(currentUser.getId())){
            throw new RuntimeException("You are not allowed to update this sprint");
        }

        if(request.getStartDate().isAfter(request.getEndDate())){
            throw new RuntimeException("Start date cannot be after end date");
        }

        sprint.setName(request.getName());
        sprint.setProjectId(request.getProjectId());
        sprint.setStartDate(request.getStartDate());
        sprint.setEndDate(request.getEndDate());
        sprint.setTaskIds(request.getTaskIds());

        sprint.setUpdatedAt(LocalDateTime.now());

        return mapToDTO(
                sprintRepository.save(sprint)
        );
    }

    @Override
    public void deleteSprint(String id) {

        UserModel currentUser =
                currentUserService.getCurrentUser();

        SprintModel sprint = sprintRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sprint not found"));

        if(!sprint.getCreatedBy().equals(currentUser.getId())){
            throw new RuntimeException("You are not allowed to delete this sprint");
        }

        sprintRepository.delete(sprint);
    }

    private SprintResponseDTO mapToDTO(SprintModel sprint){

        return SprintResponseDTO.builder()
                .id(sprint.getId())
                .name(sprint.getName())
                .projectId(sprint.getProjectId())
                .startDate(sprint.getStartDate())
                .endDate(sprint.getEndDate())
                .taskIds(sprint.getTaskIds())

                .createdBy(sprint.getCreatedBy())
                .createdAt(sprint.getCreatedAt())
                .updatedAt(sprint.getUpdatedAt())

                .build();
    }

}
