package com.backend.rootLab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Ai_tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AITaskModel {
    @Id
    private String id;
    private String projectId;
    private String prompt;
    private String generatedTask;
}
