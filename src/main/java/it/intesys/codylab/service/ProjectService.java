package it.intesys.codylab.service;

import it.intesys.codylab.model.Project;
import it.intesys.codylab.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService{
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public Project findByCodice(String codice){
        return projectRepository.findByCodice(codice);
    }
}
