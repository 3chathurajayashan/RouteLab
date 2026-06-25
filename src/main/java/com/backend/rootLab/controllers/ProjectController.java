package com.backend.rootLab.controllers;


import com.backend.rootLab.DTOS.Projects.ProjectRequestDTO;
import com.backend.rootLab.DTOS.Projects.ProjectResponseDTO;
import com.backend.rootLab.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // CREATE PROJECT
    @PostMapping
    public ProjectResponseDTO createProject(
            @RequestBody ProjectRequestDTO projectRequestDTO
    ){
        return projectService.createProject(projectRequestDTO);
    }

    // GET ALL PROJECTS
    @GetMapping
    public List<ProjectResponseDTO> getAllProjects(){
        return projectService.getAllProjects();
    }

    // GET PROJECT BY ID
    @GetMapping("/{id}")
    public ProjectResponseDTO getProjectById(@PathVariable String id){
        return projectService.getProjectById(id);
    }

    // GET PROJECTS BY OWNER
    @GetMapping("/owner/{ownerId}")
    public List<ProjectResponseDTO> getProjectsByOwner(@PathVariable String ownerId){
        return projectService.getProjectsByOwner(ownerId);
    }
    @GetMapping("/my-projects")
    public List<ProjectResponseDTO> getMyProjects(){
        return projectService.getMyProjects();
    }
    // UPDATE PROJECT
    @PutMapping("/{id}")
    public ProjectResponseDTO updateProject(
            @RequestBody ProjectRequestDTO projectRequestDTO,
            @PathVariable String id
    ){
        return projectService.updateProject(projectRequestDTO, id);
    }

    // DELETE PROJECT
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable String id){
        projectService.deleteProject(id);
    }
}
