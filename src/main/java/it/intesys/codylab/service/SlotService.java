package it.intesys.codylab.service;

import it.intesys.codylab.dto.SlotDTO;
import it.intesys.codylab.mapper.SlotMapper;
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

    public SlotDTO getSlotById(Long id) {
        return slotMapper.toDTO(slotRepository.findById(id).orElse(null));
    }

    public SlotDTO save(SlotDTO slotDTO) {
        return slotMapper.toDTO(slotRepository.save(slotMapper.toEntity(slotDTO)));
    }

    public List<SlotDTO> findAll() {
        return StreamSupport.stream(slotRepository.findAll().spliterator(), false)
                .map(slotMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        slotRepository.deleteById(id);
    }
}