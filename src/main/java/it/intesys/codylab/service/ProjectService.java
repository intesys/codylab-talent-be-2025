package it.intesys.codylab.service;

import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
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

    public List<ProjectsApiDTO> getProjects() {
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .map(projectMapper::toApiDTO)
                .collect(Collectors.toList());
    }

    public ProjectsApiDTO getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(projectMapper::toApiDTO)
                .orElse(null);
    }

    public ProjectsApiDTO createProject(ProjectsApiDTO projectsApiDTO) {
        Project project = projectMapper.toEntity(projectsApiDTO);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toApiDTO(savedProject);
    }

    public ProjectsApiDTO updateProject(Long id, ProjectsApiDTO projectsApiDTO) {
        Project project = projectMapper.toEntity(projectsApiDTO);
        project.setId(id);
        Project updatedProject = projectRepository.save(project);
        return projectMapper.toApiDTO(updatedProject);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public List<ProjectsApiDTO> getProjectByUserIdOrProjectIds(ProjectFilterApiDTO filter) {
        List<Project> projects = projectRepository.findByUserIdOrProjectIds(filter.getUserId(), filter.getIds());
        return projects.stream()
                .map(projectMapper::toApiDTO)
                .collect(Collectors.toList());
    }
    public List<ProjectsApiDTO> getProjectByCodice(String codice) {
        List<Project> projects = projectRepository.findByCodice(codice);
        return projects.stream()
                .map(projectMapper::toApiDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectsApiDTO> getProjectByCodiceAndUsername(String codice, String username) {
        List<Project> projects = projectRepository.findByCodiceAndUsername(codice, username);
        return projects.stream()
                .map(projectMapper::toApiDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectsApiDTO> getProjectByUsername(String username) {
        List<Project> projects = projectRepository.findByUsername(username);
        return projects.stream()
                .map(projectMapper::toApiDTO)
                .collect(Collectors.toList());
    }


}
