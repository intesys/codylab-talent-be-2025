package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.api.rest.ProjectsApi;
import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProjectRestController implements ProjectsApi {

    private ProjectService projectService;

    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public ResponseEntity<List<ProjectsApiDTO>> getProjects(
            Integer pageNumber,
            Integer size,
            String sort,
            ProjectFilterApiDTO projectFilter) {
        List<ProjectsApiDTO> projects = projectService.getProjects();

        if (projects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(projects);
    }

    @Override
    public ResponseEntity<ProjectsApiDTO> getProjectById(Long id) {
        ProjectsApiDTO project = projectService.getProjectById(id);

        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(project);
    }

    @Override
    public ResponseEntity<ProjectsApiDTO> createProject(ProjectsApiDTO projectsApiDTO) {
        ProjectsApiDTO createdProject = projectService.createProject(projectsApiDTO);
        URI location = URI.create("/api/v1/projects/" + createdProject.getId());
        return ResponseEntity.created(location).body(createdProject);
    }

    @Override
    public ResponseEntity<ProjectsApiDTO> updateProject(Long id, ProjectsApiDTO projectsApiDTO) {
        ProjectsApiDTO updatedProject = projectService.updateProject(id, projectsApiDTO);

        if (updatedProject == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedProject);
    }

}