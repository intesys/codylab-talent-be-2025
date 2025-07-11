package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.SlotsApiDTO;
import it.intesys.codylab.model.Slot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SlotMapper {

    @Mapping(source = "startDate", target = "startDate", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "endDate", target = "endDate", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "task.id", target = "taskId")
    SlotsApiDTO toApiDTO(Slot slot);

    @Mapping(source = "startDate", target = "startDate", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "endDate", target = "endDate", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "taskId", target = "task.id")
    Slot toEntity(SlotsApiDTO slotsApiDTO);
}
