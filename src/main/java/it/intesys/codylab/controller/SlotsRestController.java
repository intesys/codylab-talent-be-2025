package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.SlotsApiDTO;
import it.intesys.codylab.api.rest.SlotsApi;
import it.intesys.codylab.mapper.SlotMapper;
import it.intesys.codylab.service.SlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SlotsRestController implements SlotsApi {
    private SlotService slotService;
    private SlotMapper slotMapper;
    public SlotsRestController(SlotService slotService, SlotMapper slotMapper) {
        this.slotService = slotService;
        this.slotMapper = slotMapper;

    }

    @Override
    public ResponseEntity<SlotsApiDTO> createSlot(SlotsApiDTO slotsApiDTO) {
        return SlotsApi.super.createSlot(slotsApiDTO);
    }

    @Override
    public ResponseEntity<Void> deleteSlot(Long slotId) {
        return SlotsApi.super.deleteSlot(slotId);
    }

    @Override
    public ResponseEntity<SlotsApiDTO> getSlotById(Long slotId) {
        return SlotsApi.super.getSlotById(slotId);
    }


    @Override
    public ResponseEntity<SlotsApiDTO> updateSlot(Long slotId, SlotsApiDTO slotsApiDTO) {
        return SlotsApi.super.updateSlot(slotId, slotsApiDTO);
    }

    @Override
    public ResponseEntity<List<SlotsApiDTO>> searchSlots(Integer pageNumber, Integer size, String sort, Long taskId) {
        List<SlotsApiDTO> slots = slotService.getAllSlots(); // già lista di SlotsApiDTO
        return ResponseEntity.ok(slots);
    }

}
