package it.intesys.codylab.mapper;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "dd/MM/yyyy")
    ProjectDTO toDTO(Project project);

    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "dd/MM/yyyy")
    Project toEntity(ProjectDTO projectDTO);

}