package it.intesys.codylab.service;

import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.mapper.TaskMapper;
import it.intesys.codylab.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
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
}
