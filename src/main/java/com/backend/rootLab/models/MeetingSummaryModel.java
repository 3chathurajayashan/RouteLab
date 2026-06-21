package com.backend.rootLab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "meeting_summaries")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeetingSummaryModel {
    @Id
    private String id;
    private String projectId;
    private String transcript;
    private String aiSummary;
}
