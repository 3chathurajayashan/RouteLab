package com.backend.rootLab.controllers;

import com.backend.rootLab.DTOS.Projects.ProjectRequestDTO;
import com.backend.rootLab.DTOS.Projects.ProjectResponseDTO;
import com.backend.rootLab.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ProjectResponseDTO createProject(@RequestBody ProjectRequestDTO projectRequestDTO, @RequestParam String ownerId){
        return projectService.createProject(projectRequestDTO,ownerId);

    }
}
