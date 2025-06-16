package it.intesys.codylab.controller;

import it.intesys.codylab.model.Task;
import it.intesys.codylab.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {
    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

//  findById
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.findById(id);
    }

//  findAll
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

//  addTask
    @PostMapping
    public void addTask(@RequestBody Task task) {
        taskService.save(task);
    }
}
