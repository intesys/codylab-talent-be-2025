package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.api.rest.TasksApi;
import it.intesys.codylab.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return TasksApi.super.createTask(tasksApiDTO);
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long taskId) {
        return TasksApi.super.deleteTask(taskId);
    }

    @Override
    public ResponseEntity<TasksApiDTO> getTaskById(Long taskId) {
        return TasksApi.super.getTaskById(taskId);
    }

    @Override
    public ResponseEntity<TasksApiDTO> updateTask(Long taskId, TasksApiDTO tasksApiDTO) {
        return TasksApi.super.updateTask(taskId, tasksApiDTO);
    }

    @Override
    public ResponseEntity<List<TasksApiDTO>> searchTasks(Integer pageNumber, Integer size, String sort, Long projectId, Long taskId) {
        List<TasksApiDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }


}