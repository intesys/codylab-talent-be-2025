package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.api.model.ProjectsWithResponsabileApiDTO;
import it.intesys.codylab.api.rest.ProjectsApi;
import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class,TaskMapper.class})
public interface ProjectMapper {

    @Mapping(target = "dataInizio", source = "dataInizio", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "responsabileId", source = "responsabile.id")
    ProjectsApiDTO toApiDTO(Project project);

    @Mapping(target = "dataInizio", source = "dataInizio", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "responsabile", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "responsabile.id", source = "responsabileId")
    Project toEntity(ProjectsApiDTO projectsApiDTO);


    @Mapping(target = "responsabileId", source = "responsabile.id")
    @Mapping(target = "responsabile", source = "responsabile")

    ProjectsWithResponsabileApiDTO toApiDTOWithResponsabile(Project project);
}



