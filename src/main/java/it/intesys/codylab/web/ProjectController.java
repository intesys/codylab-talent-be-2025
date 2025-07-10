package it.intesys.codylab.web;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.repository.ProjectRepository;
import it.intesys.codylab.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mvc")
public class ProjectController {

    private ProjectService projectService;
    private ProjectRepository projectRepository;

    public ProjectController(ProjectService projectService, ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
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

    @GetMapping("/project/add")
    public String showForm(Model model) {
        model.addAttribute("project", new Project()); // Assicurati che Project abbia i campi codice, nome, ecc.
        return "project-add"; // Deve corrispondere al nome del tuo file HTML (senza estensione)
    }

    @PostMapping("/project")
    public String saveProject(@ModelAttribute Project project) {
        // logica di salvataggio
        projectRepository.save(project);
        return "redirect:/mvc/projects";
    }

}
