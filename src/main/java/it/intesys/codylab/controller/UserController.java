package it.intesys.codylab.controller;

import it.intesys.codylab.dto.TaskDTO;
import it.intesys.codylab.model.User;
import it.intesys.codylab.service.TaskService;
import it.intesys.codylab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;
    private TaskService TaskService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        updatedUser.setId(id);
        return userService.save(updatedUser);
    }

    @PostMapping("/{taskId}/users")
    public TaskDTO assignUsersToTask(@PathVariable Long taskId, @RequestBody List<Long> userIds) {
        return TaskService.addUsersToTask(taskId, userIds);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
