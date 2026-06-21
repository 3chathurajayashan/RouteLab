package com.backend.rootLab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "teams")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamModel {
    @Id
    private String id;
    private String name;
    private String teamLeadId;
    private List<String> memberIds;
}
