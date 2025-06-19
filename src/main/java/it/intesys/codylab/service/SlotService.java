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
    private final SlotRepository slotRepository;
    private final TaskRepository taskRepository;
    private final SlotMapper slotMapper;

    public SlotService(SlotRepository slotRepository, TaskRepository taskRepository, SlotMapper slotMapper) {
        this.slotRepository = slotRepository;
        this.taskRepository = taskRepository;
        this.slotMapper = slotMapper;
    }

    public SlotDTO create(SlotDTO dto) {
        Task task = taskRepository.findById(dto.getTaskId())
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        Slot slot = slotMapper.toEntity(dto);
        slot.setTask(task);

        Slot saved = slotRepository.save(slot);
        return slotMapper.toDTO(saved);
    }
}
