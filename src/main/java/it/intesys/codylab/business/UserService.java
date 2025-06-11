package it.intesys.codylab.business;

import it.intesys.codylab.dto.User;
import it.intesys.codylab.dto.UserProfile;
import it.intesys.codylab.dto.WorkingHours;
import it.intesys.codylab.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(long id) {
        return userRepository.findById(id);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }


    public void updateUserProfile(Long userId, UserProfile userProfile) {
        userRepository.updateUserProfile(userId, userProfile);
    }

    public void updateUser(Long id, User user) {
        userRepository.updateUser(id, user);
    }

//    public void updateUserWorkingHours(Long userId, WorkingHours workingHours) {
//        userRepository.updateUserWorkingHours(userId, workingHours);
//    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}