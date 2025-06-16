package it.intesys.codylab.controller;

import it.intesys.codylab.model.Slot;
import it.intesys.codylab.service.SlotService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slots")
public class SlotsRestController {
    private final SlotService slotService;

    public SlotsRestController(SlotService slotService) {
        this.slotService = slotService;
    }

    @GetMapping("/{slotId}")
    public String getSlot(@PathVariable Long slotId) {
        return slotService.findById(slotId).toString();

}
   @PostMapping("/addSlot")
    public void addSlot(@RequestBody Slot slot) {
        slotService.saveSlot(slot);
   }

}
