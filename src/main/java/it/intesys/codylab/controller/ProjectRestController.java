package it.intesys.codylab.controller;

import it.intesys.codylab.dto.AssignUserToTaskDTO;
import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.dto.SlotDTO;
import it.intesys.codylab.dto.TaskDTO;
import it.intesys.codylab.service.ProjectService;
import it.intesys.codylab.service.SlotService;
import it.intesys.codylab.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProjectRestController {

    private ProjectService projectService;
    private TaskService taskService;
    private SlotService slotService;


    public ProjectRestController(ProjectService projectService , TaskService taskService, SlotService slotService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.slotService = slotService;
    }

    @GetMapping("/projects")
    public List<ProjectDTO> getProjects() {
        return projectService.findAll();
    }

    @GetMapping("/project/{id}")
    public ProjectDTO getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/project/codice/{codice}")
    public ProjectDTO getProjectByCodice(@PathVariable String codice) {
        return projectService.findByCodice(codice);
    }
    @PostMapping("/project/save")
    public ProjectDTO saveProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.save(projectDTO);
    }

    @PostMapping("/task/save")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO savedTask = taskService.saveTask(taskDTO);
        return ResponseEntity.ok(savedTask);
    }

    @PostMapping("/slot/save")
    public ResponseEntity<SlotDTO> createSlot(@RequestBody SlotDTO slotDTO) {
        SlotDTO savedSlot = slotService.save(slotDTO);
        return ResponseEntity.ok(savedSlot);
    }

    @PostMapping("/user-to-task")
    public ResponseEntity<Object> assignUserToTaskDTO(@RequestBody AssignUserToTaskDTO assignUserToTaskDTO) {
        taskService.assignUserToTask(assignUserToTaskDTO.getUserId(), assignUserToTaskDTO.getTaskId());
        return ResponseEntity.ok().build();
    }

}