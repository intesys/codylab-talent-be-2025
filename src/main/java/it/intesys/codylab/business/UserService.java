package it.intesys.codylab.business;

import it.intesys.codylab.dto.User;
import it.intesys.codylab.dto.UserProfile;
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

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void updateUserProfile(Long userId, UserProfile userProfile) {
        User user = userRepository.findById(userId);
        user.setProfile(userProfile);
        userRepository.save(user);
    }
}