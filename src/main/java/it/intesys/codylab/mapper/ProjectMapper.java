package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.UserRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface ProjectMapper {

    @Mapping(target = "startDate", source = "startDate", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "manager", source = "manager.username")
    ProjectsApiDTO toApiDTO(Project project);

    @Mapping(target = "startDate", source = "startDate", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    Project toEntity(ProjectsApiDTO projectsApiDTO);

    default Project toEntityWithUser(ProjectsApiDTO projectsApiDTO, UserRepository userRepository) {
        Project project = toEntity(projectsApiDTO);
        if (projectsApiDTO.getManager() != null) {
            User user = userRepository.findByUsername(projectsApiDTO.getManager())
                    .orElseThrow(() -> new RuntimeException("User not found: " + projectsApiDTO.getManager()));
            project.setManager(user);
        }
        return project;
    }
}
