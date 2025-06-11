package it.intesys.codylab.repository;

import it.intesys.codylab.dto.User;
import it.intesys.codylab.dto.UserProfile;
import it.intesys.codylab.dto.WorkingHours;

import java.util.List;

public interface UserRepository {

    User findById(Long id);
    List<User> findAll();
    void save(User user);
    void deleteById(Long id);
    void updateUserProfile(Long userId, UserProfile userProfile);
//    void updateUserWorkingHours(Long userId, WorkingHours workingHours);
}
