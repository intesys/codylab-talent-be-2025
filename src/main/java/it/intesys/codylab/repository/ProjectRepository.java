package it.intesys.codylab.repository;

import it.intesys.codylab.model.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByCodice(String codice);

    @Query("SELECT p FROM Project p FULL JOIN p.tasks t FULL JOIN t.users u WHERE u.id = :userId OR p.id IN :projectIds")
    List<Project> findByUserIdOrProjectIds(@Param("userId") Long userId, @Param("projectIds") List<Long> projectIds);

}