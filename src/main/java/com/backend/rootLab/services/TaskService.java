package com.backend.rootLab.services;

import com.backend.rootLab.DTOS.Tasks.TaskRequestDTO;
import com.backend.rootLab.DTOS.Tasks.TaskResponseDTO;
import com.backend.rootLab.models.TaskStatus;

import java.util.List;

public interface TaskService {

    TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO);
    List<TaskResponseDTO> getAllTasks();
    TaskResponseDTO getTaskById(String id);
    List<TaskResponseDTO> getTasksByProject(String projectId);
    List<TaskResponseDTO> getTasksByUser(String userId);
    TaskResponseDTO updateTask(String id, TaskRequestDTO taskRequestDTO);
    void deleteTask(String id);
    List<TaskResponseDTO> getTasksByStatus(TaskStatus status);

}
