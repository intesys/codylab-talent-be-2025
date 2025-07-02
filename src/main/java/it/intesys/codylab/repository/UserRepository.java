package it.intesys.codylab.repository;

import it.intesys.codylab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.progettiResponsabili p LEFT JOIN FETCH p.responsabile WHERE u.id = :id")
    User findUtenteWithProgettiDirigente(@Param("id") Long id);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.progettiResponsabili p LEFT JOIN FETCH p.responsabile WHERE u.username = :username")
    User findUtenteWithProgettiDirigenteByUsername(@Param("username") String username);
}