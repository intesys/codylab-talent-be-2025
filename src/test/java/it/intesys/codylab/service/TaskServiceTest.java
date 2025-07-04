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

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    TaskRepository taskRepository;
    @Mock
    TaskMapper taskMapper;

    @InjectMocks
    TaskService taskService;

    @DisplayName("Verifico che quando chiamo una task esistente torna dei dati consistenti")
    @Test
    void getTaskById() {
        //ARRANGE
        Task task = new Task();
        task.setId(1L);
        task.setDurata(10);

        TasksApiDTO tasksApiDTO = new TasksApiDTO();
        tasksApiDTO.setId(1L);
        tasksApiDTO.setDurata(10);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskMapper.toApiDTO(task)).thenReturn(tasksApiDTO);
        // ACT
        TasksApiDTO taskResult = taskService.getTaskById(1L);
        // ASSERT
        assertNotNull(taskResult);
        assertEquals(1L, taskResult.getId());
        assertEquals(10, taskResult.getDurata());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskMapper, times(1)).toApiDTO(task);
    }

    @DisplayName("Verifico che quando chiamo una task NON esistente solleva una eccezione")
    @Test
    void getTaskByIdNotFound() {
        // ARRANGE
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        // ACT & ASSERT
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            taskService.getTaskById(1L);
        });

        assertEquals("Task not found with id: 1", exception.getMessage());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskMapper, never()).toApiDTO(any());
    }

    @DisplayName("Verifico che quando creo una task viene salvato correttamente")
    @Test
    void createTask() {
        // ARRANGE
        Task task = new Task();
        task.setId(1L);
        task.setDurata(10);

        TasksApiDTO tasksApiDTO = new TasksApiDTO();
        tasksApiDTO.setId(1L);
        tasksApiDTO.setDurata(10);

        when(taskMapper.toEntity(tasksApiDTO)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMapper.toApiDTO(task)).thenReturn(tasksApiDTO);

        // ACT
        TasksApiDTO createdTask = taskService.createTask(tasksApiDTO);

        // ASSERT
        assertNotNull(createdTask);
        assertEquals(1L, createdTask.getId());
        assertEquals(10, createdTask.getDurata());
        verify(taskRepository, times(1)).save(task);

    }

    @DisplayName( "Verifico che quando modifico una task viene salvato correttamente")
    @Test
    void updateTask() {
        // ARRANGE
        Task task = new Task();
        task.setId(1L);
        task.setDurata(10);

        TasksApiDTO tasksApiDTO = new TasksApiDTO();
        tasksApiDTO.setId(1L);
        tasksApiDTO.setDurata(10);

        when(taskMapper.toEntity(tasksApiDTO)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMapper.toApiDTO(task)).thenReturn(tasksApiDTO);

        // ACT
        TasksApiDTO updatedTask = taskService.updateTask(1L, tasksApiDTO);

        // ASSERT
        assertNotNull(updatedTask);
        assertEquals(1L, updatedTask.getId());
        assertEquals(10, updatedTask.getDurata());
        verify(taskRepository, times(1)).save(task);
    }

    @DisplayName("Verifico che quando elimino una task viene chiamato il metodo deleteById")
    @Test
    void deleteTask() {
        // ARRANGE
        Long taskId = 1L;
        // ACT
        taskService.deleteTask(taskId);
        // ASSERT
        verify(taskRepository, times(1)).deleteById(taskId);
    }
}
