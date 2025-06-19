package it.intesys.codylab.controller;

import it.intesys.codylab.dto.TaskDTO;
import it.intesys.codylab.model.Task;
import it.intesys.codylab.service.TaskService;
import org.springframework.web.bind.annotation.*;

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
}
