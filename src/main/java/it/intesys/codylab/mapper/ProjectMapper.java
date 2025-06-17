package it.intesys.codylab.mapper;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
//    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "dd-MM-yyyy")
    ProjectDTO toDto(Project project);

    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "dd-MM-yyyy")
    Project toEntity(ProjectDTO projectDTO);
}
