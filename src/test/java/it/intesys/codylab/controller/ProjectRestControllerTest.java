package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import it.intesys.codylab.service.ProjectService;
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

    @Autowired
    private ProjectService projectService;

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
    @Test
    void getProjectById() throws Exception {
        // Prima crea un progetto
        String projectJson = String.format(
                "{\"codice\":\"TEST002\",\"nome\":\"Test Project 2\",\"descrizione\":\"Test Description 2\",\"responsabileId\":%d}",
                savedUserId
        );

        String response = mockMvc.perform(post("/api/v1/projects")
                        .contentType("application/json")
                        .content(projectJson))
                .andReturn().getResponse().getContentAsString();

        Long projectId = Long.parseLong(response.split("\"id\":")[1].split(",")[0]);

        mockMvc.perform(get("/api/v1/projects/" + projectId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(projectId))
                .andExpect(jsonPath("$.nome").value("Test Project 2"))
                .andExpect(jsonPath("$.responsabileId").value(savedUserId));
    }
    @Test
    void updateProject() throws Exception {
        // Prima crea un progetto
        String projectJson = String.format(
                "{\"codice\":\"TEST003\",\"nome\":\"Test Project 3\",\"descrizione\":\"Test Description 3\",\"responsabileId\":%d}",
                savedUserId
        );

        String response = mockMvc.perform(post("/api/v1/projects")
                        .contentType("application/json")
                        .content(projectJson))
                .andReturn().getResponse().getContentAsString();

        Long projectId = Long.parseLong(response.split("\"id\":")[1].split(",")[0]);

        // Aggiorna il progetto
        String updatedProjectJson = String.format(
                "{\"codice\":\"TEST003\",\"nome\":\"Updated Project 3\",\"descrizione\":\"Updated Description 3\",\"responsabileId\":%d}",
                savedUserId
        );

        mockMvc.perform(put("/api/v1/projects/" + projectId)
                        .contentType("application/json")
                        .content(updatedProjectJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(projectId))
                .andExpect(jsonPath("$.nome").value("Updated Project 3"))
                .andExpect(jsonPath("$.responsabileId").value(savedUserId));
    }
    @Test
    void deleteProject() throws Exception {
        // Prima crea un progetto
        String projectJson = String.format(
                "{\"codice\":\"TEST004\",\"nome\":\"Test Project 4\",\"descrizione\":\"Test Description 4\",\"responsabileId\":%d}",
                savedUserId
        );

        String response = mockMvc.perform(post("/api/v1/projects")
                        .contentType("application/json")
                        .content(projectJson))
                .andReturn().getResponse().getContentAsString();

        Long projectId = Long.parseLong(response.split("\"id\":")[1].split(",")[0]);

        // Elimina il progetto
        mockMvc.perform(delete("/api/v1/projects/" + projectId))
                .andDo(print())
                .andExpect(status().isNoContent());

        // Verifica che il progetto non esista più
        mockMvc.perform(get("/api/v1/projects/" + projectId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    void getProjectsWithResponsabile() throws Exception {
        // 1. Crea un responsabile
        User responsabile = new User();
        responsabile = userRepository.save(responsabile);

        // 2. Crea un progetto associato
        ProjectsApiDTO projectDTO = new ProjectsApiDTO();
        projectDTO.setCodice("TEST001");
        projectDTO.setNome("Progetto Test");
        projectDTO.setResponsabileId(responsabile.getId());
        projectService.createProject(projectDTO);

        // 3. Chiama l'endpoint specifico
        mockMvc.perform(get("/api/v1/projects-with-responsabile"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].codice").value("TEST001"))
                .andExpect(jsonPath("$[0].responsabile.id").value(responsabile.getId()));
}}