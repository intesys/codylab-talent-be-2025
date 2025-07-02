package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.*;
import it.intesys.codylab.api.rest.ProjectsApi;
import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public ResponseEntity<ProjectsPageApiDTO> getProjects(Integer pageNumber, Integer size, String sort, ProjectFilterApiDTO projectFilter) {
        logger.info("Fetching projects with filter: {}", projectFilter);

            List<ProjectsApiDTO> projects = projectService.getProjectByUsername(projectFilter.getUsername());
            ProjectsPageApiDTO projectsPageApiDTO = new ProjectsPageApiDTO();
            projectsPageApiDTO.setContent(projects);
            projectsPageApiDTO.setTotalElements((long) projects.size());

            /*getProjectByCodice(projectFilter.getCodice());*/
            /* getProjectsByUserIdOrProjectIds(projectFilter);*/
            /*getProjectByCodiceAndUsername(projectFilter.getCodice(), projectFilter.getUsername());*/
            return ResponseEntity.ok(projectsPageApiDTO);
    }

    public ResponseEntity<List<ProjectsApiDTO>> xgetProjects(
            Integer pageNumber,
            Integer size,
            String sort,
            ProjectFilterApiDTO projectFilter) {

        List<ProjectsApiDTO> projects = projectService.getProjectByUsername(projectFilter.getUsername());
        /*getProjectByCodice(projectFilter.getCodice());*/
        /* getProjectsByUserIdOrProjectIds(projectFilter);*/
        /*getProjectByCodiceAndUsername(projectFilter.getCodice(), projectFilter.getUsername());*/
        if (projects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(projects);
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
        return ResponseEntity.notFound().build();
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