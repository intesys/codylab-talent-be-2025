package it.intesys.codylab.mapper;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.Task;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface ProjectMapper {

    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "dd/MM/yyyy")
    ProjectDTO toDTO(Project project);

    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "responsabile", ignore = true)
    Project toEntity(ProjectDTO projectDTO);

    @AfterMapping
    default void mapResponsabileToDTO(Project project, @MappingTarget ProjectDTO dto) {
        if (project.getResponsabile() != null) {
            dto.setResponsabileId(project.getResponsabile().getId());
        }
    }


    @AfterMapping
    default void mapResponsabileToEntity(ProjectDTO dto, @MappingTarget Project project) {
    }
}