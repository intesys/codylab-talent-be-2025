package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.api.rest.UsersApi;
import it.intesys.codylab.mapper.UserMapper;
import it.intesys.codylab.model.User;
import it.intesys.codylab.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<List<UsersApiDTO>> getUsers(
            Integer pageNumber,
            Integer size,
            String sort,
            @RequestParam(required = false) List<Long> ids,
            @RequestParam(required = false) Long taskId) {

        if (pageNumber == null) pageNumber = 0;
        if (size == null) size = 10;
        if (sort == null || sort.isEmpty()) sort = "id";
        if (ids == null) ids = List.of();

        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sort));

        Page<UsersApiDTO> users = userService.getUsers(ids, taskId, pageable);

        if (users == null || users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(users.getContent());
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
        userDto.setId(null);
        userDto.setManagedProjects(null);

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
    public ResponseEntity<UsersApiDTO> getUserWithManagedProjects(Long id) {
        User user = userService.findUserWithProjectManagers(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UsersApiDTO dto = userMapper.toApiDTO(user);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<UsersApiDTO> getUserByUsername(String username) {
        if (username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().build();  // opzionale, non è definito nel tuo OpenAPI
        }

        UsersApiDTO userDto = userService.getUserByUsername(username);

        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userDto);
    }

}
