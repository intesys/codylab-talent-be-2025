package it.intesys.codylab.controller;

import it.intesys.codylab.dto.TaskDTO;
import it.intesys.codylab.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<TaskDTO>> showTasks() {
        List<TaskDTO> tasks = taskService.showTasks();
        return ResponseEntity.ok(tasks);
}
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO dto) {
        TaskDTO saved = taskService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/{taskId}/users")
    public ResponseEntity<TaskDTO> addUserToTask(@PathVariable Long taskId, @RequestBody List<Long> userIds) {
        TaskDTO updated = taskService.addUsersToTask(taskId, userIds);
        return ResponseEntity.ok(updated);
    }
}
