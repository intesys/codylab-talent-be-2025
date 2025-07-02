package it.intesys.codylab.service;

import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.mapper.TaskMapper;
import it.intesys.codylab.model.Task;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Task> findAllPaginated(int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return taskRepository.findAll(pageable);
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

    public TasksApiDTO createTask(TasksApiDTO tasksApiDTO) {
        Task task = taskMapper.toEntity(tasksApiDTO);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toApiDTO(savedTask);
    }

    public TasksApiDTO updateTask(Long id, TasksApiDTO tasksApiDTO) {
        Task task = taskMapper.toEntity(tasksApiDTO);
        task.setId(id);
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toApiDTO(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


}
