package it.intesys.codylab.service;

import it.intesys.codylab.api.model.UserFilterApiDTO;
import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.api.rest.UsersApi;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserService {


    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserNameById(Long id) {
        return userRepository.findById(id).get();
    }
    public List<User> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .toList();

    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


}