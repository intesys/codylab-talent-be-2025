package it.intesys.codylab.service;

import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.mapper.ProjectMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    public List<ProjectsApiDTO> getProjectByProjectFilter(ProjectFilterApiDTO filter) {

        if(!CollectionUtils.isEmpty(filter.getProjectCodes()) && filter.getUsername() != null) {
            return getProjectByUsernameAndProjectCodes(filter);
        } else if (!CollectionUtils.isEmpty(filter.getProjectCodes()) && filter.getUsername() == null) {
            return getProjectByProjectCodes(filter);
        } else if (filter.getUsername() != null && CollectionUtils.isEmpty(filter.getProjectCodes())) {
            return getProjectByUsername(filter);
        }
        return getProjects();
    }

    private List<ProjectsApiDTO> getProjects() {
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .map(projectMapper::toApiDTO)
                .collect(Collectors.toList());
    }
    private List<ProjectsApiDTO> getProjectByUsernameAndProjectCodes(ProjectFilterApiDTO filter) {
        List<Project> projects = projectRepository.findByUsernameAndProjectCodes(filter.getUsername(), filter.getProjectCodes());
        return projects.stream()
                .map(projectMapper::toApiDTO)
                .collect(Collectors.toList());
    }

    private List<ProjectsApiDTO> getProjectByUsername(ProjectFilterApiDTO filter) {
        List<Project> projects = projectRepository.findByUsername(filter.getUsername());
        return projects.stream()
                .map(projectMapper::toApiDTO)
                .collect(Collectors.toList());
    }

    private List<ProjectsApiDTO> getProjectByProjectCodes(ProjectFilterApiDTO filter) {
        List<Project> projects = projectRepository.findByCodiceIn(filter.getProjectCodes());
        return projects.stream()
                .map(projectMapper::toApiDTO)
                .collect(Collectors.toList());
    }

}