package it.intesys.codylab.service;

import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.mapper.ProjectMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new NoSuchElementException("Progetto non trovato"));
    }

    public ProjectsApiDTO createProject(ProjectsApiDTO projectsApiDTO) {
        projectsApiDTO.setId(null);
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
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null && project.getUsers() != null) {
            for (User user : new ArrayList<>(project.getUsers())) {
                user.getProjects().remove(project);
            }
            project.getTasks().clear();
            project.getUsers().clear();
            projectRepository.deleteById(id);
        }
    }

    public Page<ProjectsApiDTO> getProjects(ProjectFilterApiDTO filter, int pageNumber, int size, String sort) {
        if (sort == null || sort.isBlank()) {
            sort = "id";
        }
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sort));

        Page<Project> projectPage;
        if (!CollectionUtils.isEmpty(filter.getProjectCodes()) && filter.getUsername() != null) {
            projectPage = projectRepository.findByUsernameAndProjectCodes(filter.getUsername(), filter.getProjectCodes(), pageable);
        } else if (!CollectionUtils.isEmpty(filter.getProjectCodes())) {
            projectPage = projectRepository.findByCodiceIn(filter.getProjectCodes(), pageable);
        } else if (filter.getUsername() != null) {
            projectPage = projectRepository.findByUsername(filter.getUsername(), pageable);
        } else {
            projectPage = projectRepository.findAll(pageable);
        }

        return projectPage.map(projectMapper::toApiDTO);
    }
}