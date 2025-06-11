package it.intesys.codylab.service;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.mapper.ProjectMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    public ProjectDTO findByCodice(String codice) {
        return projectMapper.toDTO( projectRepository.findByCodice(codice) );
    }
    public List<ProjectDTO> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toDTO)
                .toList();
    }

    public List<Project> findByDurata(Integer durata) {
        return projectRepository.findByDurata(durata);
    }

}
