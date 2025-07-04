package it.intesys.codylab.service;

import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.mapper.ProjectMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.NoSuchElementException;

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
                .orElseThrow(() -> new NoSuchElementException("Project not found with id: " + id));
    }

    public ProjectsApiDTO createProject(ProjectsApiDTO projectsApiDTO) {
        if (projectsApiDTO.getCodice() == null) {
            throw new IllegalArgumentException("Il codice del progetto non può essere nullo");
        }
        Project project = projectMapper.toEntity(projectsApiDTO);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toApiDTO(savedProject);
    }

    public ProjectsApiDTO updateProject(Long id, ProjectsApiDTO projectsApiDTO) {
        if (projectsApiDTO.getCodice() == null) {
            throw new IllegalArgumentException("Il codice del progetto non può essere nullo");
        }
        Project project = projectMapper.toEntity(projectsApiDTO);
        project.setId(id);
        Project updatedProject = projectRepository.save(project);
        return projectMapper.toApiDTO(updatedProject);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Page<ProjectsApiDTO> getProjects(ProjectFilterApiDTO filter, int pageNumber, int size, String sort) {
        if (sort == null || sort.isBlank()) {
            sort = "id";
        }
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sort));

        Page<Project> projectPage;

        if (!CollectionUtils.isEmpty(filter.getProjectCodes()) && filter.getUsername() != null) {
            projectPage = getByUsernameAndProjectCodes(filter, pageable);
        } else if (!CollectionUtils.isEmpty(filter.getProjectCodes())) {
            projectPage = getByProjectCodes(filter, pageable);
        } else if (filter.getUsername() != null) {
            projectPage = getByUsername(filter, pageable);
        } else {
            projectPage = projectRepository.findAll(pageable);
        }

        return projectPage.map(projectMapper::toApiDTO);
    }

    private Page<Project> getByUsernameAndProjectCodes(ProjectFilterApiDTO filter, Pageable pageable) {
        return projectRepository.findByUsernameAndProjectCodes(filter.getUsername(), filter.getProjectCodes(), pageable);
    }


    private Page<Project> getByUsername(ProjectFilterApiDTO filter, Pageable pageable) {
        return projectRepository.findByUsername(filter.getUsername(), pageable);
    }


    private Page<Project> getByProjectCodes(ProjectFilterApiDTO filter, Pageable pageable) {
        return projectRepository.findByCodiceIn(filter.getProjectCodes(), pageable);
    }


}