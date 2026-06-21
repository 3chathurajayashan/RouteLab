package com.backend.rootLab.services;


import com.backend.rootLab.DTOS.Projects.ProjectResponseDTO;
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
    @Override
    public List<TaskResponseDTO> getAllTasks(){

        return taskRepository.findAll().stream().map(this::mapToDTO).toList();

    }

    @Override
   public TaskResponseDTO getTaskById(String id){
    TaskModel taskModel = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task Was Not Founf!"));

    return maptToDTO(taskModel);
   }
 

   @Override
   public  List<TaskResponseDTO> getTasksByProject(String projectId){

    return taskRepository.findByProjectId(projectId).stream().map(this::mapToDTO).toList();



   }
   @Override
   public List<TaskResponseDTO> getTasksByUser(String userId){
    return taskRepository.findByAssignedUserId(userId).stream().map(this::mapToDTO).toList();

   }

   @Override
   public TaskResponseDTO updateTask(String id, TaskRequestDTO taskRequestDTO){

    TaskModel taskModel = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task not found"));
   

    taskModel.setTitle(taskRequestDTO.getTitle());
    taskModel.setDescription(taskRequestDTO.getDescription());
    taskModel.setProjectId(taskRequestDTO.getProjectId());
    taskModel.setAssignedUserId(taskRequestDTO.getAssignedUserId());


   }









}
