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
        UsersApiDTO usersApiDTO = new UsersApiDTO();
        usersApiDTO.setNome("Test User");
        usersApiDTO.setCognome("User");
        usersApiDTO.setMail("user@gmail.com");
        usersApiDTO.setUsername("testuser");


        UsersApiDTO savedUser = userService.createUser(usersApiDTO);

        assertNotNull(savedUser);
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("testuser");
    }}