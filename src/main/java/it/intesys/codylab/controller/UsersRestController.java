package it.intesys.codylab.controller;

import it.intesys.codylab.business.UserService;
import it.intesys.codylab.dto.UpdateUserProfileRequest;
import it.intesys.codylab.dto.User;
import it.intesys.codylab.dto.UserProfile;
import it.intesys.codylab.dto.WorkingHours;
import it.intesys.codylab.repository.MemoryUserRepository;
import it.intesys.codylab.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public List<User> getUsers(
            @RequestParam(required = false) UserProfile profile,
            @RequestParam(required = false) String minHours
    ) {
        Duration minDuration = minHours != null ? Duration.parse(minHours) : null;
        return userService.findUsersByFilter(profile, minDuration);
    }


    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user); 

        URI location = URI.create("/users/" + savedUser.getId());

        return ResponseEntity
                .created(location)
                .body(savedUser);
    }

    @PatchMapping("/{userId}/profile")
    public void updateUserProfile(@PathVariable Long userId, @RequestBody UpdateUserProfileRequest request) {
        userService.updateUserProfile(userId, request.getProfile());
    }

    @PatchMapping("/{userId}/workingHours")
    public void updateWorkingHours(@PathVariable Long userId, @RequestBody WorkingHours workingHours) {
        userService.updateWorkingHours(userId, workingHours);
    }

    @PutMapping("/{userId}")
    public User updateAllUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        return userService.updateAllUser(userId, updatedUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}