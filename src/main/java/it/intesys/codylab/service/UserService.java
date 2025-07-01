package it.intesys.codylab.service;

import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.springframework.stereotype.Service;

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
}
