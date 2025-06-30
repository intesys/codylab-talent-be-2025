package it.intesys.codylab.service;

import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.mapper.UserMapper;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UsersApiDTO> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userMapper::toApiDTO)
                .collect(Collectors.toList());
    }

    public UsersApiDTO getUserById(Long id) {
        return userRepository.findUserWithoutProjects(id)
                .map(user -> {
                    user.setProgettiResponsabili(null);
                    return userMapper.toApiDTO(user);
                })
                .orElse(null);
    }


    public UsersApiDTO createUser(UsersApiDTO userDto) {
        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.toApiDTO(savedUser);
    }

    public UsersApiDTO updateUser(Long id, UsersApiDTO userDto) {
        User user = userMapper.toEntity(userDto);
        user.setId(id);
        User updatedUser = userRepository.save(user);
        return userMapper.toApiDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findUtenteWithProgettiDirigente(Long id) {
        return userRepository.findUtenteWithProgettiDirigente(id);
    }

    public List<UsersApiDTO> getUsers(
            List<Long> ids,
            Long taskId,
            Integer pageNumber,
            Integer size,
            String sort) {

        List<User> users = (List<User>) userRepository.findAll();



        if (ids != null && !ids.isEmpty()) {
            users = users.stream()
                    .filter(user -> ids.contains(user.getId()))
                    .toList();
        }

        if (taskId != null) {
            users = users.stream()
                    .filter(user -> taskId.equals(user.getTaskId()))
                    .toList();
        }

        return users.stream()
                .map(user -> {
                    user.setProgettiResponsabili(null);
                    return userMapper.toApiDTO(user);
                })
                .toList();
    }

}
