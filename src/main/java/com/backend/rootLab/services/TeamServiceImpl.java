package com.backend.rootLab.services;

import com.backend.rootLab.DTOS.Teams.TeamRequestDTO;
import com.backend.rootLab.DTOS.Teams.TeamResponseDTO;
import com.backend.rootLab.models.TeamModel;
import com.backend.rootLab.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService{

    private final TeamRepository teamRepository;

    @Override
    public TeamResponseDTO createTeam(
            TeamRequestDTO request
    ) {

        TeamModel team = TeamModel.builder()
                .name(request.getName())
                .teamLeadId(request.getTeamLeadId())
                .memberIds(request.getMemberIds())
                .build();

        return mapToDTO(
                teamRepository.save(team)
        );
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
                .orElseThrow(() ->
                        new RuntimeException("Team not found"));

        return mapToDTO(team);
    }
    @Override
    public List<TeamResponseDTO> getTeamsByLead(
            String teamLeadId
    ) {

        return teamRepository.findByTeamLeadId(teamLeadId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
    @Override
    public TeamResponseDTO updateTeam(
            String id,
            TeamRequestDTO request
    ) {

        TeamModel team = teamRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Team not found"));

        team.setName(request.getName());
        team.setTeamLeadId(request.getTeamLeadId());
        team.setMemberIds(request.getMemberIds());

        return mapToDTO(
                teamRepository.save(team)
        );
    }
    @Override
    public void deleteTeam(String id) {

        teamRepository.deleteById(id);
    }

    private TeamResponseDTO mapToDTO(
            TeamModel team
    ) {

        return TeamResponseDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .teamLeadId(team.getTeamLeadId())
                .memberIds(team.getMemberIds())
                .build();
    }


}
