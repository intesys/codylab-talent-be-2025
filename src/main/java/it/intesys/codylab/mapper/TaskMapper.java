package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SlotMapper.class, UserMapper.class})
public interface TaskMapper {

    @Mapping(source = "startDate", target = "startDate", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "project.name", target = "projectName")
    TasksApiDTO toApiDTO(Task task);

    @Mapping(source = "startDate", target = "startDate", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "projectName", target = "project.name")
    Task toEntity(TasksApiDTO tasksApiDTO);

}
