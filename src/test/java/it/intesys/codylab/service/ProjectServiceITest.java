package it.intesys.codylab.service;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ProjectServiceITest {
    @Autowired
    ProjectService projectService;

    @Autowired
    UserRepository userRepository;

    @Test
    void testProjectLifeCycle() {
        // 1. Crea e salva un User
        User responsabile = new User();
        User savedUser = userRepository.save(responsabile); // L'ID viene generato automaticamente

        // 2. Crea il DTO del progetto
        ProjectsApiDTO projectsApiDTO = new ProjectsApiDTO();
        projectsApiDTO.setNome("Test Project");
        projectsApiDTO.setDescrizione("This is a test project");
        projectsApiDTO.setCodice("TEST123");
        projectsApiDTO.setResponsabileId(savedUser.getId()); // Usa l'ID dell'utente salvato

        // 3. Crea il progetto
        ProjectsApiDTO createdProject = projectService.createProject(projectsApiDTO);

        // 4. Verifiche
        assertNotNull(createdProject);
        assertThat(createdProject.getId()).isNotNull();
        assertThat(createdProject.getCodice()).isEqualTo("TEST123");
        assertThat(createdProject.getResponsabileId()).isEqualTo(savedUser.getId());

        // 5. Recupera il progetto
        ProjectsApiDTO retrievedProject = projectService.getProjectById(createdProject.getId());

        // 6. Verifiche sul progetto recuperato
        assertNotNull(retrievedProject);
        assertThat(retrievedProject.getId()).isEqualTo(createdProject.getId());
        assertThat(retrievedProject.getCodice()).isEqualTo("TEST123");
        assertThat(retrievedProject.getResponsabileId()).isEqualTo(savedUser.getId());
    }

}