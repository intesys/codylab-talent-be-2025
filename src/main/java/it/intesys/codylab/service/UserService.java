package it.intesys.codylab.service;

import it.intesys.codylab.api.model.UserFilterApiDTO;
import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.mapper.UserMapper;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Page<UsersApiDTO> getUsers(UserFilterApiDTO filter, int pageNumber, int size, String sort) {
        if (sort == null || sort.isBlank()) {
            sort = "id";
        }
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sort));

        Page<User> userPage;

        if (!filter.getIds().isEmpty() && filter.getTaskId() != null) {
            userPage = findByIdsAndTasksId(filter.getIds(), filter.getTaskId(), pageable);
        } else if (filter.getTaskId() != null && filter.getIds().isEmpty()) {
            userPage = findByTasksId(filter.getTaskId(), pageable);
        } else if (filter.getTaskId() == null && !filter.getIds().isEmpty()) {
            userPage = findByIds(filter.getIds(), pageable);
        } else {
            userPage = userRepository.findAll(pageable);
        }

        return userPage.map(userMapper::toApiDTO);
    }

    public UsersApiDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setProjects(null);
                    return userMapper.toApiDTO(user);
                })
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
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

    public User findUserWithProgettiResponsabile(Long id) {
        return userRepository.findUserWithProgettiResponsabile(id);
    }


    private Page<User> findByIdsAndTasksId(List<Long> ids, Long taskId, Pageable pageable) {
        return userRepository.findByIdsAndTasksId(ids, taskId, pageable);
    }

    private Page<User> findByTasksId(Long taskId, Pageable pageable) {
        return userRepository.findByTasksId(taskId, pageable);
    }

    private Page<User> findByIds(List<Long> ids, Pageable pageable) {
        return userRepository.findByIds(ids, pageable);
    }
}
