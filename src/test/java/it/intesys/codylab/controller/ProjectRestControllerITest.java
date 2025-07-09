package it.intesys.codylab.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ProjectRestControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getProjects() throws Exception {
        mockMvc.perform(get("/api/v1/projects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)));
    }


    @Test
    void createProject() throws Exception {
        String newProject = """
        {
          "codice": "PRJ001",
          "nome": "Progetto Alpha",
          "descrizione": "Descrizione del progetto Alpha",
          "responsabile": 1
        }
        """;

        mockMvc.perform(post("/api/v1/projects")
                        .contentType("application/json")
                        .content(newProject))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content[0].codice").value("PRJ001"))
                .andExpect(jsonPath("$.content[0].nome").value("Progetto Alpha"))
                .andExpect(jsonPath("$..content[0].responsabile").value(1));
    }
}
