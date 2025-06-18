package it.intesys.codylab.controller;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProjectRestController {

    private ProjectService projectService;

    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<ProjectDTO> getProjects() {
        return projectService.findAll();
    }

    @GetMapping("/project/{id}")
    public ProjectDTO getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/project/codice/{codice}")
    public ProjectDTO getProjectByCodice(@PathVariable String codice) {
        return projectService.findByCodice(codice);
    }
    @PostMapping("/project/save")
    public ProjectDTO saveProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.save(projectDTO);
    }

//    @GetMapping("/project/durata/{durata}")
//    public List<ProjectDTO> getProjectByDurata(@PathVariable Integer durata) {
//        return projectService.findByDurata(durata);
//    }

}