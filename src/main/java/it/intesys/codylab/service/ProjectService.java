package it.intesys.codylab.service;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.mapper.ProjectMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ProjectDTO save(ProjectDTO projectDTO) {
        Project project = projectMapper.toEntity(projectDTO);
        initProject(project);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toDTO(savedProject);
    }

    private void initProject(Project project) {
        if (project.getTasks() != null) {
            project.getTasks().forEach(task -> task.setProject(project));
        }
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
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
        if (project.getUsers() != null) {
            for (User user : new ArrayList<>(project.getUsers())) {
                user.getProjects().remove(project);
            }
            project.getTasks().clear();
            project.getUsers().clear();
            projectRepository.deleteById(id);
        }
    }

}