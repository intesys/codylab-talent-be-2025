package it.intesys.codylab.service;

import it.intesys.codylab.api.model.SlotsApiDTO;
import it.intesys.codylab.mapper.SlotMapper;
import it.intesys.codylab.model.Slot;
import it.intesys.codylab.repository.SlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SlotService {

    private SlotRepository slotRepository;
    private SlotMapper slotMapper;

    public SlotService(SlotRepository slotRepository, SlotMapper slotMapper) {
        this.slotRepository = slotRepository;
        this.slotMapper = slotMapper;
    }

    public List<SlotsApiDTO> getSlots() {
        return StreamSupport.stream(slotRepository.findAll().spliterator(), false)
                .map(slotMapper::toApiDTO)
                .collect(Collectors.toList());
    }

    public SlotsApiDTO getSlotById(Long id) {
        return slotRepository.findById(id)
                .map(slotMapper::toApiDTO)
                .orElse(null);
    }

    public SlotsApiDTO createSlot(SlotsApiDTO slotsApiDTO) {
        Slot slot = slotMapper.toEntity(slotsApiDTO);
        Slot savedSlot = slotRepository.save(slot);
        return slotMapper.toApiDTO(savedSlot);
    }

    public SlotsApiDTO updateSlot(Long id, SlotsApiDTO slotsApiDTO) {
        Slot slot = slotMapper.toEntity(slotsApiDTO);
        slot.setId(id);
        Slot updatedSlot = slotRepository.save(slot);
        return slotMapper.toApiDTO(updatedSlot);
    }

    public void deleteSlot(Long id) {
        slotRepository.deleteById(id);
    }
}
