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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    @Mock
    ProjectRepository projectRepository;
    @Mock
    ProjectMapper projectMapper;

    @InjectMocks
    ProjectService projectService;


    @DisplayName("Verifico che quando chiamo un progetto esistente torna dei dati consistenti")
    @Test
    void getProjectById() {
        // ARRANGE
        Project project = new Project();
        project.setId(1L);
        project.setCodice("PROJ123");

        ProjectsApiDTO projectDTO = new ProjectsApiDTO();
        projectDTO.setId(1L);
        projectDTO.setCodice("PROJ123");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectMapper.toApiDTO(project)).thenReturn(projectDTO);

        // ACT
        ProjectsApiDTO projectResult = projectService.getProjectById(1L);

        // ASSERT
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
        //when(projectMapper.toDTO(any(Project.class))).thenReturn(new ProjectDTO());
        // ACT & ASSERT
        RuntimeException exception = assertThrows(RuntimeException.class, () -> projectService.getProjectById(1L));
        assertEquals("Project not found with id: 1", exception.getMessage());
    }
}
