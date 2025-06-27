package it.intesys.codylab.repository;

import it.intesys.codylab.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.progettiResponsabili p LEFT JOIN FETCH p.responsabile WHERE u.id = :id")
    User findUtenteWithProgettiDirigente(@Param("id") Long id);
}