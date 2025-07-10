package it.intesys.codylab.web;

import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        ProjectFilterApiDTO filter = new ProjectFilterApiDTO();
        Page<ProjectsApiDTO> projects = projectService.getProjects(filter, 0, 10, null);
        model.addAttribute("projects", projects);
        return "projects";
    }
}
