//package it.intesys.codylab.controller;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//class ProjectRestControllerITest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void searchProjects() throws Exception {
//        mockMvc.perform(get("/api/v1/projects"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.*", hasSize(1))) // Assuming at least one project exists
//                .andExpect(jsonPath("$.[0].id").isNumber());
//    }
//
//    @Test
//    void createProject()  throws Exception{
//        mockMvc.perform(post("/api/v1/projects")
//                .contentType("application/json")
//                .content("{\"code\":\"TEST001\",\"name\":\"Test Project 001\",\"description\":\"Test Description\"}"))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Example Project"));
//    }
//}