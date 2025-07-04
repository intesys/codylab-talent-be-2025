package it.intesys.codylab.service;


import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.mapper.UserMapper;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    @DisplayName("Verifico che quando chiamo un utente esistente torna dei dati consistenti")
    @Test
    void getUserById() {
        //ARRANGE
        User user = new User();
        user.setId(1L);
        user.setUsername("mario.rossi");

        UsersApiDTO usersApiDTO = new UsersApiDTO();
        usersApiDTO.setId(1L);
        usersApiDTO.setUsername("mario.rossi");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toApiDTO(user)).thenReturn(usersApiDTO);

        // ACT
        UsersApiDTO userResult = userService.getUserById(1L);

        // ASSERT
        assertNotNull(userResult);
        assertEquals(1L, userResult.getId());
        assertEquals("mario.rossi", userResult.getUsername());
        verify(userRepository, times(1)).findById(1L);
        verify(userMapper, times(1)).toApiDTO(user);
    }

    @DisplayName("Verifico che quando chiamo un utente NON esistente solleva una eccezione")
    @Test
    void getUserByIdNotFound() {
        // ARRANGE
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // ACT & ASSERT
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            userService.getUserById(1L);
        });

        assertEquals("User not found with id: 1", exception.getMessage());
        verify(userRepository, times(1)).findById(1L);
        verify(userMapper, never()).toApiDTO(any(User.class));
    }

    @DisplayName("Verifico che quando creo un utente viene salvato correttamente")
    @Test
    void createUser() {
        // ARRANGE
        User user = new User();
        user.setId(1L);
        user.setUsername("mario.rossi");

        UsersApiDTO usersApiDTO = new UsersApiDTO();
        usersApiDTO.setId(1L);
        usersApiDTO.setUsername("mario.rossi");

        when(userMapper.toEntity(usersApiDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toApiDTO(user)).thenReturn(usersApiDTO);

        // ACT
        UsersApiDTO createdUser = userService.createUser(usersApiDTO);

        // ASSERT
        assertNotNull(createdUser);
        assertEquals(1L, createdUser.getId());
        assertEquals("mario.rossi", createdUser.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @DisplayName( "Verifico che quando modifico un utente viene salvato correttamente")
    @Test
    void updateUser() {
        // ARRANGE
        User user = new User();
        user.setId(1L);
        user.setUsername("mario.rossi");

        UsersApiDTO usersApiDTO = new UsersApiDTO();
        usersApiDTO.setId(1L);
        usersApiDTO.setUsername("mario.rossi");

        when(userMapper.toEntity(usersApiDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toApiDTO(user)).thenReturn(usersApiDTO);

        // ACT
        UsersApiDTO updatedUser = userService.updateUser(1L, usersApiDTO);

        // ASSERT
        assertNotNull(updatedUser);
        assertEquals(1L, updatedUser.getId());
        assertEquals("mario.rossi", updatedUser.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @DisplayName("Verifico che quando elimino un utente viene chiamato il metodo deleteById")
    @Test
    void deleteUser() {
        // ARRANGE
        Long userId = 1L;
        // ACT
        userService.deleteUser(userId);
        // ASSERT
        verify(userRepository, times(1)).deleteById(userId);
    }
}
