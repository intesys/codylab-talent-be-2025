package it.intesys.codylab.web;

import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/project/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/mvc/projects";
    }

    @GetMapping("/project/add")
    public String showAddProjectForm(Model model) {
        model.addAttribute("project", new ProjectsApiDTO());
        return "project-add";
    }

    @PostMapping("/project")
    public String addProject(@ModelAttribute("project") ProjectsApiDTO project) {
        projectService.createProject(project);
        return "redirect:/mvc/projects";
    }
}