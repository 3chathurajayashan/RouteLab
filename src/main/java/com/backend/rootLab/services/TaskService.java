package com.backend.rootLab.services;

import com.backend.rootLab.DTOS.Tasks.TaskRequestDTO;
import com.backend.rootLab.DTOS.Tasks.TaskResponseDTO;

import java.util.List;

public interface TaskService {

    TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO);
    List<TaskResponseDTO> getAllTasks();
    TaskResponseDTO getTaskById(String id);
    List<TaskResponseDTO> getTasksByProjectId(String projectId);
    List<TaskResponseDTO> getTasksByUserId(String userId);
    TaskResponseDTO updateTask(String id, TaskRequestDTO taskRequestDTO);
    void deleteTask(String id);

}
