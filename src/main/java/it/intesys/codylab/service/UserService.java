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

//  findById
    public User findById(Long id){
        return userRepository.findById(id);
    }

//  findAll
    public List<User> findAll(){
        return userRepository.findAll();
    }

//  addUser
    public void save(User user){
        userRepository.save(user);
    }

//  updateUser
    public void update(Long id, User user){
        userRepository.update(id, user);
    }

//  updateUserProfile
    public void updateUserProfile(Long id, User user){
        userRepository.updateUserProfile(id, user);
    }

//  deleteUser
    public void delete(Long id){
        userRepository.delete(id);
    }
}