package it.intesys.codylab.repository;

import it.intesys.codylab.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {


    @Query("SELECT p FROM Project p LEFT JOIN p.tasks t LEFT JOIN t.users u WHERE u.username = :username AND p.codice IN :projectCodes")
    Page<Project> findByUsernameAndProjectCodes(@Param("username") String username, @Param("projectCodes") List<String> projectCodes, Pageable pageable);

    @Query("SELECT p FROM Project p LEFT JOIN p.tasks t LEFT JOIN t.users u WHERE u.username = :username")
    Page<Project> findByUsername(@Param("username") String username, Pageable pageable);

//    @Query("SELECT p FROM Project p WHERE p.codice IN :projectCodes")
    Page<Project> findByCodiceIn(@Param("projectCodes") List<String> projectCodes, Pageable pageable);
}