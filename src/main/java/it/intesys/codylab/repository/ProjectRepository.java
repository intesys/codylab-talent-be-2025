package it.intesys.codylab.repository;

import it.intesys.codylab.model.Project;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByCodice(String codice);

    List<Project> findAll();

    @Query("SELECT * FROM projects WHERE durata = :durata")
    List<Project> findByDurata(Integer durata);
    // Define any additional methods specific to Project if needed
    // For example, you might want to find projects by name or date range
}
