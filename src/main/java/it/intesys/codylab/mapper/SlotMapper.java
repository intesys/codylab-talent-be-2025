package it.intesys.codylab.mapper;

import it.intesys.codylab.dto.SlotDTO;
import it.intesys.codylab.model.Slot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SlotMapper {
    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "dd-MM-yyyy")
    @Mapping(source = "dataFine", target = "dataFine", dateFormat = "dd-MM-yyyy")
    @Mapping(source = "task.id", target = "taskId")
    SlotDTO toDTO(Slot slot);

    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "dd-MM-yyyy")
    @Mapping(source = "dataFine", target = "dataFine", dateFormat = "dd-MM-yyyy")
    @Mapping(source = "taskId", target = "task.id")
    Slot toEntity(SlotDTO slotDTO);
}
