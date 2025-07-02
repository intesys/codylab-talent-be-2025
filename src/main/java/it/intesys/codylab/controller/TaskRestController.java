package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.TaskFilterApiDTO;
import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.api.model.TasksPageApiDTO;
import it.intesys.codylab.api.rest.TasksApi;
import it.intesys.codylab.mapper.TaskMapper;
import it.intesys.codylab.model.Task;
import it.intesys.codylab.repository.TaskRepository;
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
    private final TaskRepository taskRepository;
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskRestController(TaskRepository taskRepository, TaskService taskService, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.taskMapper = taskMapper;
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
    public ResponseEntity<TasksPageApiDTO> getTasks(Integer pageNumber, Integer size, String sort, TaskFilterApiDTO taskFilter) {
        Page<Task> pagedTasks = taskService.findAllPaginated(pageNumber, size);

        List<TasksApiDTO> taskDtos = pagedTasks.getContent()
                .stream()
                .map(taskMapper::toApiDTO)
                .toList();

        TasksPageApiDTO response = new TasksPageApiDTO();
        response.setContent(taskDtos);
        response.setTotalElements(pagedTasks.getTotalElements());
        response.setTotalPages(pagedTasks.getTotalPages());
        response.setSize(pagedTasks.getSize());
        response.setNumber(pagedTasks.getNumber());

        return ResponseEntity.ok(response);

    }

    //    @Override
//    public ResponseEntity<List<TasksApiDTO>> getTasks(Integer pageNumber, Integer size, String sort, TaskFilterApiDTO taskFilter) {
//        List<TasksApiDTO> tasks = taskService.getTasks();
//        if (tasks.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(tasks);
//
//    }

    @Override
    public ResponseEntity<TasksApiDTO> updateTask(Long id, TasksApiDTO tasksApiDTO) {
        TasksApiDTO updateTask = taskService.updateTask(id, tasksApiDTO);
        if (updateTask != null) {
            return ResponseEntity.ok(updateTask);
        } else {
            return ResponseEntity.notFound().build();
        }}


}
