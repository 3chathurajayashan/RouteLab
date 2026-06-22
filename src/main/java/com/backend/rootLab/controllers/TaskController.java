package com.backend.rootLab.controllers;



import com.backend.rootLab.DTOS.Tasks.TaskRequestDTO;
import com.backend.rootLab.DTOS.Tasks.TaskResponseDTO;

import com.backend.rootLab.services.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceImpl taskService;

    @PostMapping
    public TaskResponseDTO createTask(@RequestBody TaskRequestDTO request) {
        return taskService.createTask(request);
    }

    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/project/{projectId}")
    public List<TaskResponseDTO> getTasksByProject(@PathVariable String projectId) {
        return taskService.getTasksByProject(projectId);
    }

    @GetMapping("/user/{userId}")
    public List<TaskResponseDTO> getTasksByUser(@PathVariable String userId) {
        return taskService.getTasksByUser(userId);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@PathVariable String id,
                                      @RequestBody TaskRequestDTO request) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }
}