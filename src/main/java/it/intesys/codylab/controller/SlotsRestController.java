package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.SlotFilterApiDTO;
import it.intesys.codylab.api.model.SlotsApiDTO;
import it.intesys.codylab.api.rest.SlotsApi;
import it.intesys.codylab.service.SlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SlotsRestController implements SlotsApi {

    private final SlotService slotService;

    public SlotsRestController(SlotService slotService) {
        this.slotService = slotService;
    }

    @Override
    public ResponseEntity<SlotsApiDTO> updateSlot(Long id, SlotsApiDTO slotsApiDTO) {
        SlotsApiDTO updatedSlot = slotService.updateSlot(id, slotsApiDTO);
        if (updatedSlot != null) {
            return ResponseEntity.ok(updatedSlot);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<SlotsApiDTO>> getSlots(Integer pageNumber, Integer size, String sort, SlotFilterApiDTO slotFilter) {
        List<SlotsApiDTO> slots = slotService.getSlots();
        if (slots != null && !slots.isEmpty()) {
            return ResponseEntity.ok(slots);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public ResponseEntity<SlotsApiDTO> getSlotById(Long id) {
        SlotsApiDTO slot = slotService.getSlotById(id);
        if (slot != null) {
            return ResponseEntity.ok(slot);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteSlot(Long id) {
        if (slotService.getSlotById(id) != null) {
            slotService.deleteSlot(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<SlotsApiDTO> createSlot(SlotsApiDTO slotsApiDTO) {
        SlotsApiDTO createdSlot = slotService.createSlot(slotsApiDTO);
        if (createdSlot != null) {
            return ResponseEntity.created(URI.create("/api/v1/slots/" + createdSlot.getId())).body(createdSlot);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
