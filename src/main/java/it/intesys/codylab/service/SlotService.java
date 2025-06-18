package it.intesys.codylab.service;

import it.intesys.codylab.dto.SlotDTO;
import it.intesys.codylab.mapper.SlotMapper;
import it.intesys.codylab.model.Slot;
import it.intesys.codylab.model.Task;
import it.intesys.codylab.repository.SlotRepository;
import it.intesys.codylab.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SlotService {

    private SlotRepository slotRepository;
    private TaskRepository taskRepository; // devi aggiungerlo
    private SlotMapper slotMapper;

    public SlotService(SlotRepository slotRepository, TaskRepository taskRepository, SlotMapper slotMapper) {
        this.slotRepository = slotRepository;
        this.taskRepository = taskRepository;
        this.slotMapper = slotMapper;
    }

    public SlotDTO save(SlotDTO slotDTO) {
        Slot slot = slotMapper.toEntity(slotDTO);


        Long taskId = slotDTO.getTaskId();
        if (taskId == null) {
            throw new IllegalArgumentException("taskId must not be null");
        }

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + taskId));
        slot.setTask(task);


        Slot saved = slotRepository.save(slot);
        return slotMapper.toDTO(saved);
    }

    public SlotDTO getSlotById(Long id) {
        return slotMapper.toDTO(slotRepository.findById(id).orElse(null));
    }


    public List<SlotDTO> findAll() {
        return StreamSupport.stream(slotRepository.findAll().spliterator(), false)
                .map(slotMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        slotRepository.deleteById(id);
    }
}