package it.intesys.codylab.repository;

import it.intesys.codylab.model.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByCodice(String codice);

    //@Query("SELECT * FROM projects WHERE durata = :durata")
    List<Project> findByDurata(Integer durata);

}
