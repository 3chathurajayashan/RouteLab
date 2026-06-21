package com.backend.rootLab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "risk_analysis")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RiskAnalysisModel {
    @Id
    private String id;
    private String projectId;
    private String riskLevel;
    private String aiReport;
}
