package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.TaskFilterApiDTO;
import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.api.rest.TasksApi;
import it.intesys.codylab.repository.TaskRepository;
import it.intesys.codylab.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TaskRestController implements TasksApi {
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    public TaskRestController(TaskRepository taskRepository, TaskService taskService) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<TasksApiDTO> createTask(TasksApiDTO tasksApiDTO) {
        TasksApiDTO createdTask = taskService.createTask(tasksApiDTO);
        if (createdTask != null) {
            return ResponseEntity.created(URI.create("/api/v1/tasks/" + createdTask.getId())).body(createdTask);
        } else {
            return ResponseEntity.badRequest().build();
        }


    }

    @Override
    public ResponseEntity<Void> deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<TasksApiDTO> getTaskById(Long id) {
        TasksApiDTO task = taskService.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<TasksApiDTO>> getTasks(Integer pageNumber, Integer size, String sort, TaskFilterApiDTO taskFilter) {
        List<TasksApiDTO> tasks = taskService.getTasks();
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);

    }

    @Override
    public ResponseEntity<TasksApiDTO> updateTask(Long id, TasksApiDTO tasksApiDTO) {
        TasksApiDTO updateTask = taskService.updateTask(id, tasksApiDTO);
        if (updateTask != null) {
            return ResponseEntity.ok(updateTask);
        } else {
            return ResponseEntity.notFound().build();
        }}


}
