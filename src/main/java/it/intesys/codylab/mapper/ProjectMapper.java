package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public abstract class ProjectMapper {

    @Autowired
    protected UserRepository userRepository;

    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "responsabile.id", target = "responsabileId")
    public abstract ProjectsApiDTO toDTO(Project project);

    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "responsabile", ignore = true)
    public abstract Project toEntity(ProjectsApiDTO dto);

    @AfterMapping
    protected void mapResponsabileToEntity(ProjectsApiDTO dto, @MappingTarget Project entity) {
        if (dto.getResponsabileId() != null) {
            userRepository.findById(dto.getResponsabileId()).ifPresent(entity::setResponsabile);
        }
    }
}
