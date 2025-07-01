package it.intesys.codylab.service;

import it.intesys.codylab.api.model.UserFilterApiDTO;
import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService( UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User findUtenteWithProgettiDirigente(Long id) {
        return userRepository.findUtenteWithProgettiDirigente(id);
    }

    public User findUtenteWithProgettiDirigenteByUsername(String username) {
        return userRepository.findUtenteWithProgettiDirigenteByUsername(username);
    }
    public User findByUsername(String username) {
        return userRepository.findUtenteWithProgettiDirigenteByUsername(username);
    }
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }



}
