package it.intesys.codylab.service;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.mapper.TaskMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.Task;
import it.intesys.codylab.repository.ProjectRepository;
import it.intesys.codylab.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectRepository = projectRepository;
    }

    public List<TasksApiDTO> getTasks() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .map(taskMapper::toApiDTO)
                .collect(Collectors.toList());
    }

    public TasksApiDTO getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toApiDTO)
                .orElse(null);
    }

    public TasksApiDTO createTask(TasksApiDTO taskDto) {
        Project project = projectRepository.findById(taskDto.getProgettoId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        Task task = taskMapper.toEntity(taskDto);
        task.setProject(project);

        Task saved = taskRepository.save(task);
        return taskMapper.toApiDTO(saved);
    }
}
