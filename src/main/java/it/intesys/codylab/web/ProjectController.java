package it.intesys.codylab.web;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mvc")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/project/add")
    public String addProject(Model model) {
        model.addAttribute("project", new ProjectDTO());
        return "project-add";
    }

    @GetMapping("/projects")
    public String viewProject(Model model) {
        List<ProjectDTO> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/project/codice/{codice}")
    public String viewProject(@PathVariable String codice, Model model) {
        ProjectDTO project = projectService.findByCodice(codice);
        model.addAttribute("project", project);
        return "project";
    }

    @PostMapping("/project")
    public String addProject(@ModelAttribute ProjectDTO projectDTO, Model model) {
        ProjectDTO savedProject = projectService.save(projectDTO);
        model.addAttribute("project", savedProject);
        return "redirect:/mvc/project/codice/" + savedProject.getCodice();
    }

}
