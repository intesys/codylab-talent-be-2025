package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.UserFilterApiDTO;
import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.api.rest.UsersApi;
import it.intesys.codylab.mapper.UserMapper;
import it.intesys.codylab.model.User;
import it.intesys.codylab.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserRestController implements UsersApi {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserRestController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<List<UsersApiDTO>> getUsers(Integer pageNumber, Integer size, String sort, UserFilterApiDTO userFilterApiDTO) {
        List<UsersApiDTO> users = userService.getUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<UsersApiDTO> getUserById(Long id) {
        UsersApiDTO user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UsersApiDTO> createUser(UsersApiDTO userDto) {
        UsersApiDTO createdUser = userService.createUser(userDto);
        URI location = URI.create("/api/v1/users/" + createdUser.getId());
        return ResponseEntity.created(location).body(createdUser);
    }

    @Override
    public ResponseEntity<UsersApiDTO> updateUser(Long id, UsersApiDTO userDto) {
        UsersApiDTO updatedUser = userService.updateUser(id, userDto);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<UsersApiDTO> getUserWithProgettiGestiti(Long id) {
        User user = userService.findUserWithProgettiResponsabile(id);
        if (user != null) {
            UsersApiDTO dto = userMapper.toApiDTO(user);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
