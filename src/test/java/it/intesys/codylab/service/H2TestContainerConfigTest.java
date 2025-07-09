package it.intesys.codylab.service;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
public class H2TestContainerConfigTest {

    // 1. Definisci il container H2
    @Container
    public static GenericContainer<?> h2Container = new GenericContainer<>(
            DockerImageName.parse("oscarfonts/h2:latest"))
            .withExposedPorts(1521, 81)
            .withEnv("H2_OPTIONS", "-ifNotExists");

    // 2. Configura dinamicamente l'URL del datasource
    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () ->
                "jdbc:h2:tcp://" + h2Container.getHost() + ":" +
                        h2Container.getMappedPort(1521) + "/~/testdb");
    }

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveProjectWithValidResponsabile() {
        // 1. Crea e salva prima l'utente responsabile
        User responsabile = new User();
        responsabile.setNome("Mario Rossi");
        responsabile.setCognome("Verdi");
        responsabile.setMail("m.rossi@example.com");
        User savedResponsabile = userRepository.save(responsabile);

        // 2. Crea il progetto con un responsabile valido
        ProjectsApiDTO projectDTO = new ProjectsApiDTO();
        projectDTO.setCodice("TEST_" + System.currentTimeMillis());
        projectDTO.setNome("Progetto Test");
        projectDTO.setDescrizione("Descrizione test");
        projectDTO.setDataInizio(LocalDate.now());
        projectDTO.setDurata(30);
        projectDTO.setResponsabileId(savedResponsabile.getId()); // ID reale

        // 3. Salva il progetto
        ProjectsApiDTO savedProject = projectService.createProject(projectDTO);

        // 4. Verifiche
        assertNotNull(savedProject.getId());
        assertEquals(savedResponsabile.getId(), savedProject.getResponsabileId());
    }
}