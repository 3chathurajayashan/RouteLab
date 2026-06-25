package com.backend.rootLab.services;

import com.backend.rootLab.DTOS.Projects.ProjectRequestDTO;
import com.backend.rootLab.DTOS.Projects.ProjectResponseDTO;

import java.util.List;

public interface ProjectService {


    ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO);
    List<ProjectResponseDTO> getAllProjects();
    ProjectResponseDTO getProjectById(String id);
    List<ProjectResponseDTO> getProjectsByOwner(String ownerId);
    ProjectResponseDTO updateProject(ProjectRequestDTO projectRequestDTO , String id);
    void deleteProject(String id);
    List<ProjectResponseDTO> getMyProjects();

    
}
