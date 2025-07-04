package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.TaskFilterApiDTO;
import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.api.model.TasksPageApiDTO;
import it.intesys.codylab.api.rest.TasksApi;
import it.intesys.codylab.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TaskRestController implements TasksApi {

    private TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<TasksPageApiDTO> getTasks(Integer pageNumber, Integer size, String sort, TaskFilterApiDTO taskFilter) {
        Page<TasksApiDTO> tasks = taskService.getTasks(taskFilter, pageNumber, size, sort);
        TasksPageApiDTO tasksPageApiDTO = new TasksPageApiDTO();
        tasksPageApiDTO.setContent(tasks.getContent());
        tasksPageApiDTO.setTotalElements(tasks.getTotalElements());
        tasksPageApiDTO.setTotalPages(tasks.getTotalPages());
        tasksPageApiDTO.setNumber(tasks.getNumber());
        tasksPageApiDTO.setSize(tasks.getSize());
        return ResponseEntity.ok(tasksPageApiDTO);
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
