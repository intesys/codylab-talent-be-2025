package it.intesys.codylab.controller;

import it.intesys.codylab.model.User;
import it.intesys.codylab.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//
//    @GetMapping
//    public List<User> getUsers() {
//        return userService.getUsers();
//    }
//
//    @PostMapping
//    public void addUser(@RequestBody User user) {
//        userService.saveUser(user);
//    }
//
//    @PatchMapping("/{userId}/profile")
//    public void updateUserProfile(@PathVariable Long userId, @RequestBody UserProfile userProfile) {
//        userService.updateUserProfile(userId, userProfile);
//    }
}
