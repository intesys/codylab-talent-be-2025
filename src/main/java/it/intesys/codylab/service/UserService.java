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
    private UserRepository userRepository;
    private UserMapper userMapper;

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
        return userRepository.findById(id)
                .map(userMapper::toApiDTO)
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
}
