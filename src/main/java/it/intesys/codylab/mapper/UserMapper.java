package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.dto.UserDTO;
import it.intesys.codylab.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TaskMapper.class)
public interface UserMapper {

    UsersApiDTO toApiDTO(User user);

    User toEntity(UsersApiDTO usersApiDTO);

}