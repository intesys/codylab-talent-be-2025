package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.UsersApiDTO;
import it.intesys.codylab.model.Task;
import it.intesys.codylab.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface UserMapper {

    @Mapping(target = "managedProjects", ignore = true)
    @Mapping(target = "tasks", source = "tasks")
    UsersApiDTO toApiDTO(User user);

    List<UsersApiDTO> toApiDTO(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "managedProjects", ignore = true)
    @Mapping(target = "tasks", source = "tasks")
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
        if (dto.getDailyHours() != null) {
            user.setDailyHours(dto.getDailyHours());
        }
        if (dto.getTasks() != null) {
            List<Task> tasks = dto.getTasks().stream().map(taskApiDto -> {
                Task task = new Task();

                // Map i campi di TasksApiDTO in Task
                task.setId(taskApiDto.getId());
                task.setCode(taskApiDto.getCode());
                task.setName(taskApiDto.getName());
                task.setDescription(taskApiDto.getDescription());
                task.setStartDate(taskApiDto.getStartDate());
                task.setDuration(taskApiDto.getDuration());

                // Aggiungi altri campi se necessario
                return task;
            }).collect(Collectors.toList());

            // Imposta la lista di Task sull'utente
            user.setTasks(tasks);
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
