package it.intesys.codylab.service;

import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class UserService {
        @Autowired
        private UserRepository repository;

        public List<User> findAll() {
            return (List<User>) repository.findAll();
        }

        public User findById(Long id) {
            return repository.findById(id).orElse(null);
        }

        public User save(User user) {
            return repository.save(user);
        }

        public void delete(Long id) {
            repository.deleteById(id);
        }
    }
