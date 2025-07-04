package it.intesys.codylab.service;

import it.intesys.codylab.api.model.TaskFilterApiDTO;
import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.mapper.TaskMapper;
import it.intesys.codylab.model.Task;
import it.intesys.codylab.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public Page<TasksApiDTO> getTasks(TaskFilterApiDTO filter, int pageNumber, int size, String sort) {
        if (sort == null || sort.isBlank()) {
            sort = "id";
        }
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sort));

        Page<Task> taskPage;

        if(!filter.getIds().isEmpty() && filter.getUserId() != null) {
            taskPage = findByIdsAndUserId(filter.getUserId(), filter.getIds(), pageable);
        } else if (filter.getUserId() != null && filter.getIds().isEmpty()) {
            taskPage = findByUserId(filter.getUserId(), pageable);
        } else if (filter.getUserId() == null && !filter.getIds().isEmpty()) {
            taskPage = findByIds(filter.getIds(), pageable);
        } else {
            taskPage = taskRepository.findAll(pageable);
        }
        return taskPage.map(taskMapper::toApiDTO);
    }

    public TasksApiDTO getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toApiDTO)
                .orElse(null);
    }

    public TasksApiDTO createTask(TasksApiDTO taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task saved = taskRepository.save(task);
        return taskMapper.toApiDTO(saved);
    }

    public TasksApiDTO updateTask(Long id, TasksApiDTO taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        task.setId(id);
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toApiDTO(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private Page<Task> findByIdsAndUserId(Long userId, List<Long> ids, Pageable pageable) {
        return taskRepository.findByIdsAndUserId(userId, ids, pageable);
    }

    private Page<Task> findByUserId(Long userId, Pageable pageable) {
        return taskRepository.findByUserId(userId, pageable);
    }

    private Page<Task> findByIds(List<Long> ids, Pageable pageable) {
        return taskRepository.findByIds(ids, pageable);
    }
}
