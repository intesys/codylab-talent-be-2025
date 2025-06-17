package it.intesys.codylab.service;

import it.intesys.codylab.model.Slot;
import it.intesys.codylab.repository.SlotRepository;
import org.springframework.stereotype.Service;

@Service
public class SlotService {
    private final SlotRepository slotRepository;

    public SlotService(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public Slot findById(Long id) {
        return slotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slot not found with id: " + id));
    }

    public Slot saveSlot(Slot slot) {
        return slotRepository.save(slot);
    }
}
