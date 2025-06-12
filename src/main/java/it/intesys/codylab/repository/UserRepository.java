package it.intesys.codylab.repository;
import it.intesys.codylab.dto.User;

import java.util.List;

public interface UserRepository {

    User findById(Long id);
    List<User> findAll();
    User save(User user);
    void delete(User user);
    void deleteById(Long id);

}
