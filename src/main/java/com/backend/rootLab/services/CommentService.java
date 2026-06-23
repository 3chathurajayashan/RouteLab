package com.backend.rootLab.services;



import com.backend.rootLab.DTOS.Comments.CommentRequestDTO;
import com.backend.rootLab.DTOS.Comments.CommentResponseDTO;

import java.util.List;

public interface CommentService {

    CommentResponseDTO createComment(CommentRequestDTO request);

    List<CommentResponseDTO> getAllComments();

    CommentResponseDTO getCommentById(String id);

    List<CommentResponseDTO> getCommentsByTask(String taskId);

    List<CommentResponseDTO> getCommentsByUser(String userId);

    void deleteComment(String id);
}