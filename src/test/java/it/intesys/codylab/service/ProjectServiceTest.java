package it.intesys.codylab.service;

import it.intesys.codylab.dto.ProjectDTO;
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
        project.setCode("PROJ123");
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        //when(projectMapper.toDTO(any(Project.class))).thenReturn(new ProjectDTO());
        //ACT
        Project projectResult = projectService.getProjectById(1L);
        //ASSERT
        assertNotNull(projectResult);
        assertEquals(1l, projectResult.getId());
        assertEquals("PROJ123", projectResult.getCode());
        verify(projectRepository, times(1)).findById(1L);
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