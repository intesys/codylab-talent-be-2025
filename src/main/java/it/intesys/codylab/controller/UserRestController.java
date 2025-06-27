package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.api.rest.UsersApi;
import it.intesys.codylab.mapper.UserMapper;
import it.intesys.codylab.model.User;
import it.intesys.codylab.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}