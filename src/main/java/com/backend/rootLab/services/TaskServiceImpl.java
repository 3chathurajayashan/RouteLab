package com.backend.rootLab.services;


import com.backend.rootLab.DTOS.Projects.ProjectResponseDTO;
import com.backend.rootLab.DTOS.Tasks.TaskRequestDTO;
import com.backend.rootLab.DTOS.Tasks.TaskResponseDTO;
import com.backend.rootLab.models.*;
import com.backend.rootLab.repository.ProjectRepository;
import com.backend.rootLab.repository.SprintRepository;
import com.backend.rootLab.repository.TaskRepository;
import com.backend.rootLab.repository.UserRepository;
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
    private final ProjectRepository projectRepository;
    private final SprintRepository sprintRepository;
    private final UserRepository userRepository;

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO dto) {

        UserModel currentUser = currentUserService.getCurrentUser();

        // 1. Validate Project
        ProjectModel project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // 2. Validate Sprint (if provided)
        if (dto.getSprintId() != null) {
            SprintModel sprint = sprintRepository.findById(dto.getSprintId())
                    .orElseThrow(() -> new RuntimeException("Sprint not found"));

            // ensure sprint belongs to same project
            if (!sprint.getProjectId().equals(project.getId())) {
                throw new RuntimeException("Sprint does not belong to this project");
            }
        }

        // 3. Validate Assigned User (if provided)
        if (dto.getAssignedUserId() != null) {
            userRepository.findById(dto.getAssignedUserId())
                    .orElseThrow(() -> new RuntimeException("Assigned user not found"));
        }

        TaskModel task = TaskModel.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .projectId(dto.getProjectId())
                .sprintId(dto.getSprintId())
                .assignedUserId(dto.getAssignedUserId())
                .creatorId(currentUser.getId())
                .status(dto.getStatus())
                .priority(dto.getPriority())
                .dueDate(dto.getDueDate())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return mapToDTO(taskRepository.save(task));
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
    public TaskResponseDTO updateTask(String id, TaskRequestDTO dto) {

        UserModel currentUser = currentUserService.getCurrentUser();

        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // permission check
        if (!task.getCreatorId().equals(currentUser.getId())
                && !task.getAssignedUserId().equals(currentUser.getId())) {
            throw new RuntimeException("Not allowed to update task");
        }

        // validate project
        ProjectModel project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // validate sprint consistency
        if (dto.getSprintId() != null) {
            SprintModel sprint = sprintRepository.findById(dto.getSprintId())
                    .orElseThrow(() -> new RuntimeException("Sprint not found"));

            if (!sprint.getProjectId().equals(project.getId())) {
                throw new RuntimeException("Sprint does not belong to this project");
            }
        }

        // validate user
        if (dto.getAssignedUserId() != null) {
            userRepository.findById(dto.getAssignedUserId())
                    .orElseThrow(() -> new RuntimeException("Assigned user not found"));
        }

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setProjectId(dto.getProjectId());
        task.setSprintId(dto.getSprintId());
        task.setAssignedUserId(dto.getAssignedUserId());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());
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
