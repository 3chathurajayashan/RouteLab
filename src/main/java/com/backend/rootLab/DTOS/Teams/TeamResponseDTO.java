package com.backend.rootLab.DTOS.Teams;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TeamResponseDTO {

    private String id;
    private String name;
    private String teamLeadId;
    private List<String> memberIds;
}
