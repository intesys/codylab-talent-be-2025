package it.intesys.codylab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("testcontainer")
class ProjectRestControllerITest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void simpleSearchProjects() throws Exception {
        mockMvc.perform(get("/api/v1/projects")
                .param("search", "test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void createProject()  throws Exception{
//        String newProjectJson = "{ \"name\": \"New Project\", \"description\": \"A new project for testing\" }";
        ProjectsApiDTO projectsApiDTO = new ProjectsApiDTO();
        projectsApiDTO.setName("New Project");
        projectsApiDTO.setDescription("A new project for testing");
        projectsApiDTO.setState(ProjectsApiDTO.StateEnum.OPEN);
        projectsApiDTO.setCode("NP");
        String newProjectJson = objectMapper.writeValueAsString(projectsApiDTO);
//        String newProjectJson = projectsApiDTO.toString();


        mockMvc.perform(post("/api/v1/projects")
                .contentType("application/json")
                .content(newProjectJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("New Project"))
                .andExpect(jsonPath("$.description").value("A new project for testing"));
    }

}