package it.intesys.codylab.service;

import it.intesys.codylab.dto.SlotDTO;
import it.intesys.codylab.mapper.SlotMapper;
import it.intesys.codylab.model.Slot;
import it.intesys.codylab.model.Task;
import it.intesys.codylab.repository.SlotRepository;
import it.intesys.codylab.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class SlotService {

    private SlotRepository slotRepository;
    private SlotMapper slotMapper;
    private TaskRepository taskRepository;

    public SlotService(SlotRepository slotRepository, SlotMapper slotMapper, TaskRepository taskRepository) {
        this.slotRepository = slotRepository;
        this.slotMapper = slotMapper;
        this.taskRepository = taskRepository;
    }

    public SlotDTO createSlot(SlotDTO slotDTO) {
        Task task = taskRepository.findById(slotDTO.getTaskId())
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        Slot slot = slotMapper.toEntity(slotDTO);
        slot.setTask(task);

        Slot saved = slotRepository.save(slot);
        return slotMapper.toDTO(saved);
    }
}
