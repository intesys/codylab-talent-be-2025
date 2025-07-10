package it.intesys.codylab.web;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.api.rest.ProjectsApi;
import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        List<ProjectsApiDTO> projects = projectService.simpleGetProjects();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @PostMapping("/project/delete/{id}")
    public String deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
        return "redirect:/mvc/projects";
    }

    @GetMapping("/project/add")
    public String showAddProjectForm(Model model) {
        model.addAttribute("project", new ProjectDTO());
        return "project-add";
    }

    @PostMapping("/project")
    public String addProject(@ModelAttribute("project") ProjectsApiDTO project) {
        projectService.createProject(project);
        return "redirect:/mvc/projects";
    }

}