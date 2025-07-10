package it.intesys.codylab.repository;

import it.intesys.codylab.model.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByCodice(String codice);

}