package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "managedProjects", ignore = true)
    UsersApiDTO toApiDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "managedProjects", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    User toEntity(UsersApiDTO usersApiDTO);

    @Named("updateUser")
    default User updateUserFromDto(UsersApiDTO dto, @MappingTarget User user) {
        if (dto == null) {
            return user;
        }

        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }
        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getProfile() != null) {
            user.setProfile(dto.getProfile());
        }

        return user;
    }

    default String map(User user) {
        return user != null ? user.getUsername() : null;
    }

    default User map(String username) {
        if (username == null) {
            return null;
        }
        User user = new User();
        user.setUsername(username);
        return user;
    }
}
