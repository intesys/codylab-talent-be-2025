package it.intesys.codylab.controller;

import it.intesys.codylab.dto.UserDTO;
import it.intesys.codylab.model.User;
import it.intesys.codylab.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserDTO> getUsers(){
        return userService.findAll();
    }
}
