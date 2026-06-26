package com.backend.rootLab.services;


import com.backend.rootLab.DTOS.Projects.ProjectResponseDTO;
import com.backend.rootLab.DTOS.Tasks.TaskRequestDTO;
import com.backend.rootLab.DTOS.Tasks.TaskResponseDTO;
import com.backend.rootLab.models.TaskModel;
import com.backend.rootLab.models.TaskStatus;
import com.backend.rootLab.models.UserModel;
import com.backend.rootLab.repository.TaskRepository;
import com.backend.rootLab.security.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final CurrentUserService currentUserService;

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO){

        UserModel currentUser =
                currentUserService.getCurrentUser();

        TaskModel taskModel = TaskModel.builder()
                .title(taskRequestDTO.getTitle())
                .description(taskRequestDTO.getDescription())
                .projectId(taskRequestDTO.getProjectId())
                .assignedUserId(taskRequestDTO.getAssignedUserId())

                .creatorId(currentUser.getId()) // 🔥 IMPORTANT

                .status(taskRequestDTO.getStatus())
                .sprintId(taskRequestDTO.getSprintId())
                .priority(taskRequestDTO.getPriority())
                .dueDate(taskRequestDTO.getDueDate())

                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return mapToDTO(taskRepository.save(taskModel));
    }
    @Override
    public List<TaskResponseDTO> getAllTasks(){

        return taskRepository.findAll().stream().map(this::mapToDTO).toList();

    }

    @Override
   public TaskResponseDTO getTaskById(String id){
    TaskModel taskModel = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task Was Not Founf!"));

    return mapToDTO(taskModel);
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
    public TaskResponseDTO updateTask(String id, TaskRequestDTO request) {

        UserModel currentUser =
                currentUserService.getCurrentUser();

        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // 🔥 ONLY CREATOR OR ASSIGNEE CAN UPDATE
        if (!task.getCreatorId().equals(currentUser.getId())
                && !task.getAssignedUserId().equals(currentUser.getId())) {
            throw new RuntimeException("Not allowed to update this task");
        }

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setProjectId(request.getProjectId());
        task.setAssignedUserId(request.getAssignedUserId());
        task.setStatus(request.getStatus());
        task.setSprintId(request.getSprintId());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setUpdatedAt(LocalDateTime.now());

        return mapToDTO(taskRepository.save(task));
    }
    @Override
    public void deleteTask(String id) {

        UserModel currentUser =
                currentUserService.getCurrentUser();

        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getCreatorId().equals(currentUser.getId())) {
            throw new RuntimeException("Only creator can delete task");
        }

        taskRepository.delete(task);
    }

    @Override
    public List<TaskResponseDTO> getTasksByStatus(TaskStatus status){
        return  taskRepository.findByStatus(status).stream().map(this::mapToDTO).toList();
    }

    private TaskResponseDTO mapToDTO(TaskModel task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .projectId(task.getProjectId())
                .assignedUserId(task.getAssignedUserId())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }








}
