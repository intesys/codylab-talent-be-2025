package it.intesys.codylab.service;

import it.intesys.codylab.dto.UserDTO;
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

    public List<UserDTO> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

}