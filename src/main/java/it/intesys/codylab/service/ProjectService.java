package it.intesys.codylab.service;

import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.api.model.ProjectsWithResponsabileApiDTO;
import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.mapper.ProjectMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
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

    public List<ProjectDTO> findAll() {
        List<Project> projects = StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return projectMapper.toDTOList(projects);
    }

    @Transactional
    public List<ProjectDTO> delete(Long id) {
        // Elimina il progetto specifico
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found with id: " + id));
        // Gestisci relazioni
        project.setResponsabile(null);
        project.getTasks().forEach(t -> t.setProject(null));

        projectRepository.delete(project);

        // Restituisci tutti i progetti rimanenti
        return projectMapper.toDTOList(projectRepository.findAll());
    }



    public ProjectsApiDTO getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(projectMapper::toApiDTO)
                .orElseThrow(() -> new NoSuchElementException("Progetto con ID " + id + " non trovato."));
    }

    public ProjectsApiDTO createProject(ProjectsApiDTO projectsApiDTO) {
        if (projectsApiDTO == null) {
            throw new IllegalArgumentException("Il progetto non può essere nullo");
        }
        if (projectsApiDTO.getCodice() == null) {
            throw new IllegalArgumentException("Il codice del progetto non può essere nullo");
        }

        Project project = projectMapper.toEntity(projectsApiDTO);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toApiDTO(savedProject);
    }

    public ProjectsApiDTO updateProject(Long id, ProjectsApiDTO projectsApiDTO) {
        if (projectsApiDTO == null) {
            throw new IllegalArgumentException("Il progetto non può essere nullo");
        }
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
    public List<ProjectsWithResponsabileApiDTO> getAllProjectsWithResponsabile() {
        List<Project> projects = projectRepository.findAllWithResponsabile();
        return projects.stream()
                .map(projectMapper::toApiDTOWithResponsabile)
                .collect(Collectors.toList());
    }

    public Page<Project> findAllPaginated(int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return projectRepository.findAll(pageable);
    }

//    public List<ProjectsApiDTO> getProjectByProjectFilter(ProjectFilterApiDTO filter) {
//
//        if(filter.getCodice()  != null && filter.getUsername() != null) {
//            return getProjectByUsernameAndProjectCodes(filter);
//        } else if (filter.getCodice() != null && filter.getUsername() == null) {
//            return getProjectByProjectCodes(filter);
//        } else if (filter.getCodice() == null && filter.getUsername() != null) {
//            return getProjectByUsername(filter);
//        }
//        return getProjects();
//    }
//
//
//    private List<ProjectsApiDTO> getProjectByUsernameAndProjectCodes(ProjectFilterApiDTO filter) {
//        List<Project> projects = projectRepository.findByCodiceAndUsername(filter.getUsername(), filter.getCodice());
//        return projects.stream()
//                .map(projectMapper::toApiDTO)
//                .collect(Collectors.toList());
//    }
//
//    private List<ProjectsApiDTO> getProjectByUsername(ProjectFilterApiDTO filter) {
//        List<Project> projects = projectRepository.findByUsername(filter.getUsername());
//        return projects.stream()
//                .map(projectMapper::toApiDTO)
//                .collect(Collectors.toList());
//    }
//
//    private List<ProjectsApiDTO> getProjectByProjectCodes(ProjectFilterApiDTO filter) {
//        List<Project> projects = projectRepository.findByCodice(filter.getCodice());
//        return projects.stream()
//                .map(projectMapper::toApiDTO)
//                .collect(Collectors.toList());
//    }

//    private List<ProjectsApiDTO> getProjects() {
//        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
//                .map(projectMapper::toApiDTO)
//                .collect(Collectors.toList());
//    }

}







