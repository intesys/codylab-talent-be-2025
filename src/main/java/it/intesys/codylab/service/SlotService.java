package it.intesys.codylab.service;

import it.intesys.codylab.api.model.SlotsApiDTO;
import it.intesys.codylab.mapper.SlotMapper;
import it.intesys.codylab.model.Slot;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.SlotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class SlotService {
    private  final SlotRepository slotRepository;
    private final SlotMapper slotMapper;

    public SlotService(SlotRepository slotRepository, SlotMapper slotMapper) {
        this.slotRepository = slotRepository;
        this.slotMapper = slotMapper;
    }

    public List <SlotsApiDTO> getSlots() {
        return StreamSupport.stream(slotRepository.findAll().spliterator(), false)
                .map(slotMapper::toApiDTO)
                .toList();
    }

    public Page<Slot> findAllPaginated(int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return slotRepository.findAll(pageable);
    }

    public SlotsApiDTO getSlotById(Long id) {
        return slotRepository.findById(id)
                .map(slotMapper::toApiDTO)
                .orElse(null);
    }

    public SlotsApiDTO createSlot(SlotsApiDTO slotsApiDTO) {
        Slot slots = slotMapper.toEntity(slotsApiDTO);
        Slot savedSlot = slotRepository.save(slots);
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
