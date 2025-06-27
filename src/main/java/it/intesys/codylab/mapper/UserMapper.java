package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = TaskMapper.class)
public interface UserMapper {




    @Mapping(source = "progettiResponsabili", target = "progettiGestiti")
    UsersApiDTO toApiDTO(User user);

    User toEntity(UsersApiDTO usersApiDTO);

    @Mapping(target = "responsabileId", source = "responsabile.id")
    ProjectsApiDTO toApiDTO(Project project);

    @AfterMapping
    default void setResponsabileIds(User user, @MappingTarget UsersApiDTO dto) {
        if (dto.getProgettiGestiti() != null) {
            dto.getProgettiGestiti().forEach(progetto -> {
                progetto.setResponsabileId(user.getId()); // Imposta l'ID del responsabile
            });
        }
    }


}