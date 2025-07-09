package it.intesys.codylab.service;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ProjectServiceITest {

    @Autowired
    ProjectService projectService;
    @Autowired
    private UserRepository userRepository;

    @DisplayName("Ciclo di vita del progetto")
    @Test
    @Transactional
    void testLifeCycle() {
        User user = new User();
        user.setNome("John");
        user.setCognome("Doe");
        user.setUsername("johndoe");

        user = userRepository.save(user);

        ProjectsApiDTO projectsApiDTO = new ProjectsApiDTO();
        projectsApiDTO.setCodice("PRJ001");
        projectsApiDTO.setNome("Test Project");
        projectsApiDTO.setDescrizione("This is a test project");
        projectsApiDTO.setResponsabile(user.getId().toString());

        projectsApiDTO = projectService.createProject(projectsApiDTO);

        assertNotNull(projectsApiDTO);
        assertThat(projectsApiDTO.getId()).isNotNull();
        assertThat(projectsApiDTO.getCodice()).isEqualTo("PRJ001");

        ProjectsApiDTO project = projectService.getProjectById(projectsApiDTO.getId());
        assertNotNull(project);
        assertThat(project.getCodice()).isEqualTo("PRJ001");
    }
}
