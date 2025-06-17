package it.intesys.codylab.controller;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProjectRestController {

    private final ProjectService projectService;

    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<ProjectDTO> getProjects() {
        return projectService.findAll();
    }

    @GetMapping("/project/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/project/codice/{codice}")
    public ProjectDTO getProjectByCodice(@PathVariable String codice) {
        return projectService.findByCodice(codice);
    }
}
