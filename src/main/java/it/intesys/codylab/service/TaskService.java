package it.intesys.codylab.service;

import it.intesys.codylab.dto.TaskDTO;
import it.intesys.codylab.mapper.TaskMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.Task;
import it.intesys.codylab.repository.ProjectRepository;
import it.intesys.codylab.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.taskMapper = taskMapper;
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Project project = projectRepository.findById(taskDTO.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        Task task = taskMapper.toEntity(taskDTO);
        task.setProject(project);

        Task saved = taskRepository.save(task);
        return taskMapper.toDTO(saved);
    }
}