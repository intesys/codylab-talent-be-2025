package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.SlotFilterApiDTO;
import it.intesys.codylab.api.model.SlotsApiDTO;
import it.intesys.codylab.api.model.SlotsPageApiDTO;
import it.intesys.codylab.api.rest.SlotsApi;
import it.intesys.codylab.service.SlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SlotRestController implements SlotsApi {
    private SlotService slotService;

    public SlotRestController(SlotService slotService) {
        this.slotService = slotService;
    }

    @Override
    public ResponseEntity<SlotsPageApiDTO> getSlots(Integer pageNumber, Integer size, String sort, SlotFilterApiDTO filter) {
        List<SlotsApiDTO> slots = slotService.getSlots();
        SlotsPageApiDTO slotsPage = new SlotsPageApiDTO();
        slotsPage.setContent(slots);
        slotsPage.setTotalElements((long) slots.size());
        return ResponseEntity.ok(slotsPage);
    }

    @Override
    public ResponseEntity<SlotsApiDTO> getSlotById(Long id) {
        SlotsApiDTO slot = slotService.getSlotById(id);
        if (slot == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(slot);
    }

    @Override
    public ResponseEntity<SlotsApiDTO> createSlot(SlotsApiDTO slotDto) {
        SlotsApiDTO createdSlot = slotService.createSlot(slotDto);
        URI location = URI.create("/api/v1/slots/" + createdSlot.getId());
        return ResponseEntity.created(location).body(createdSlot);
    }

    @Override
    public ResponseEntity<SlotsApiDTO> updateSlot(Long id, SlotsApiDTO slotDto) {
        SlotsApiDTO updatedSlot = slotService.updateSlot(id, slotDto);
        if (updatedSlot == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedSlot);
    }

    @Override
    public ResponseEntity<Void> deleteSlot(Long id) {
        slotService.deleteSlot(id);
        return ResponseEntity.noContent().build();
    }
}
