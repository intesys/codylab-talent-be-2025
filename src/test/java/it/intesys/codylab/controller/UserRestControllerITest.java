package it.intesys.codylab.controller;

import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class UserRestControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testGetUserById() throws Exception {
        // 1. Crea e salva un utente di test
        User user = new User();
        user.setUsername("testuser");
        user.setNome("Mario");
        user.setCognome("Rossi");
        user.setMail("m.rossi@example.com");
        user.setProfilo("DEV");
        user.setOrarioGiornaliero(8.0);
        User savedUser = userRepository.save(user);

        // 2. Esegui la richiesta GET
        mockMvc.perform(get("/api/v1/users/{id}", savedUser.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(savedUser.getId()))
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.nome").value("Mario"))
                .andExpect(jsonPath("$.cognome").value("Rossi"));
    }
}