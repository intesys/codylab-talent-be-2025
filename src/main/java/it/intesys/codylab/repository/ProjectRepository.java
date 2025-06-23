package it.intesys.codylab.repository;

import it.intesys.codylab.model.Project;
import org.springframework.data.repository.ListCrudRepository;

public interface ProjectRepository extends ListCrudRepository<Project, Long> {

    public Project findByCodice(String codice);

    boolean existsByCodice(String codice);

    // Additional methods specific to the Project can be defined here if needed
}
