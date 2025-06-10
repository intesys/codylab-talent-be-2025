package it.intesys.codylab.controller;

import it.intesys.codylab.business.UserService;
import it.intesys.codylab.dto.User;
import it.intesys.codylab.dto.UserProfile;
import it.intesys.codylab.dto.WorkingHours;
import it.intesys.codylab.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
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
        return userService.getUser(userId);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PatchMapping("/{userId}/profile")
    public void updateUserProfile(@PathVariable Long userId, @RequestBody UserProfile userProfile) {
        userService.updateUserProfile(userId, userProfile);
    }
}
