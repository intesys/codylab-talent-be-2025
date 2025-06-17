package it.intesys.codylab.service;

import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).get();
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
    public void addUser(User user) {
        userRepository.save(user);
    }

}
