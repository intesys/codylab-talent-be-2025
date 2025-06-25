package it.intesys.codylab.mapper;

import it.intesys.codylab.api.model.SlotsApiDTO;
import it.intesys.codylab.dto.SlotDTO;
import it.intesys.codylab.model.Slot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface SlotMapper {
    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "dataFine", target = "dataFine", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    SlotDTO toDTO(Slot slot);

    @Mapping(source = "taskId", target = "task.id")
    @Mapping(source = "dataInizio", target = "dataInizio", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "dataFine", target = "dataFine",dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    Slot toEntity(SlotDTO slotDTO);

    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "dataInizio", target = "startTime", qualifiedByName = "localDateTimeToOffsetDateTime")
    @Mapping(source = "dataFine", target = "endTime", qualifiedByName = "localDateTimeToOffsetDateTime")
    SlotsApiDTO toSlotApiDTO(Slot slot);

    @Mapping(source = "taskId", target = "task.id")
    @Mapping(source = "startTime", target = "dataInizio", qualifiedByName = "offsetDateTimeToLocalDateTime")
    @Mapping(source = "endTime", target = "dataFine", qualifiedByName = "offsetDateTimeToLocalDateTime")
    Slot toSlotModel(SlotsApiDTO slotsApiDTO);

    // Conversion methods between LocalDateTime and OffsetDateTime
    @Named("localDateTimeToOffsetDateTime")
    default OffsetDateTime mapLocalDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.atOffset(ZoneOffset.UTC); // o altro offset se serve
    }

    @Named("offsetDateTimeToLocalDateTime")
    default LocalDateTime mapOffsetDateTimeToLocalDateTime(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) return null;
        return offsetDateTime.toLocalDateTime();
    }


}