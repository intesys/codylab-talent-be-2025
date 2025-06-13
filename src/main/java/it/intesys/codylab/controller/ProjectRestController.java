package it.intesys.codylab.controller;

import it.intesys.codylab.model.Project;
import it.intesys.codylab.service.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectRestController {

    private final ProjectService projectService;

    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/codice/{codice}")
    public Project getProjectByCodice(@PathVariable String codice){
        return projectService.findByCodice(codice);
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id){
        return projectService.findById(id);
    }
}
