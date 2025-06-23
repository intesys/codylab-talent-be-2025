package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.api.rest.ProjectsApi;
import it.intesys.codylab.mapper.ProjectMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.service.ProjectService;
import it.intesys.codylab.service.SlotService;
import it.intesys.codylab.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProjectRestController implements ProjectsApi {

    private static final Logger logger = LoggerFactory.getLogger(ProjectRestController.class);
    private ProjectService projectService;
    private TaskService taskService;
    private SlotService slotService;
    private ProjectMapper projectMapper;


    public ProjectRestController(ProjectService projectService , TaskService taskService, SlotService slotService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.slotService = slotService;
        this.projectMapper = projectMapper;
    }


    @Override
    public ResponseEntity<ProjectsApiDTO> createProject(ProjectsApiDTO projectsApiDTO) {
        try {
            ProjectsApiDTO savedProject = projectService.save(projectsApiDTO);
            return ResponseEntity
                    .created(URI.create("/api/v1/projects/" + savedProject.getId()))
                    .body(savedProject);
        } catch (Exception e) {
            logger.error("Error creating project {}", projectsApiDTO, e);
            return ResponseEntity.status(500).body(new ProjectsApiDTO());
        }
    }




    @Override
    public ResponseEntity<List<ProjectsApiDTO>> searchProjects(
            Integer pageNumber,
            Integer size,
            String sort,
            ProjectFilterApiDTO projectFilter) {

        // La logica per ottenere i progetti filtrati/paginati ecc.

        List<ProjectsApiDTO> projects = projectService.getAllProjects();

        if (projects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(projects);
    }
    @Override
    public ResponseEntity<ProjectsApiDTO> getProjectById(Long id) {
        ProjectsApiDTO projectDTO = projectService.getProjectById(id);
        if (projectDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(projectDTO);
    }

    @Override
    public ResponseEntity<ProjectsApiDTO> updateProject(Long id, ProjectsApiDTO projectsApiDTO) {
        try {
            ProjectsApiDTO updatedProject = projectService.updateProject(id, projectsApiDTO);
            return ResponseEntity.ok(updatedProject);
        } catch (Exception e) {
            logger.error("Error updating project with id {}", id, e);
            return ResponseEntity.status(500).body(new ProjectsApiDTO());
        }
    }


    /*sff
    @GetMapping("/projects")
    public List<ProjectDTO> getProjects() {
        return projectService.findAll();
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable Long id) {
        ProjectDTO dto = projectService.getProjectById(id);
        return dto != null? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }


    @GetMapping("/project/codice/{codice}")
    public ProjectDTO getProjectByCodice(@PathVariable String codice) {
        return projectService.findByCodice(codice);
    }
    @PostMapping("/project/save")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO saved = projectService.save(projectDTO);
        return ResponseEntity.ok(saved);
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
*/


}