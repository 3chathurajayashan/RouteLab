package com.backend.rootLab.services;



import com.backend.rootLab.DTOS.Comments.CommentRequestDTO;
import com.backend.rootLab.DTOS.Comments.CommentResponseDTO;
import com.backend.rootLab.models.CommentModel;
import com.backend.rootLab.repository.CommentRepository;
import com.backend.rootLab.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDTO createComment(CommentRequestDTO request) {

        CommentModel comment = CommentModel.builder()
                .taskId(request.getTaskId())
                .userId(request.getUserId())
                .message(request.getMessage())
                .createdAt(LocalDateTime.now())
                .build();

        return mapToDTO(
                commentRepository.save(comment)
        );
    }

    @Override
    public List<CommentResponseDTO> getAllComments() {

        return commentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public CommentResponseDTO getCommentById(String id) {

        CommentModel comment = commentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Comment not found"));

        return mapToDTO(comment);
    }

    @Override
    public List<CommentResponseDTO> getCommentsByTask(String taskId) {

        return commentRepository.findByTaskId(taskId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<CommentResponseDTO> getCommentsByUser(String userId) {

        return commentRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public void deleteComment(String id) {

        commentRepository.deleteById(id);
    }

    private CommentResponseDTO mapToDTO(CommentModel comment) {

        return CommentResponseDTO.builder()
                .id(comment.getId())
                .taskId(comment.getTaskId())
                .userId(comment.getUserId())
                .message(comment.getMessage())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}