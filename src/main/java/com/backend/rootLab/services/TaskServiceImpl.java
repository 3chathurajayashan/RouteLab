package com.backend.rootLab.services;


import com.backend.rootLab.DTOS.Tasks.TaskRequestDTO;
import com.backend.rootLab.DTOS.Tasks.TaskResponseDTO;
import com.backend.rootLab.models.TaskModel;
import com.backend.rootLab.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO){
        TaskModel taskModel = TaskModel.builder()
                .title(taskRequestDTO.getTitle())
                .description(taskRequestDTO.getDescription())
                .projectId(taskRequestDTO.getProjectId())
                .assignedUserId(taskRequestDTO.getAssignedUserId())
                .status(taskRequestDTO.getStatus())
                .priority(taskRequestDTO.getPriority())
                .dueDate(taskRequestDTO.getDueDate())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now()).build();

        return mapToDTO(taskRepository.save(taskModel));
    }
    public List<TaskResponseDTO> getAllTasks(){

    }
}
