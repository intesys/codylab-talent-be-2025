package it.intesys.codylab.controller;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectRestController {

    private final ProjectService projectService;

    @Autowired
    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<ProjectDTO> getAllProjects() {
        return projectService.findAll();
    }

    @GetMapping("/project/{id}")
    public ProjectDTO getProjectById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PostMapping("/project")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDTO createProject(@RequestBody ProjectDTO project) {
        return projectService.save(project);
    }

    @PutMapping("/project/{id}")
    public ProjectDTO updateProject(@PathVariable Long id, @RequestBody ProjectDTO updatedProject) {
        return projectService.update(id, updatedProject);
    }

    @DeleteMapping("/project/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable Long id) {
        projectService.delete(id);
    }
}
