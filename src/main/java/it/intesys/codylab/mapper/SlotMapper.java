package it.intesys.codylab.mapper;

import it.intesys.codylab.dto.SlotDTO;
import it.intesys.codylab.model.Slot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SlotMapper {

    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "dataFine", target = "dataFine", dateFormat = "dd/MM/yyyy")
    SlotDTO toDTO(Slot slot);

    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "dataFine", target = "dataFine", dateFormat = "dd/MM/yyyy")
    Slot toEntity(SlotDTO slotDTO);
}