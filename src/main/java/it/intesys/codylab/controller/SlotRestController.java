package it.intesys.codylab.controller;

import it.intesys.codylab.dto.SlotDTO;
import it.intesys.codylab.service.SlotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/slots")
public class SlotRestController {

    private final SlotService slotService;

    public SlotRestController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping("/slot")
    public SlotDTO createSlot(@RequestBody SlotDTO slotDTO) {
        return slotService.createSlot(slotDTO);
    }
}
