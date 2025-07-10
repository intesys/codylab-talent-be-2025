package it.intesys.codylab.web;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.service.ProjectService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.List;

//@SpringBootTest
@WebMvcTest(ProjectController.class)
@ActiveProfiles("test")
public class ProjectControllerTest {

    @Autowired
    private MockMvcTester mvc;

    @MockitoBean
    private ProjectService projectService;

    @Test
    void testViewName() {

        Assertions.assertThat(mvc.get().uri(  "/mvc/projects").accept(MediaType.TEXT_HTML))
                .hasStatus2xxSuccessful()
                .hasViewName("projects");
    }

    @Test
    void testProjectsPageContainsList() throws Exception {

        // Mock the service to return a list of projects
        ProjectDTO projectDTO1 = new ProjectDTO();
        projectDTO1.setCodice("A");

        ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setCodice("B");

        List<ProjectDTO> mockProjects = List.of(
                projectDTO1, projectDTO2
        );

        Mockito.when(projectService.findAll()).thenReturn(mockProjects);

        mvc.get().uri("/mvc/projects")
                .assertThat()
                .hasStatus2xxSuccessful()
                .model().containsEntry(
                    "projects", mockProjects
                );

        // Perform the request and verify the response
        System.out.println(
            mvc.perform(mvc.get().uri("/mvc/projects").accept(MediaType.TEXT_HTML))
                    .getResponse().getContentAsString()
        );

    }

}
