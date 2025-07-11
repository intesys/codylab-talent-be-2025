package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.TaskFilterApiDTO;
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
    public ResponseEntity<List<TasksApiDTO>> getTasks(
            Integer pageNumber,
            Integer size,
            String sort,
            Long userId,
            List<Long> taskIds) {

        TaskFilterApiDTO filter = new TaskFilterApiDTO();
        filter.setUserId(userId);
        filter.setIds(taskIds);

        List<TasksApiDTO> tasks = taskService.getTasksByFilter(filter);

        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);
    }

    @Override
    public ResponseEntity<TasksApiDTO> getTaskById(Long id) {
        TasksApiDTO task = taskService.getTaskById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @Override
    public ResponseEntity<TasksApiDTO> createTask(TasksApiDTO taskDto) {
        TasksApiDTO createdTask = taskService.createTask(taskDto);
        URI location = URI.create("/api/v1/tasks/" + createdTask.getId());
        return ResponseEntity.created(location).body(createdTask);
    }

    @Override
    public ResponseEntity<TasksApiDTO> updateTask(Long id, TasksApiDTO taskDto) {
        TasksApiDTO updatedTask = taskService.updateTask(id, taskDto);
        if (updatedTask == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTask);
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
