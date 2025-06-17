package it.intesys.codylab.mapper;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface ProjectMapper {

    @Mapping(target = "dataInizio", source = "dataInizio", dateFormat = "dd/MM/yyyy")
    ProjectDTO toDTO(Project project);

    @Mapping(target = "dataInizio", source = "dataInizio", dateFormat = "dd/MM/yyyy")
    Project toEntity(ProjectDTO projectDTO);
}