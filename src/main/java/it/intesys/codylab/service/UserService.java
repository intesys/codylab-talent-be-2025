package it.intesys.codylab.service;

import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserNameById(Long id) {
        return userRepository.findById(id);
    }
}
