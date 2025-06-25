package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.SlotsApiDTO;
import it.intesys.codylab.dto.SlotDTO;
import it.intesys.codylab.model.Slot;
import it.intesys.codylab.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import it.intesys.codylab.api.model.SlotsApiDTO;
import it.intesys.codylab.dto.SlotDTO;
import it.intesys.codylab.model.Slot;
import it.intesys.codylab.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface SlotMapper {

    // ENTITY -> DTO (servizio)
    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "dataFine", target = "dataFine", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    SlotDTO toDTO(Slot slot);

    // DTO (servizio) -> ENTITY
    @Mapping(source = "taskId", target = "task")  // il Long taskId diventa Task con id
    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "dataFine", target = "dataFine", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    Slot toEntity(SlotDTO slotDTO);

    // ENTITY -> API DTO
    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "dataInizio", target = "startTime", qualifiedByName = "localDateTimeToOffsetDateTime")
    @Mapping(source = "dataFine", target = "endTime", qualifiedByName = "localDateTimeToOffsetDateTime")
    SlotsApiDTO toSlotApiDTO(Slot slot);

    // API DTO -> ENTITY
    @Mapping(source = "taskId", target = "task")
    @Mapping(source = "startTime", target = "dataInizio", qualifiedByName = "offsetDateTimeToLocalDateTime")
    @Mapping(source = "endTime", target = "dataFine", qualifiedByName = "offsetDateTimeToLocalDateTime")
    Slot toSlotModel(SlotsApiDTO slotsApiDTO);

    // Se vuoi convertire API DTO direttamente in DTO servizio (opzionale)
    @Mapping(source = "taskId", target = "taskId")
    @Mapping(source = "startTime", target = "dataInizio", qualifiedByName = "offsetDateTimeToLocalDateTime")
    @Mapping(source = "endTime", target = "dataFine", qualifiedByName = "offsetDateTimeToLocalDateTime")
    SlotDTO toSlotDTO(SlotsApiDTO slotsApiDTO);

    // Helper per creare Task da Long taskId
    default Task map(Long taskId) {
        if (taskId == null) return null;
        Task task = new Task();
        task.setId(taskId);
        return task;
    }

    @Named("localDateTimeToOffsetDateTime")
    default OffsetDateTime mapLocalDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.atOffset(ZoneOffset.UTC);
    }

    @Named("offsetDateTimeToLocalDateTime")
    default LocalDateTime mapOffsetDateTimeToLocalDateTime(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) return null;
        return offsetDateTime.toLocalDateTime();
    }
}

