package it.intesys.codylab.web;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mvc")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @GetMapping("/projects")
    public String projects(Model model) {
        List<ProjectDTO> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @PostMapping("/project/delete/{id}")
    public String deleteProject(@PathVariable("id") Long id) {
        projectService.delete(id);
        return "redirect:/mvc/projects";
    }

}
