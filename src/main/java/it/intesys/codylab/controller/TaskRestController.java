package it.intesys.codylab.controller;

import it.intesys.codylab.model.Task;
import it.intesys.codylab.service.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {
    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PostMapping
    public void addTask(@RequestBody Task task) {
        taskService.save(task);
    }
}
