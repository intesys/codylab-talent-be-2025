package it.intesys.codylab.service;

import it.intesys.codylab.model.Project;
import it.intesys.codylab.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project findByCodice(String codice) {
        return projectRepository.findByCodice(codice);
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Project not found with id: " + id)
        );
    }
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }
}
