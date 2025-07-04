package it.intesys.codylab.service;

import it.intesys.codylab.api.model.SlotFilterApiDTO;
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

    private SlotRepository slotRepository;
    private SlotMapper slotMapper;

    public SlotService(SlotRepository slotRepository, SlotMapper slotMapper) {
        this.slotRepository = slotRepository;
        this.slotMapper = slotMapper;
    }

    public Page<SlotsApiDTO> getSlots(SlotFilterApiDTO filter, int pageNumber, int size, String sort) {
        if (sort == null || sort.isBlank()) {
            sort = "id";
        }
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sort));

        Page<Slot> slotPage;

        if(!filter.getIds().isEmpty() && filter.getTaskId() != null) {
            slotPage = findByIdsAndTaskId(filter.getIds(), filter.getTaskId(), pageable);
        } else if (filter.getTaskId() != null && filter.getIds().isEmpty()) {
            slotPage = findByTaskId(filter.getTaskId(), pageable);
        } else if (filter.getTaskId() == null && !filter.getIds().isEmpty()) {
            slotPage = findByIds(filter.getIds(), pageable);
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

    private Page<Slot> findByIdsAndTaskId(List<Long> ids, Long taskId, Pageable pageable) {
        return slotRepository.findByIdsAndTaskId(ids, taskId, pageable);
    }

    private Page<Slot> findByTaskId(Long taskId, Pageable pageable) {
        return slotRepository.findByTaskId(taskId, pageable);
    }

    private Page<Slot> findByIds(List<Long> ids, Pageable pageable) {
        return slotRepository.findByIds(ids, pageable);
    }
}
