package it.intesys.codylab.controller;

import it.intesys.codylab.model.User;
import it.intesys.codylab.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/v1")
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public List<User> getUsers() {

        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserNameById(@PathVariable Long id) {
        return userService.getUserNameById(id);
    }

}