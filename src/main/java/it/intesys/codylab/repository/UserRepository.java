package it.intesys.codylab.repository;

import it.intesys.codylab.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN u.progettiResponsabili p WHERE p.responsabile.id = :id")
    User findUtenteWithProgettiDirigente(Long id);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findUserWithoutProjects(@Param("id") Long id);



}
