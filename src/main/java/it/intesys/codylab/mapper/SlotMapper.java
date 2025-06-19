package it.intesys.codylab.mapper;

import it.intesys.codylab.dto.SlotDTO;
import it.intesys.codylab.model.Slot;
import org.springframework.stereotype.Component;

@Component
public class SlotMapper {

    public SlotDTO toDTO(Slot slot) {
        if (slot == null) return null;
        SlotDTO dto = new SlotDTO();
        dto.setId(slot.getId());
        dto.setDataInizio(slot.getDataInizio());
        dto.setDataFine(slot.getDataFine());
        dto.setDurata(slot.getDurata());
        dto.setTaskId(slot.getTask() != null ? slot.getTask().getId() : null);
        return dto;
    }

    public Slot toEntity(SlotDTO dto, boolean keepId) {
        if (dto == null) return null;
        Slot slot = new Slot();
        slot.setId(keepId ? dto.getId() : null);
        slot.setDataInizio(dto.getDataInizio());
        slot.setDataFine(dto.getDataFine());
        slot.setDurata(dto.getDurata());
        return slot;
    }

    public Slot toEntity(SlotDTO dto) {
        return toEntity(dto, true);
    }
}
