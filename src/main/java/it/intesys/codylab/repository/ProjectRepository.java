package it.intesys.codylab.repository;

import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {


    @Query("SELECT p FROM Project p FULL JOIN p.tasks t FULL JOIN t.users u WHERE u.id = :userId OR p.id IN :projectIds")
    List<Project> findByUserIdOrProjectIds(@Param("userId") Long userId, @Param("projectIds") List<Long> projectIds);

    @Query("SELECT p FROM Project p WHERE (:codice IS NULL OR p.codice = :codice)")
    List<Project> findByCodice(@Param("codice") String codice);

    @Query("SELECT DISTINCT p FROM Project p JOIN p.tasks t JOIN t.users u WHERE " +
            "(:codice IS NULL OR p.codice = :codice) AND " +
            "(:username IS NULL OR u.username = :username)")
    List<Project> findByCodiceAndUsername(@Param("codice") String codice, @Param("username") String username);

    @Query("SELECT DISTINCT p FROM Project p JOIN p.tasks t JOIN t.users u WHERE u.username = :username")
    List<Project> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.progettiResponsabili WHERE u.id = :id")
    User findByIdWithProjects(@Param("id") Long id);

    @Query("SELECT p FROM Project p LEFT JOIN FETCH p.responsabile")
    List<Project> findAllWithResponsabile();
}
