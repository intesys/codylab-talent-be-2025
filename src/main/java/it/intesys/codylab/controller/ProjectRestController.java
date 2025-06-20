package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.ProblemApiDTO;
import it.intesys.codylab.api.model.ProjectApiDTO;
import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.api.rest.ProjectsApi;
import it.intesys.codylab.dto.AssignUserToTaskDTO;
import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.dto.SlotDTO;
import it.intesys.codylab.dto.TaskDTO;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.service.ProjectService;
import it.intesys.codylab.service.SlotService;
import it.intesys.codylab.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ProjectRestController implements ProjectsApi {

    private static final Logger logger = LoggerFactory.getLogger(ProjectRestController.class);
    private ProjectService projectService;
    private TaskService taskService;
    private SlotService slotService;



    public ProjectRestController(ProjectService projectService , TaskService taskService, SlotService slotService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.slotService = slotService;
    }

    @Override
    public ResponseEntity<List<ProjectsApiDTO>> searchProjects
            (Integer pageNumber,
             Integer size,
             String sort,
             ProjectFilterApiDTO projectFilter) {
        ProjectsApiDTO projectsApiDTO = new ProjectsApiDTO();
        projectsApiDTO.setId(1L);
        projectsApiDTO.setNome("Example Project");
        // ResponseEntity.noContent().build();
        return ResponseEntity.ok()
                .header("x-total-count", "1")
                .header("x-page-size", "10")
                .header("x-page-number", "0")
                .body(List.of(projectsApiDTO));
    }

    @Override
    public ResponseEntity<ProjectsApiDTO> createProject(ProjectsApiDTO projectsApiDTO) {
        try {
            ProjectDTO projectDTO = new ProjectDTO();
            // mapper da ProjectsApiDTO a ProjectDTO
            //projectDTO = projectService.save(projectDTO);
            // mapper da ProjectDTO a ProjectsApiDTO
            ProjectsApiDTO projectsApiDTOCreated = new ProjectsApiDTO();
            projectsApiDTOCreated.setId(1L);
            projectsApiDTOCreated.setNome("Example Project");
            return ResponseEntity
                    .created(URI.create("/api/v1/projects/" + projectsApiDTOCreated.getId()))
                    .body(projectsApiDTOCreated);
        } catch (Exception e) {
            logger.error("Error creating project {}", projectsApiDTO, e);
            ProblemApiDTO dettaglioErrore = new ProblemApiDTO();
            dettaglioErrore.detail("Error creating project " + projectsApiDTO);
            dettaglioErrore.setStatus(500);
            dettaglioErrore.setTitle("Internal Server Error");
            dettaglioErrore.setInstance(URI.create("/api/v1/projects"));
            dettaglioErrore.setTimestamp(OffsetDateTime.now());
            ResponseEntity.internalServerError().body(dettaglioErrore);
            return ResponseEntity.internalServerError().build();
        }
    }

//    @Override
//    public ResponseEntity<ProjectsApiDTO> getProjectById(Long projectId) {
//        return ResponseEntity.notFound().build();
//    }

    @GetMapping("/projects")
    public List<ProjectsApiDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectsApiDTO> getProjectById(@PathVariable Long id) {
        ProjectsApiDTO project = projectService.getProjectById(id);
        return project != null ? ResponseEntity.ok(project) : ResponseEntity.notFound().build();
    }
    /*
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