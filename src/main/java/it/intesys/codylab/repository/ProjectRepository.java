package it.intesys.codylab.repository;

import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {


    @Query("SELECT p FROM Project p FULL JOIN p.tasks t FULL JOIN t.users u WHERE u.username = :username AND p.codice IN :projectCodes")
    List<Project> findByUsernameAndProjectCodes(@Param("username") String username, @Param("projectCodes") List<String> projectCodes);

    @Query("SELECT p FROM Project p FULL JOIN p.tasks t FULL JOIN t.users u WHERE u.username = :username")
    List<Project> findByUsername(@Param("username") String username);

    @Query("SELECT p FROM Project p WHERE p.codice IN :projectCodes")
    List<Project> findByCodice(@Param("projectCodes") List<String> projectCodes);
}