package it.intesys.codylab.web;

import it.intesys.codylab.service.ProjectService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@WebMvcTest(ProjectController.class)
@ActiveProfiles("test")
public class ProjectControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;

    @MockitoBean
    private ProjectService projectService;

    @Test
    void testViewName() {
        Assertions.assertThat(mockMvcTester.get().uri(  "/mvc/projects").accept(MediaType.TEXT_HTML))
                .hasStatus2xxSuccessful()
                .hasViewName("projects");
    }
}
