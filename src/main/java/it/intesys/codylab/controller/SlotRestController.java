package it.intesys.codylab.controller;

import it.intesys.codylab.api.model.SlotFilterApiDTO;
import it.intesys.codylab.api.model.SlotsApiDTO;
import it.intesys.codylab.api.rest.SlotsApi;
import it.intesys.codylab.service.SlotService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class SlotRestController implements SlotsApi {
    private SlotService slotService;

    public SlotRestController(SlotService slotService) {
        this.slotService = slotService;
    }

    @Override
    public ResponseEntity<List<SlotsApiDTO>> getSlots(
            Integer pageNumber,
            Integer size,
            String sort,
            String ids) {

        if (pageNumber == null) pageNumber = 0;
        if (size == null) size = 10;
        if (sort == null || sort.isBlank()) sort = "id";

        List<Long> idList = null;
        if (ids != null && !ids.isBlank()) {
            try {
                idList = Arrays.stream(ids.split(","))
                        .map(String::trim)
                        .map(Long::parseLong)
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().build(); // formato errato
            }
        }

        // Passa idList anche se è null, il service gestirà il filtro
        Page<SlotsApiDTO> slots = slotService.getSlots(idList, pageNumber, size, sort);

        if (slots == null || slots.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(slots.getContent());
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
