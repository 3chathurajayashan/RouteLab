package com.backend.rootLab.DTOS.Comments;



import lombok.Data;

@Data
public class CommentRequestDTO {

    private String taskId;

    private String userId;

    private String message;
}