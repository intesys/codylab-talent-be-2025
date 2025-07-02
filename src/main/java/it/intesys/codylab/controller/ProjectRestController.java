package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.*;
import it.intesys.codylab.api.rest.ProjectsApi;
import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.mapper.ProjectMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    private ProjectMapper projectMapper;

    public ProjectRestController(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @Override
    public ResponseEntity<ProjectsPageApiDTO> getProjects(Integer pageNumber, Integer size, String sort, ProjectFilterApiDTO projectFilter) {
        logger.info("Fetching paginated projects");

        // Ottieni i progetti paginati dal service
        Page<Project> pagedProjects = projectService.findAllPaginated(pageNumber, size);

        // Mappa da Project a ProjectsApiDTO
        List<ProjectsApiDTO> projectDtos = pagedProjects.getContent()
                .stream()
                .map(projectMapper::toApiDTO)
                .toList();

        // Costruisci la risposta DTO
        ProjectsPageApiDTO response = new ProjectsPageApiDTO();
        response.setContent(projectDtos);
        response.setTotalElements(pagedProjects.getTotalElements());
        response.setTotalPages(pagedProjects.getTotalPages());
        response.setSize(pagedProjects.getSize());
        response.setNumber(pagedProjects.getNumber());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ProjectsApiDTO> createProject(ProjectsApiDTO projectsApiDTO) {
        if (projectsApiDTO.getDataInizio() == null) {
            projectsApiDTO.setDataInizio(OffsetDateTime.now().toLocalDate());
        }
        ProjectsApiDTO createdProject = projectService.createProject(projectsApiDTO);
        return ResponseEntity.created(URI.create("/api/v1/projects/" + createdProject.getId())).body(createdProject);
    }

    @Override
    public ResponseEntity<ProjectsApiDTO> getProjectById(Long projectId) {
        ProjectsApiDTO dto = projectService.getProjectById(projectId);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<List<ProjectsWithResponsabileApiDTO>> getProjectsWithResponsabile() {
        return ResponseEntity.ok(projectService.getAllProjectsWithResponsabile());
    }

    @Override
    public ResponseEntity<ProjectsApiDTO> updateProject(Long projectId, ProjectsApiDTO projectsApiDTO) {
        ProjectsApiDTO updatedProject = projectService.updateProject(projectId, projectsApiDTO);
        if (updatedProject != null) {
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Override
    public ResponseEntity<Void> deleteProject(Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }



    /**
     @GetMapping("/projects") public List<ProjectDTO> getProjects() {
     return projectService.findAll();
     }

     @GetMapping("/project/{id}") public Project getProjectById(@PathVariable Long id) {
     return projectService.getProjectById(id);
     }

     @GetMapping("/project/codice/{codice}") public ProjectDTO getProjectByCodice(@PathVariable String codice) {
     return projectService.findByCodice(codice);
     }

     */
}