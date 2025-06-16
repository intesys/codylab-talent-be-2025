package it.intesys.codylab.controller;

import it.intesys.codylab.model.User;
import it.intesys.codylab.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersRestController {

    private final UserService userService;

    public UsersRestController(UserService userService) {
        this.userService = userService;
    }

//  findById
    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.findById(userId);
    }

//  findAll
    @GetMapping
    public List<User> getUsers() {
        // TODO Esercizio 1: implementare il metodo per restituire tutti gli utenti
        return userService.findAll();
    }

//  addUser
    @PostMapping
    public void addUser(@RequestBody User user) {
        // TODO Esercizio 2: implementare il metodo per aggiungere un nuovo utente
        userService.save(user);
    }

//  updateUser
    @PutMapping("/{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody User user) {
        userService.update(userId, user);
    }

//  updateUserProfile
    @PatchMapping("/{userId}/profilo")
    public void updateUserProfile(@PathVariable Long userId, @RequestBody User user) {
        userService.updateUserProfile(userId, user);
    }

//  deleteUser
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }
}