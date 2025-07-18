package it.intesys.codylab.repository;

import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.id = :id")
    List<Project> findUserWithoutProjects(@Param("id") Long id);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String manager);


}
