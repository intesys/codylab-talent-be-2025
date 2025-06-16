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
    public Slot findById(Long slotId) {
        return slotRepository.findById(slotId);
    }
    public void saveSlot(Slot slot) {
        slotRepository.save(slot);
    }

}
