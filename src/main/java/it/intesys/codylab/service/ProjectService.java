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

    public Project findById(Long id){
        return  projectRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Project not found with id: " + id)
        );
    }
}
