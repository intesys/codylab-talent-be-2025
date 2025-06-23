package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.dto.TaskDTO;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import it.intesys.codylab.mapper.UserMapper;

@Mapper(componentModel = "spring", uses = {SlotMapper.class, UserMapper.class})
public interface TaskMapper {

    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "dd/MM/yyyy")
    TaskDTO toDTO(Task task);


    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "dd/MM/yyyy")
    Task toEntity(TaskDTO taskDTO);

    @Mapping(source = "project", target = "progettoId")
    TasksApiDTO toTaskApiDTO(Task task);


    default Long map(Project project) {
        if (project == null) {
            return null;
        }
        return project.getId();
    }
    Task toTaskModel(TasksApiDTO taskCreatedDto);
}