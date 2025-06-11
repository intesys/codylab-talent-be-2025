package it.intesys.codylab.web;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/mvc/projects")
    public String viewProject(Model model) {
        List<ProjectDTO> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/mvc/project/codice/{codice}")
    public String viewProject(@PathVariable String codice, Model model) {
        ProjectDTO project = projectService.findByCodice(codice);
        model.addAttribute("project", project);
        return "project";
    }

}
