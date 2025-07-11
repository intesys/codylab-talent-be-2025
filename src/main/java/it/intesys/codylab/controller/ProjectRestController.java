package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.*;
import it.intesys.codylab.api.rest.ProjectsApi;
import it.intesys.codylab.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            String username,
            List<String> projectCodes) {

        ProjectFilterApiDTO filter = new ProjectFilterApiDTO();
        filter.setUsername(username);
        filter.setProjectCodes(projectCodes);

        if (pageNumber == null) pageNumber = 0;
        if (size == null) size = 10;

        Page<ProjectsApiDTO> pagedProjects = (Page<ProjectsApiDTO>) projectService.getProjects(filter, pageNumber, size, sort);

        return ResponseEntity.ok(
                pagedProjects.getContent()
        );
    }



    @Override
    public ResponseEntity<ProjectsApiDTO> getProjectById(Long id) {
        ProjectsApiDTO project = projectService.getProjectById(id);

        if (project == null) {
            return ResponseEntity.notFound().build();
        }
        ProjectsApiDTO dto = projectService.getProjectById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
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

    @Override
    public ResponseEntity<Void> deleteProject(Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
}
}