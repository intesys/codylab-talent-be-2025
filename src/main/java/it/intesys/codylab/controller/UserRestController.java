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

    private UserService userService;
    private UserMapper userMapper;
    public UserRestController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<UsersApiDTO> createUser(UsersApiDTO usersApiDTO) {
        User user = userMapper.toEntity(usersApiDTO);
        User savedUser = userService.saveUser(user);
        UsersApiDTO responseDto = userMapper.toApiDTO(savedUser);
        return ResponseEntity
                .created(URI.create("/api/v1/users/" + responseDto.getId()))
                .body(responseDto);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        User user = userService.getUserNameById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUserById(userId);  // Metodo corretto per cancellare
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UsersApiDTO> getUserById(Long userId) {
        User user = userService.getUserNameById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UsersApiDTO usersApiDTO = userMapper.toApiDTO(user);
        return ResponseEntity.ok(usersApiDTO);
    }
    @Override
    public ResponseEntity<List<UsersApiDTO>> searchUsers(
            Integer pageNumber,
            Integer size,
            String sort,
            UserFilterApiDTO userFilterApiDTO
    ) {
        List<User> users = userService.getUsers();

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<UsersApiDTO> dtoList = users.stream()
                .map(userMapper::toApiDTO)
                .toList();

        return ResponseEntity.ok(dtoList);
    }



    @Override
    public ResponseEntity<UsersApiDTO> updateUser(Long userId, UsersApiDTO usersApiDTO) {
        return UsersApi.super.updateUser(userId, usersApiDTO);
    }
    }


    // @Override
    // public ResponseEntity<UsersApiDTO> searchUsers(UserFilterApiDTO userFilterApiDTO) {
    //     // Implement the search logic based on the filter criteria
    //     List<User> users = (List<User>) userRepository.findAll(); // Replace with actual filtering logic
    //     UsersApiDTO usersApiDTO = new UsersApiDTO();
    //     usersApiDTO.setUsers(users);
    //     return ResponseEntity.ok(usersApiDTO);
    // }

    // @GetMapping("/users")
    // public List<User> getUsers() {
    //     return userService.getUsers();
    // }

    // @GetMapping("/user/{id}")
    // public User getUserNameById(@PathVariable Long id) {
    //     return userService.getUserNameById(id);
    // }


