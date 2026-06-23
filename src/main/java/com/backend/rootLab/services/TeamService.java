package com.backend.rootLab.services;

import com.backend.rootLab.DTOS.Teams.TeamRequestDTO;
import com.backend.rootLab.DTOS.Teams.TeamResponseDTO;

import java.util.List;

public interface TeamService {


    TeamResponseDTO createTeam(TeamRequestDTO teamRequestDTO);
    List<TeamResponseDTO> getAllTeams();
    TeamResponseDTO getTeamById(String id);
    List<TeamResponseDTO> getTeamsByLead(String teamLeadId);
    TeamResponseDTO updateTeam(String id, TeamRequestDTO teamRequestDTO);
    void deleteTeam(String id);
}
