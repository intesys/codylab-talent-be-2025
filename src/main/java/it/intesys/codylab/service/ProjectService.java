package it.intesys.codylab.service;

import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.mapper.ProjectMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.repository.ProjectRepository;
import it.intesys.codylab.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userRepository = userRepository;
    }

    public List<Project> findUserWithProjectManagers(Long id) {
        this.userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        return projectRepository.findProjectsManagedByUser(id);
    }

    public ProjectsApiDTO getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(projectMapper::toApiDTO)
                .orElseThrow(() -> new NoSuchElementException("Project not found"));
    }

    public ProjectsApiDTO createProject(ProjectsApiDTO projectsApiDTO) {
        projectsApiDTO.setId(null);
        Project project = projectMapper.toEntityWithUser(projectsApiDTO, userRepository);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toApiDTO(savedProject);
    }

    public ProjectsApiDTO updateProject(Long id, ProjectsApiDTO projectsApiDTO) {
        Project project = projectMapper.toEntity(projectsApiDTO);
        project.setId(id);
        project.setTasks(projectRepository.findById(id).orElseThrow().getTasks());
        project.setManager(projectRepository.findById(id).orElseThrow().getManager());
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
            projectPage = projectRepository.findByUsernameAndProjectCodes(filter.getUsername(), filter.getProjectCodes(), pageable);
        } else if (!CollectionUtils.isEmpty(filter.getProjectCodes())) {
            projectPage = projectRepository.findByCodeIn(filter.getProjectCodes(), pageable);
        } else if (filter.getUsername() != null) {
            projectPage = projectRepository.findByUsername(filter.getUsername(), pageable);
        } else {
            projectPage = projectRepository.findAll(pageable);
        }

        return projectPage.map(projectMapper::toApiDTO);
    }

    public List<ProjectsApiDTO> simpleGetProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toApiDTO)
                .collect(Collectors.toList());
    }
}
