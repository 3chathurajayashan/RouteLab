package com.backend.rootLab.DTOS.Teams;

import lombok.Data;

import java.util.List;

@Data
public class TeamRequestDTO {
    private String name;
    private String teamLeadId;
    private List<String> memberIds;
}
