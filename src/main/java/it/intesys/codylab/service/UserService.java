package it.intesys.codylab.service;

import it.intesys.codylab.api.model.UserFilterApiDTO;
import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    public User createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        // Verifica se lo username esiste già
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        return userRepository.save(user);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public User updateUser(User user) {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("Dati utente non validi");
        }

        // 2. Controlla se l'utente esiste
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new NoSuchElementException("Utente non trovato"));

        // 3. Controlla se lo username esiste per altri utenti
        if (!existingUser.getUsername().equals(user.getUsername()) &&
                userRepository.existsByUsernameAndIdNot(user.getUsername(), user.getId())) {
            throw new IllegalArgumentException("Username già in uso");
        }

        return userRepository.save(user);
    }
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }


    public Page<User> findAllPaginated(int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return userRepository.findAll(pageable);
    }



}
