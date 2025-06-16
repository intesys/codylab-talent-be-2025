package it.intesys.codylab.controller;

import it.intesys.codylab.model.User;
import it.intesys.codylab.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersRestController {

    private final UserService userService;

    public UsersRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @GetMapping("/allUsers")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
//        // TODO Esercizio 2: implementare il metodo per aggiungere un nuovo utente
    }
}
