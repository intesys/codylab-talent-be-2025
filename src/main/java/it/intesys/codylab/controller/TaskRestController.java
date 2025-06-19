package it.intesys.codylab.controller;

import it.intesys.codylab.dto.TaskDTO;
import it.intesys.codylab.model.Task;
import it.intesys.codylab.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task/{id}")
    public Task getProjectById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/task")
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    @PostMapping("/task/{id}/users")
    public ResponseEntity<TaskDTO> addUserToTask(@PathVariable Long id, @RequestBody List<Long> userIds) {
        TaskDTO updated = taskService.addUsersToTask(id, userIds);
        return ResponseEntity.ok(updated);
    }
}
