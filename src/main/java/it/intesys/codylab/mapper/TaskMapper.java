package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.dto.TaskDTO;
import it.intesys.codylab.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SlotMapper.class, UserMapper.class})
public interface TaskMapper {

    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "dd/MM/yyyy")
    TasksApiDTO toApiDTO(Task task);

    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "dd/MM/yyyy")
    Task toEntity(TasksApiDTO tasksApiDTO);

}