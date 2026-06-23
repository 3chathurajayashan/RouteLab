package com.backend.rootLab.DTOS.Comments;



import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponseDTO {

    private String id;

    private String taskId;

    private String userId;

    private String message;

    private LocalDateTime createdAt;
}