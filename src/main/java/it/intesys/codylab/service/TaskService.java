package it.intesys.codylab.service;

import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.api.model.TaskFilterApiDTO;
import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.mapper.TaskMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.Task;
import it.intesys.codylab.repository.ProjectRepository;
import it.intesys.codylab.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
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
                .orElseThrow(() -> new NoSuchElementException("Task not found with id: " + id));
    }

    public TasksApiDTO createTask(TasksApiDTO taskDto) {
        taskDto.setId(null);
        Task task = taskMapper.toEntity(taskDto);
        Task saved = taskRepository.save(task);
        return taskMapper.toApiDTO(saved);
    }

    public TasksApiDTO updateTask(Long id, TasksApiDTO taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        task.setId(id);
        task.setSlots(taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Task not found with id: " + id)).getSlots());
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toApiDTO(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<TasksApiDTO> getTasksByProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if(project == null) {
            return null;
        }
        return StreamSupport.stream(project.getTasks().spliterator(), false)
                .map(taskMapper::toApiDTO)
                .collect(Collectors.toList());
    }
    public List<TasksApiDTO> getTasksByProjectAndUser(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if(project == null) {
            return null;
        }

        return StreamSupport.stream(project.getTasks().spliterator(), false)
                .filter(task -> task.getUsers().stream().anyMatch(user -> user.getId().equals(userId)))
                .map(taskMapper::toApiDTO)
                .collect(Collectors.toList());
    }
    public List<TasksApiDTO> getTasksByFilter(TaskFilterApiDTO filter) {
        List<Long> ids = filter.getIds() != null ? filter.getIds() : Collections.emptyList();

        if (!ids.isEmpty() && filter.getUserId() != null) {
            return getTasksByUserIdAndTasksIds(filter);
        } else if (!ids.isEmpty()) {
            return getTasksByTasksIds(filter);
        } else if (filter.getUserId() != null) {
            return getTasksByUserId(filter);
        }
        return getAllTasks();
    }

    private List<TasksApiDTO> getTasksByUserIdAndTasksIds(TaskFilterApiDTO filter) {
        List<Task> tasks = taskRepository.findByUserIdAndTaskIds(filter.getUserId(), filter.getIds());
        return tasks.stream().map(taskMapper::toApiDTO).collect(Collectors.toList());
    }

    private List<TasksApiDTO> getTasksByTasksIds(TaskFilterApiDTO filter) {
        List<Task> tasks = taskRepository.findByIds(filter.getIds());
        return tasks.stream().map(taskMapper::toApiDTO).collect(Collectors.toList());
    }

    private List<TasksApiDTO> getTasksByUserId(TaskFilterApiDTO filter) {
        List<Task> tasks = taskRepository.findByUserId(filter.getUserId());
        return tasks.stream().map(taskMapper::toApiDTO).collect(Collectors.toList());
    }

    private List<TasksApiDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAllTasks();
        return tasks.stream().map(taskMapper::toApiDTO).collect(Collectors.toList());
    }
}
