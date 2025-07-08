package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.api.model.ProjectsPageApiDTO;
import it.intesys.codylab.api.rest.ProjectsApi;
import it.intesys.codylab.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class ProjectRestController implements ProjectsApi {

    private ProjectService projectService;

    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

//    Restituizione tutti progetti
    @Override
    public ResponseEntity<ProjectsPageApiDTO> getProjects(
            Integer pageNumber,
            Integer size,
            String sort,
            ProjectFilterApiDTO projectFilter) {

        Page<ProjectsApiDTO> pagedProjects = projectService.getProjects(projectFilter, pageNumber, size, sort);
        ProjectsPageApiDTO projectsPageApiDTO = new ProjectsPageApiDTO();
        projectsPageApiDTO.setContent(pagedProjects.getContent());
        projectsPageApiDTO.setTotalElements(pagedProjects.getTotalElements());
        projectsPageApiDTO.setTotalPages(pagedProjects.getTotalPages());
        projectsPageApiDTO.setNumber(pagedProjects.getNumber());
        projectsPageApiDTO.setSize(pagedProjects.getSize());

        return ResponseEntity.ok(projectsPageApiDTO);
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

    @Override
    public ResponseEntity<Void> deleteProject(Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}