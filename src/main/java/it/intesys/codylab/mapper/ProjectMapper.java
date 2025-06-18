package it.intesys.codylab.mapper;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface ProjectMapper {

    ProjectDTO toDTO(Project project);

    Project toEntity(ProjectDTO dto);

}
