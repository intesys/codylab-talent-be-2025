package it.intesys.codylab.service;

import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.mapper.TaskMapper;
import it.intesys.codylab.model.Task;
import it.intesys.codylab.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock
    TaskRepository taskRepository;
    @Mock
    TaskMapper taskMapper;

    @InjectMocks
    TaskService taskService;

    @DisplayName("Verifico che quando chiamo un task esistente torna dei dati consistenti")
    @Test
    void getTaskById() {
        // Arrange
        Long taskId = 1L;
        Task mockTask = new Task();
        mockTask.setId(taskId);
        TasksApiDTO mockDto = new TasksApiDTO();
        mockDto.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.of(mockTask));
        when(taskMapper.toApiDTO(mockTask)).thenReturn(mockDto);

        // Act
        TasksApiDTO result = taskService.getTaskById(taskId);

        // Assert
        assertNotNull(result);
        assertEquals(taskId, result.getId());
        verify(taskRepository).findById(taskId);
        verify(taskMapper).toApiDTO(mockTask);
    }
    @DisplayName("Verifico che quando chiamo un task NON esistente ritorna null")
    @Test
    void getTaskByIdNotFound() {
        // Arrange
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.empty());

        // Act
        TasksApiDTO result = taskService.getTaskById(taskId);

        // Assert
        assertNull(result);
        verify(taskRepository).findById(taskId);
    }

    @DisplayName("Verifico che quando creo un task, viene salvato correttamente")
    @Test
    void createTask() {
        // Arrange
        TasksApiDTO taskDto = new TasksApiDTO();
        taskDto.setId(1L);
        Task taskEntity = new Task();
        taskEntity.setId(1L);

        when(taskMapper.toEntity(taskDto)).thenReturn(taskEntity);
        when(taskRepository.save(taskEntity)).thenReturn(taskEntity);
        when(taskMapper.toApiDTO(taskEntity)).thenReturn(taskDto);

        // Act
        TasksApiDTO result = taskService.createTask(taskDto);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(taskMapper).toEntity(taskDto);
        verify(taskRepository).save(taskEntity);
        verify(taskMapper).toApiDTO(taskEntity);
    }
    @DisplayName("Verifico che quando creo un task con ID nullo, solleva una eccezione")
    @Test
    void createTaskWithNullId() {
        // Arrange
        TasksApiDTO taskDto = new TasksApiDTO();
        taskDto.setId(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> taskService.createTask(taskDto)
        );

        assertEquals("ID del task non può essere nullo.", exception.getMessage());

        // Modificato: verifica che NON ci siano interazioni con i mock
        verifyNoInteractions(taskMapper);
        verifyNoInteractions(taskRepository);
    }

    @DisplayName("Verifico che quando aggiorno un task, viene aggiornato correttamente")
    @Test
    void updateTask() {
        // Arrange
        Long taskId = 1L;
        TasksApiDTO taskDto = new TasksApiDTO();
        taskDto.setId(taskId);
        Task taskEntity = new Task();
        taskEntity.setId(taskId);

        when(taskMapper.toEntity(taskDto)).thenReturn(taskEntity);
        when(taskRepository.save(taskEntity)).thenReturn(taskEntity);
        when(taskMapper.toApiDTO(taskEntity)).thenReturn(taskDto);

        // Act
        TasksApiDTO result = taskService.updateTask(taskId, taskDto);

        // Assert
        assertNotNull(result);
        assertEquals(taskId, result.getId());
        verify(taskMapper).toEntity(taskDto);
        verify(taskRepository).save(taskEntity);
        verify(taskMapper).toApiDTO(taskEntity);
    }
    @DisplayName("Verifico che quando aggiorno un task con ID nullo, solleva una eccezione")
    @Test
    void updateTaskWithNullId() {
        // Arrange
        Long taskId = null;
        TasksApiDTO taskDto = new TasksApiDTO();
        taskDto.setId(taskId);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> taskService.updateTask(taskId, taskDto)
        );

        assertEquals("ID del task non può essere nullo.", exception.getMessage());

        // Modificato: verifica che NON ci siano interazioni con i mock
        verifyNoInteractions(taskMapper);
        verifyNoInteractions(taskRepository);
    }

    @DisplayName("Verifico che quando elimino un task, viene eliminato correttamente")
    @Test
    void deleteTask() {
        // Arrange
        Long taskId = 1L;

        // Act
        taskService.deleteTask(taskId);

        // Assert
        verify(taskRepository, times(1)).deleteById(taskId);
    }
}