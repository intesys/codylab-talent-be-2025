package it.intesys.codylab.mapper;

import it.intesys.codylab.dto.TaskDTO;
import it.intesys.codylab.mapper.SlotMapper;
import it.intesys.codylab.model.Slot;
import it.intesys.codylab.model.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    private final SlotMapper slotMapper;

    public TaskMapper(SlotMapper slotMapper) {
        this.slotMapper = slotMapper;
    }

    public Task toEntity(TaskDTO dto) {
        Task task = new Task();
        task.setCodice(dto.getCodice());
        task.setNome(dto.getNome());
        task.setDescrizione(dto.getDescrizione());
        task.setDataInizio(dto.getDataInizio());
        task.setDurata(dto.getDurata());

        if (dto.getSlots() != null) {
            List<Slot> slots = dto.getSlots()
                    .stream()
                    .map(slotMapper::toEntity)
                    .collect(Collectors.toList());
            slots.forEach(slot -> slot.setTask(task));
            task.setSlots(slots);
        }

        return task;
    }

    public TaskDTO toDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setCodice(task.getCodice());
        dto.setNome(task.getNome());
        dto.setDescrizione(task.getDescrizione());
        dto.setDataInizio(task.getDataInizio());
        dto.setDurata(task.getDurata());
        dto.setProjectId(task.getProject() != null ? task.getProject().getId() : null);

        if (task.getSlots() != null) {
            dto.setSlots(task.getSlots()
                    .stream()
                    .map(slotMapper::toDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}
