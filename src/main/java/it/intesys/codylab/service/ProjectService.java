package it.intesys.codylab.service;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.mapper.ProjectMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> findByDurata(Integer durata) {
        return StreamSupport.stream(projectRepository.findByDurata(durata).spliterator(), false)
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

}
