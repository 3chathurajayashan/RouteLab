package com.backend.rootLab.controllers;




import com.backend.rootLab.DTOS.Teams.TeamRequestDTO;
import com.backend.rootLab.DTOS.Teams.TeamResponseDTO;
import com.backend.rootLab.security.CurrentUserService;
import com.backend.rootLab.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final CurrentUserService currentUserService;

    // ✅ Create team (JWT user becomes leader automatically)
    @PostMapping
    public TeamResponseDTO createTeam(@RequestBody TeamRequestDTO request) {
        return teamService.createTeam(request);
    }

    // ✅ Get all teams
    @GetMapping
    public List<TeamResponseDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

    // ✅ Get team by id
    @GetMapping("/{id}")
    public TeamResponseDTO getTeamById(@PathVariable String id) {
        return teamService.getTeamById(id);
    }

    // ❌ OPTIONAL: keep only for admin/debug
    @GetMapping("/lead/{teamLeadId}")
    public List<TeamResponseDTO> getTeamsByLead(@PathVariable String teamLeadId) {
        return teamService.getTeamsByLead(teamLeadId);
    }

    // 🔥 NEW: Get my teams (BEST PRACTICE)
    @GetMapping("/my-teams")
    public List<TeamResponseDTO> getMyTeams() {

        String currentUserId =
                currentUserService.getCurrentUser().getId();

        return teamService.getTeamsByLead(currentUserId);
    }

    // ✅ Update team (secured inside service)
    @PutMapping("/{id}")
    public TeamResponseDTO updateTeam(
            @PathVariable String id,
            @RequestBody TeamRequestDTO request
    ) {
        return teamService.updateTeam(id, request);
    }

    // ✅ Delete team (secured inside service)
    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable String id) {
        teamService.deleteTeam(id);
    }
}