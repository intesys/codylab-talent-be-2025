package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.*;
import it.intesys.codylab.api.rest.SlotsApi;
import it.intesys.codylab.mapper.SlotMapper;
import it.intesys.codylab.model.Slot;
import it.intesys.codylab.service.SlotService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SlotsRestController implements SlotsApi {

    private final SlotService slotService;
    private final SlotMapper slotMapper;

    public SlotsRestController(SlotService slotService, SlotMapper slotMapper) {
        this.slotService = slotService;
        this.slotMapper = slotMapper;
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
    public ResponseEntity<SlotsPageApiDTO> getSlots(Integer pageNumber, Integer size, String sort, SlotFilterApiDTO slotFilter) {

        Page<Slot> pagedSlots = slotService.findAllPaginated(pageNumber, size);

        List<SlotsApiDTO> taskDtos = pagedSlots.getContent()
                .stream()
                .map(slotMapper::toApiDTO)
                .toList();

        SlotsPageApiDTO response = new SlotsPageApiDTO();
        response.setContent(taskDtos);
        response.setTotalElements(pagedSlots.getTotalElements());
        response.setTotalPages(pagedSlots.getTotalPages());
        response.setSize(pagedSlots.getSize());
        response.setNumber(pagedSlots.getNumber());

        return ResponseEntity.ok(response);


    }

    //    @Override
//    public ResponseEntity<List<SlotsApiDTO>> getSlots(Integer pageNumber, Integer size, String sort, SlotFilterApiDTO slotFilter) {
//        List<SlotsApiDTO> slots = slotService.getSlots();
//        if (slots != null && !slots.isEmpty()) {
//            return ResponseEntity.ok(slots);
//        } else {
//            return ResponseEntity.noContent().build();
//        }
//    }

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
