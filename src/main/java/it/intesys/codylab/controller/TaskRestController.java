package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.api.rest.TasksApi;
import it.intesys.codylab.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TaskRestController implements TasksApi {
    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;

    }

    @Override
    public ResponseEntity<TasksApiDTO> createTask(TasksApiDTO tasksApiDTO) {
        try {
            TasksApiDTO savedTask = taskService.saveTask(tasksApiDTO);
            return ResponseEntity
                    .created(URI.create("/api/v1/tasks/" + savedTask.getId()))
                    .body(savedTask);
        } catch (Exception e) {
            // Log the error and return an appropriate response
            return ResponseEntity.status(500).body(new TasksApiDTO());
        }
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long taskId) {
        try {
            taskService.getAllTasks().stream()
                    .filter(t -> t.getId().equals(taskId))
                    .findFirst()
                    .ifPresent(task -> taskService.deleteTask(taskId));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Log the error and return an appropriate response
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<TasksApiDTO> getTaskById(Long taskId) {
        TasksApiDTO task = taskService.getAllTasks().stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .orElse(null);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<TasksApiDTO> updateTask(Long taskId, TasksApiDTO tasksApiDTO) {
        try {
            TasksApiDTO updatedTask = taskService.updateTask(taskId, tasksApiDTO);
            return ResponseEntity.ok(updatedTask);
        } catch (Exception e) {
            // Log the error and return an appropriate response
            return ResponseEntity.status(500).body(new TasksApiDTO());
        }

    }

    @Override
    public ResponseEntity<List<TasksApiDTO>> searchTasks(Integer pageNumber, Integer size, String sort, Long projectId, Long taskId) {
        List<TasksApiDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }


}