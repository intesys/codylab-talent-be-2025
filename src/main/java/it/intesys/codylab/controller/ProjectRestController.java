package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.api.rest.ProjectsApi;
import it.intesys.codylab.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProjectRestController implements ProjectsApi {

    private ProjectService projectService;

    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public ResponseEntity<List<ProjectsApiDTO>> getProjects
            (Integer pageNumber,
             Integer size,
             String sort,
             ProjectFilterApiDTO projectFilter) {
        ProjectsApiDTO projectsApiDTO = new ProjectsApiDTO();
        projectsApiDTO.setId(1L);
        projectsApiDTO.setNome("Example Project");
        return ResponseEntity
                .ok(List.of(projectsApiDTO));
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