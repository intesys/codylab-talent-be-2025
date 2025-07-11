package it.intesys.codylab.service;

import it.intesys.codylab.api.model.SlotsApiDTO;
import it.intesys.codylab.mapper.SlotMapper;
import it.intesys.codylab.model.Slot;
import it.intesys.codylab.repository.SlotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SlotService {

    private final SlotRepository slotRepository;
    private final SlotMapper slotMapper;

    public SlotService(SlotRepository slotRepository, SlotMapper slotMapper) {
        this.slotRepository = slotRepository;
        this.slotMapper = slotMapper;
    }

    public Page<SlotsApiDTO> getSlots(List<Long> idList, Integer pageNumber, Integer size, String sort) {
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sort));

        Page<Slot> slotPage;
        if (idList != null && !idList.isEmpty()) {
            slotPage = slotRepository.findByIdIn(idList, pageable);
        } else {
            slotPage = slotRepository.findAll(pageable);
        }

        return slotPage.map(slotMapper::toApiDTO);
    }

    public SlotsApiDTO getSlotById(Long id) {
        return slotRepository.findById(id)
                .map(slotMapper::toApiDTO)
                .orElseThrow(() -> new NoSuchElementException("Slot not found with id: " + id));
    }

    public SlotsApiDTO createSlot(SlotsApiDTO slotsApiDTO) {
        Slot slot = slotMapper.toEntity(slotsApiDTO);
        slot.setId(null);
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
