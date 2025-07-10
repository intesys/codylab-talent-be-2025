package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.ProblemApiDTO;
import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.api.rest.ProjectsApi;
import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProjectRestController implements ProjectsApi {

    private static final Logger logger = LoggerFactory.getLogger(ProjectRestController.class);

    private ProjectService projectService;

    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
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

    @Override
    public ResponseEntity<ProjectsApiDTO> getProjectById(Long projectId) {
        return ResponseEntity.notFound().build();
    }


     @GetMapping("/projects") public List<ProjectDTO> getProjects() {
     return projectService.findAll();
     }

     /**
     @GetMapping("/project/{id}") public Project getProjectById(@PathVariable Long id) {
     return projectService.getProjectById(id);
     }

     @GetMapping("/project/codice/{codice}") public ProjectDTO getProjectByCodice(@PathVariable String codice) {
     return projectService.findByCodice(codice);
     }

     */
}