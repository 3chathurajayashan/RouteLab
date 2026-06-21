package com.backend.rootLab.services;

import com.backend.rootLab.DTOS.Projects.ProjectRequestDTO;
import com.backend.rootLab.DTOS.Projects.ProjectResponseDTO;
import com.backend.rootLab.models.ProjectModel;
import com.backend.rootLab.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO, String ownerId){
        ProjectModel projectModel = ProjectModel.builder().name(projectRequestDTO.getName())
                .description(projectRequestDTO.getDescription())
                .ownerId(ownerId)
                .teamMembers(projectRequestDTO.getTeamMembers())
                .status(projectRequestDTO.getStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return mapToResponse(
                projectRepository.save(projectModel)
        );
    }
    @Override
    public List<ProjectResponseDTO> getAllProjects(){
        return projectRepository.findAll().stream().map(this::mapToResponse).toList();

    }
    @Override
    public ProjectResponseDTO getProjectById(String id) {

         ProjectModel projectModel = projectRepository.findById(id)
                 .orElseThrow(()->new RuntimeException("project bot found!"));

        return mapToResponse(projectModel);
    }

    @Override
    public List<ProjectResponseDTO> getProjectsByOwner(String ownerId) {

        return projectRepository.findByOwnerId(ownerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProjectResponseDTO updateProject(ProjectRequestDTO projectRequestDTO , String id){

        ProjectModel project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project not found"));

        project.setName(projectRequestDTO.getName());
        project.setDescription(projectRequestDTO.getDescription());
        project.setTeamMembers(projectRequestDTO.getTeamMembers());
        project.setStatus(projectRequestDTO.getStatus());
        project.setUpdatedAt(LocalDateTime.now());

        return mapToResponse(
                projectRepository.save(project)
        );
    }
    @Override
    public void deleteProject(String id) {

        projectRepository.deleteById(id);
    }

    private ProjectResponseDTO mapToResponse(ProjectModel project) {

        return ProjectResponseDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .ownerId(project.getOwnerId())
                .teamMembers(project.getTeamMembers())
                .status(project.getStatus())
                .createdAt(project.getCreatedAt())
                .updatedAt(project.getUpdatedAt())
                .build();
    }


}
