package it.intesys.codylab.controller;

import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProjectRestControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private Long savedUserId;

    @BeforeEach
    void setup() {
        // Crea e salva un utente prima di ogni test
        User user = new User();
        user.setNome("Test User");
        User savedUser = userRepository.save(user);
        savedUserId = savedUser.getId();
    }

    @Test
    void createProject() throws Exception {
        String projectJson = String.format(
                "{\"codice\":\"TEST001\",\"nome\":\"Test Project\",\"descrizione\":\"Test Description\",\"responsabileId\":%d}",
                savedUserId
        );

        mockMvc.perform(post("/api/v1/projects")
                        .contentType("application/json")
                        .content(projectJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Test Project"))
                .andExpect(jsonPath("$.responsabileId").value(savedUserId));
    }

    @Test
    void searchProjects() throws Exception {
        // Prima crea un progetto
        createProject();

        mockMvc.perform(get("/api/v1/projects"))
                .andDo(print())
                .andExpect(status().isOk())
                // Verifica la struttura paginata
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].id").exists())
                .andExpect(jsonPath("$.content[0].codice").value("TEST001"))
                .andExpect(jsonPath("$.totalElements").value(1));
    }
}