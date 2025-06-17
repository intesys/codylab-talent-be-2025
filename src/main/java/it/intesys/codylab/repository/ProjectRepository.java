package it.intesys.codylab.repository;

import it.intesys.codylab.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    public Project findByCodice(String codice);
}
