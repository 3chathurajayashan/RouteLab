package com.backend.rootLab.controllers;



import com.backend.rootLab.DTOS.Comments.CommentRequestDTO;
import com.backend.rootLab.DTOS.Comments.CommentResponseDTO;
import com.backend.rootLab.services.CommentService;
import com.backend.rootLab.services.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentResponseDTO createComment(
            @RequestBody CommentRequestDTO request
    ) {
        return commentService.createComment(request);
    }

    @GetMapping
    public List<CommentResponseDTO> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public CommentResponseDTO getCommentById(
            @PathVariable String id
    ) {
        return commentService.getCommentById(id);
    }

    @GetMapping("/task/{taskId}")
    public List<CommentResponseDTO> getCommentsByTask(
            @PathVariable String taskId
    ) {
        return commentService.getCommentsByTask(taskId);
    }

    @GetMapping("/user/{userId}")
    public List<CommentResponseDTO> getCommentsByUser(
            @PathVariable String userId
    ) {
        return commentService.getCommentsByUser(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(
            @PathVariable String id
    ) {
        commentService.deleteComment(id);
    }
}