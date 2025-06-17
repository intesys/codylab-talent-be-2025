package it.intesys.codylab.service;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectDTO findByCodice(String codice) {
        Project project = projectRepository.findByCodice(codice);


        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setCodice(project.getCodice());
        projectDTO.setId(project.getId());
        projectDTO.setNome(project.getNome());
        projectDTO.setDescrizione(project.getDescrizione());
        projectDTO.setDataInizio(project.getDataInizio().toString());

        return projectDTO;
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
