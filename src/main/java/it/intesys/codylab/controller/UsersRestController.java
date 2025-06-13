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

    @GetMapping
    public List<User> getUsers() {
        // TODO Esercizio 1: implementare il metodo per restituire tutti gli utenti
        return Collections.emptyList();
    }

//    @PostMapping
//    public void addUser(@RequestBody User user) {
//        // TODO Esercizio 2: implementare il metodo per aggiungere un nuovo utente
//    }
//
//    @PatchMapping("/{userId}/profile")
//    public void updateUserProfile(@PathVariable Long userId, @RequestBody UserProfile userProfile) {
//        userService.updateUserProfile(userId, userProfile);
//    }
}
