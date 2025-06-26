package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.api.rest.ProjectsApi;
import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface ProjectMapper {

    @Mapping(target = "dataInizio", source = "dataInizio", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "responsabile", source = "responsabile.username")
    ProjectsApiDTO toApiDTO(Project project);

    @Mapping(target = "dataInizio", source = "dataInizio", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "responsabile", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    Project toEntity(ProjectsApiDTO projectsApiDTO);
}