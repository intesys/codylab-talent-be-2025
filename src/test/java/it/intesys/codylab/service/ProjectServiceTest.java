package it.intesys.codylab.service;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.mapper.ProjectMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.repository.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    ProjectRepository projectRepository;

    @Mock
    ProjectMapper projectMapper;

    @InjectMocks
    ProjectService projectService;

    @DisplayName("Verifico che quando chiamo un progetto esistente torna dei dati consistenti")
    @Test
    void getProjectById() {
        //ARRANGE
        Project project = new Project();
        project.setId(1L);
        project.setCodice("PROJ123");

        ProjectsApiDTO expectedDTO = new ProjectsApiDTO();
        expectedDTO.setId(1L);
        expectedDTO.setCodice("PROJ123");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectMapper.toApiDTO(project)).thenReturn(expectedDTO);

        //ACT
        ProjectsApiDTO projectResult = projectService.getProjectById(1L);

        //ASSERT
        assertNotNull(projectResult);
        assertEquals(1L, projectResult.getId());
        assertEquals("PROJ123", projectResult.getCodice());
        verify(projectRepository, times(1)).findById(1L);
        verify(projectMapper, times(1)).toApiDTO(project);
    }

    @DisplayName("Verifico che quando chiamo un progetto NON esistente solleva una eccezione")
    @Test
    void getProjectByIdNotFound() {
        //ARRANGE
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        // ACT & ASSERT
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            projectService.getProjectById(1L);
        });

        assertEquals("Progetto con ID 1 non trovato.", exception.getMessage());
        verify(projectRepository, times(1)).findById(1L);
        verify(projectMapper, never()).toApiDTO(any(Project.class));
    }

    @DisplayName("Verifico che quando creo un progetto viene salvato correttamente")
    @Test
    void createProject() {
        // ARRANGE
        Project project = new Project();
        project.setId(1L);
        project.setCodice("PROJ123");

        ProjectsApiDTO projectDTO = new ProjectsApiDTO();
        projectDTO.setId(1L);
        projectDTO.setCodice("PROJ123");

        when(projectMapper.toEntity(projectDTO)).thenReturn(project);
        when(projectRepository.save(project)).thenReturn(project);
        when(projectMapper.toApiDTO(project)).thenReturn(projectDTO);

        // ACT
        ProjectsApiDTO createdProject = projectService.createProject(projectDTO);

        // ASSERT
        assertNotNull(createdProject);
        assertEquals(1L, createdProject.getId());
        assertEquals("PROJ123", createdProject.getCodice());
        verify(projectRepository, times(1)).save(project);
    }
    @DisplayName("Verifico che quando creo un progetto con codice nullo solleva una eccezione")
    @Test
    void createProjectWithNullCodice() {
        // ARRANGE
        ProjectsApiDTO projectDTO = new ProjectsApiDTO();
        projectDTO.setId(1L);
        projectDTO.setCodice(null);

        // ACT & ASSERT
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            projectService.createProject(projectDTO);
        });

        assertEquals("Il codice del progetto non può essere nullo", exception.getMessage());
        verify(projectRepository, never()).save(any(Project.class));
    }
//
//    @Test
//    void updateProject() {
//    }
//
//    @Test
//    void deleteProject() {
//    }
//
//    @Test
//    void getProjectByUserIdOrProjectIds() {
//    }
//
//    @Test
//    void getProjectByCodice() {
//    }
//
//    @Test
//    void getProjectByCodiceAndUsername() {
//    }
//
//    @Test
//    void getProjectByUsername() {
//    }
//
//    @Test
//    void getAllProjectsWithResponsabile() {
//    }
//
//    @Test
//    void findAllPaginated() {
//    }
}