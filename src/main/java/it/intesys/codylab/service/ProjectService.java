package it.intesys.codylab.service;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.dto.TaskDTO;
import it.intesys.codylab.mapper.ProjectMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.Task;
import it.intesys.codylab.repository.ProjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    public ProjectDTO getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(projectMapper::toDTO)
                .orElse(null);
    }

    public ProjectDTO findByCodice(String codice) {
        return projectMapper.toDTO(projectRepository.findByCodice(codice));
    }

    public List<ProjectDTO> findAll() {
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

//    public List<ProjectDTO> findByDurata(Integer durata) {
//        return StreamSupport.stream(projectRepository.findByDurata(durata).spliterator(), false)
//                .map(projectMapper::toDTO)
//                .collect(Collectors.toList());
//    }

    public ProjectDTO save(ProjectDTO projectDTO) {
        Project project = projectMapper.toEntity(projectDTO);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toDTO(savedProject);
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    public ProjectDTO saveTask(@RequestBody ProjectDTO projectDTO) {
        Project project = projectMapper.toEntity(projectDTO);


        List<Task> tasks = project.getTasks() == null ?
                new ArrayList<>() :
                project.getTasks().stream()
                        .map(task -> {
                            task.setProject(project);
                            return task;
                        }).collect(Collectors.toList());

        project.setTasks(tasks);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toDTO(savedProject);
    }
}


