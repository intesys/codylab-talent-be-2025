package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.UserFilterApiDTO;
import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.api.model.UsersPageApiDTO;
import it.intesys.codylab.api.rest.UsersApi;
import it.intesys.codylab.mapper.UserMapper;
import it.intesys.codylab.model.User;
import it.intesys.codylab.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<UsersApiDTO> getUserWithProgettiGestiti(Long id) {
        User user = userService.findUtenteWithProgettiDirigente(id);
        if (user != null) {
            UsersApiDTO dto = userMapper.toApiDTO(user);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<UsersApiDTO> createUser(UsersApiDTO usersApiDTO) {
        User user = userMapper.toEntity(usersApiDTO);
        User savedUser = userService.findByUsername(user.getUsername());
        if (savedUser != null) {
            return ResponseEntity.status(409).build(); // Conflict if user already exists
        }
        savedUser = userService.createUser(user);
        UsersApiDTO createdUserDto = userMapper.toApiDTO(savedUser);
        return ResponseEntity.created(URI.create("/api/v1/users/" + createdUserDto.getId())).body(createdUserDto);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        User user = userService.findUtenteWithProgettiDirigente(id);
        if (user != null) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<UsersApiDTO> updateUser(Long id, UsersApiDTO usersApiDTO) {
        User existingUser = userService.findUtenteWithProgettiDirigente(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        User userToUpdate = userMapper.toEntity(usersApiDTO);
        userToUpdate.setId(id);
        User updatedUser = userService.updateUser(userToUpdate);
        UsersApiDTO updatedUserDto = userMapper.toApiDTO(updatedUser);
        return ResponseEntity.ok(updatedUserDto);
    }

    @Override
    public ResponseEntity<UsersApiDTO> getUserById(Long id) {
        User user = userService.findUtenteWithProgettiDirigente(id);
        if (user != null) {
            UsersApiDTO dto = userMapper.toApiDTO(user);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<UsersPageApiDTO> getUsers(Integer pageNumber, Integer size, String sort, UserFilterApiDTO userFilter) {
        Page<User> usersPage = userService.findAllPaginated(pageNumber, size);

        if (usersPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<UsersApiDTO> userDtos = usersPage.getContent().stream()
                .map(userMapper::toApiDTO)
                .collect(Collectors.toList());

        UsersPageApiDTO usersPageApiDTO = new UsersPageApiDTO();
        usersPageApiDTO.setContent(userDtos);
        usersPageApiDTO.setTotalElements(usersPage.getTotalElements());
        usersPageApiDTO.setTotalPages(usersPage.getTotalPages());
        usersPageApiDTO.setSize(usersPage.getSize());
        usersPageApiDTO.setNumber(usersPage.getNumber());

        return ResponseEntity.ok(usersPageApiDTO);
    }

}