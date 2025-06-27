package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TaskMapper.class)
public interface UserMapper {

    @Mapping(source = "progettiResponsabili", target = "progettiResponsabili")
    UsersApiDTO toApiDTO(User user);

    User toEntity(UsersApiDTO usersApiDTO);

    // Metodo per mappare un utente in una stringa (ad esempio il suo username)
    default String map(User user) {
        return user != null ? user.getUsername() : null;
    }

    default User map(String username) {
        if (username == null) {
            return null;
        }
        User user = new User();
        user.setUsername(username); // Imposta lo username, potrebbero essere necessari altri dettagli
        return user;
    }

}