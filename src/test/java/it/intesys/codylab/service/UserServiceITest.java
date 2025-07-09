package it.intesys.codylab.service;


import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceITest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    private User User;

    @Test
    void testUserLifeCycle() {
        // 1. Preparazione DTO
        UsersApiDTO usersApiDTO = new UsersApiDTO();
        usersApiDTO.setNome("Test User");
        usersApiDTO.setCognome("User");
        usersApiDTO.setMail("user@gmail.com");
        usersApiDTO.setUsername("testuser");

        // 2. Converti DTO in Entity
        User user = new User();
        user.setNome(usersApiDTO.getNome());
        user.setCognome(usersApiDTO.getCognome());
        user.setMail(usersApiDTO.getMail());
        user.setUsername(usersApiDTO.getUsername());

        // 3. Chiamata al service con l'Entity
        User savedUser = userService.createUser(user);

        // 4. Verifiche
        assertNotNull(savedUser);
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("testuser");
    }}