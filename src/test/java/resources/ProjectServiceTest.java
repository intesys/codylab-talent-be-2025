package resources;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.mapper.ProjectMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.repository.ProjectRepository;
import it.intesys.codylab.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    private ProjectRepository projectRepository;
    private ProjectMapper projectMapper;
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        projectRepository = mock(ProjectRepository.class);
        projectMapper = mock(ProjectMapper.class);
        projectService = new ProjectService(projectRepository, projectMapper);
    }

    @Test
    void getProjectById_shouldReturnProjectApiDTO() {
        // dati di partenza
        Long id = 1L;
        Project project = new Project();
        project.setId(id);

        ProjectsApiDTO dto = new ProjectsApiDTO();
        dto.setId(id);

        // definiamo come si comportano i mock
        when(projectRepository.findById(id)).thenReturn(Optional.of(project));
        when(projectMapper.toApiDTO(project)).thenReturn(dto);

        // chiamiamo il metodo da testare
        ProjectsApiDTO result = projectService.getProjectById(id);

        // verifichiamo
        assertEquals(id, result.getId(), "L'ID deve essere quello atteso");
        verify(projectRepository).findById(id);
        verify(projectMapper).toApiDTO(project);
    }

    @Test
    void getProjectById_shouldThrowExceptionWhenProjectNotFound() {
        // dati di partenza
        Long id = 6666L;

        // definiamo come si comportano i mock
        when(projectRepository.findById(id)).thenReturn(Optional.empty());

        // chiamiamo il metodo da testare e verifichiamo che l'eccezione venga lanciata
        try {
            projectService.getProjectById(id);
        } catch (RuntimeException e) {
            assertEquals("Progetto non trovato", e.getMessage(), "Il messaggio di errore deve essere quello atteso");

        }

        verify(projectRepository).findById(id);
    }
}


