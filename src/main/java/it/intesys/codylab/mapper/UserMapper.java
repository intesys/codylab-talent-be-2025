package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.dto.UserDTO;
import it.intesys.codylab.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TaskMapper.class)
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

    @Mapping(source = "mail", target = "email")
    UsersApiDTO toApiDTO(User user);
    @Mapping(source = "email", target = "mail")
    User toEntity(UsersApiDTO dto);
}



