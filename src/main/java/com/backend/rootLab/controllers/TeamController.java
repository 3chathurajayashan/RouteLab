package com.backend.rootLab.controllers;



import com.backend.rootLab.DTOS.Teams.TeamRequestDTO;
import com.backend.rootLab.DTOS.Teams.TeamResponseDTO;

import com.backend.rootLab.services.TeamServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamServiceImpl teamService;

    @PostMapping
    public TeamResponseDTO createTeam(
            @RequestBody TeamRequestDTO request
    ) {

        return teamService.createTeam(request);
    }

    @GetMapping
    public List<TeamResponseDTO> getAllTeams() {

        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public TeamResponseDTO getTeamById(
            @PathVariable String id
    ) {

        return teamService.getTeamById(id);
    }

    @GetMapping("/lead/{teamLeadId}")
    public List<TeamResponseDTO> getTeamsByLead(
            @PathVariable String teamLeadId
    ) {

        return teamService.getTeamsByLead(teamLeadId);
    }

    @PutMapping("/{id}")
    public TeamResponseDTO updateTeam(
            @PathVariable String id,
            @RequestBody TeamRequestDTO request
    ) {

        return teamService.updateTeam(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(
            @PathVariable String id
    ) {

        teamService.deleteTeam(id);
    }
}
