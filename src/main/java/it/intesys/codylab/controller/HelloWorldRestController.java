package it.intesys.codylab.controller;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.User;
import it.intesys.codylab.service.ProjectService;
import it.intesys.codylab.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldRestController {

    private UserService userService;

    private ProjectService projectService;

    public HelloWorldRestController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/user/{id}")
    public User getUserNameById(@PathVariable Long id) {
        return userService.getUserNameById(id);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }
    @GetMapping("/project/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/project/codice/{codice}")
    public ProjectDTO getProjectByCodice(@PathVariable String codice) {
        return projectService.findByCodice(codice);
    }

    @GetMapping("/project/durata/{durata}")
    public List<Project> getProjectByDurata(@PathVariable Integer durata) {
        return projectService.findByDurata(durata);
    }
}
