package it.intesys.codylab.mapper;

import it.intesys.codylab.dto.SlotDTO;
import it.intesys.codylab.model.Slot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SlotMapper {
    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "dataFine", target = "dataFine", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    SlotDTO toDTO(Slot slot);

    @Mapping(source = "taskId", target = "task.id")
    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "dataFine", target = "dataFine",dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    Slot toEntity(SlotDTO slotDTO);



}