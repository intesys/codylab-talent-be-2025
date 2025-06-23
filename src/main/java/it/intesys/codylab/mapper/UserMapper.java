package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Mapping da Entity a DTO - ignora le relazioni complesse
    @Mapping(target = "progettiResponsabili", ignore = true)
    UsersApiDTO toApiDTO(User user);

    // Non usare questo metodo direttamente - usa updateUserFromDto invece
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "progettiResponsabili", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    User toEntity(UsersApiDTO usersApiDTO);

    // Metodo personalizzato per aggiornare un utente esistente
    @Named("updateUser")
    default User updateUserFromDto(UsersApiDTO dto, @MappingTarget User user) {
        if (dto == null) {
            return user;
        }
        
        // Aggiorna solo i campi base, mai le relazioni
        if (dto.getNome() != null) {
            user.setNome(dto.getNome());
        }
        if (dto.getCognome() != null) {
            user.setCognome(dto.getCognome());
        }
        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getMail() != null) {
            user.setMail(dto.getMail());
        }
        if (dto.getProfilo() != null) {
            user.setProfilo(dto.getProfilo());
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