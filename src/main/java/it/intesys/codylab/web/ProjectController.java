package it.intesys.codylab.web;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.service.ProjectService;
import it.intesys.codylab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mvc")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/projects")
    public String getProjects(Model model) {
        List<ProjectsApiDTO> projects = projectService.simpleGetProjects();
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
    public String addProject(@ModelAttribute("project") ProjectsApiDTO project, Model model) {
        if (!userService.existsByUsername(project.getManager())) {
            model.addAttribute("managerError", "User not found: " + project.getManager());
            return "project-add";
        }
        projectService.createProject(project);
        return "redirect:/mvc/projects";
    }
}
