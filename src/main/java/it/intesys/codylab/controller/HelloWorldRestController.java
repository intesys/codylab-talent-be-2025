package it.intesys.codylab.controller;

import it.intesys.codylab.model.User;
import it.intesys.codylab.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldRestController {

    private UserService userService;

    public HelloWorldRestController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/user/{id}")
    public User getUserNameById(@PathVariable Long id) {
        return userService.getUserNameById(id);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

}
