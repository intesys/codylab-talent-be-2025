package it.intesys.codylab.service;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.mapper.ProjectMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.repository.ProjectRepository;
import it.intesys.codylab.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ProjectsApiDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toApiDTO)
                .toList();
    }

    public ProjectsApiDTO getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(projectMapper::toApiDTO)
                .orElse(null);
    }

    public ProjectsApiDTO save(ProjectsApiDTO projectsApiDTO) {
        Project project = projectMapper.toModel(projectsApiDTO);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toApiDTO(savedProject);
    }

    public ProjectsApiDTO updateProject(Long id, ProjectsApiDTO projectsApiDTO) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progetto non trovato con id: " + id));

        existingProject.setNome(projectsApiDTO.getNome());
        existingProject.setDescrizione(projectsApiDTO.getDescrizione());

        // Metodo per modificare solo una parte del progetto e tenere conto del codice unico
        if (projectsApiDTO.getCodice() != null && !projectsApiDTO.getCodice().equals(existingProject.getCodice())) {
            boolean codiceExists = projectRepository.existsByCodice(projectsApiDTO.getCodice());
            if (codiceExists) {
                throw new RuntimeException("Codice già esistente: " + projectsApiDTO.getCodice());
            }
            existingProject.setCodice(projectsApiDTO.getCodice());
        }

        Project updatedProject = projectRepository.save(existingProject);
        return projectMapper.toApiDTO(updatedProject);
    }
}
//    public ProjectDTO getProjectById(Long id) {
//        return projectRepository.findById(id)
//                .map(projectMapper::toDTO)
//                .orElse(null);
//    }
//
//    public ProjectDTO findByCodice(String codice) {
//        return projectMapper.toDTO(projectRepository.findByCodice(codice));
//    }
//
//    public List<ProjectDTO> findAll() {
//        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
//                .map(projectMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//
//    public ProjectDTO save(ProjectDTO projectDTO) {
//        Project project = projectMapper.toEntity(projectDTO);
//
//        // Setta il responsabile, se presente
//        if (projectDTO.getResponsabileId() != null) {
//            project.setResponsabile(
//                    userRepository.findById(projectDTO.getResponsabileId())
//                            .orElseThrow(() -> new RuntimeException("Responsabile non trovato"))
//            );
//        }
//
//        Project savedProject = projectRepository.save(project);
//        return projectMapper.toDTO(savedProject);
//    }
//
//    public void delete(Long id) {
//        projectRepository.deleteById(id);
//    }



