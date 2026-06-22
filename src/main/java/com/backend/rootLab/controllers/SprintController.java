package com.backend.rootLab.controllers;


import com.backend.rootLab.DTOS.Sprints.SprintRequestDTO;
import com.backend.rootLab.DTOS.Sprints.SprintResponseDTO;

import com.backend.rootLab.services.SprintServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
@RequiredArgsConstructor
public class SprintController {

    private final SprintServiceImpl sprintService;

    @PostMapping
    public SprintResponseDTO createSprint(@RequestBody SprintRequestDTO request) {
        return sprintService.createSprint(request);
    }

    @GetMapping
    public List<SprintResponseDTO> getAllSprints() {
        return sprintService.getAllSprints();
    }

    @GetMapping("/{id}")
    public SprintResponseDTO getSprintById(@PathVariable String id) {
        return sprintService.getSprintById(id);
    }

    @GetMapping("/project/{projectId}")
    public List<SprintResponseDTO> getSprintsByProject(@PathVariable String projectId) {
        return sprintService.getSprintByProject(projectId);
    }

    @PutMapping("/{id}")
    public SprintResponseDTO updateSprint(
            @PathVariable String id,
            @RequestBody SprintRequestDTO request
    ) {
        return sprintService.updateSprint(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteSprint(@PathVariable String id) {
        sprintService.deleteSprint(id);
    }
}