package com.backend.rootLab.services;

import com.backend.rootLab.DTOS.Teams.TeamRequestDTO;
import com.backend.rootLab.DTOS.Teams.TeamResponseDTO;
import com.backend.rootLab.models.TeamModel;
import com.backend.rootLab.models.UserModel;
import com.backend.rootLab.repository.TeamRepository;
import com.backend.rootLab.security.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final CurrentUserService currentUserService;

    @Override
    public TeamResponseDTO createTeam(TeamRequestDTO request) {

        UserModel currentUser = currentUserService.getCurrentUser();

        TeamModel team = TeamModel.builder()
                .name(request.getName())
                .teamLeadId(currentUser.getId()) // 🔥 FIX: JWT user is lead
                .memberIds(request.getMemberIds())
                .build();

        return mapToDTO(teamRepository.save(team));
    }

    @Override
    public List<TeamResponseDTO> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public TeamResponseDTO getTeamById(String id) {

        TeamModel team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        return mapToDTO(team);
    }

    @Override
    public List<TeamResponseDTO> getTeamsByLead(String teamLeadId) {
        return teamRepository.findByTeamLeadId(teamLeadId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public TeamResponseDTO updateTeam(String id, TeamRequestDTO request) {

        UserModel currentUser = currentUserService.getCurrentUser();

        TeamModel team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        // 🔥 SECURITY RULE: only team lead can update
        if (!team.getTeamLeadId().equals(currentUser.getId())) {
            throw new RuntimeException("Not allowed to update this team");
        }

        team.setName(request.getName());
        team.setMemberIds(request.getMemberIds());

        return mapToDTO(teamRepository.save(team));
    }

    @Override
    public void deleteTeam(String id) {

        UserModel currentUser = currentUserService.getCurrentUser();

        TeamModel team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        if (!team.getTeamLeadId().equals(currentUser.getId())) {
            throw new RuntimeException("Not allowed to delete this team");
        }

        teamRepository.delete(team);
    }

    private TeamResponseDTO mapToDTO(TeamModel team) {

        return TeamResponseDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .teamLeadId(team.getTeamLeadId())
                .memberIds(team.getMemberIds())
                .build();
    }
}
