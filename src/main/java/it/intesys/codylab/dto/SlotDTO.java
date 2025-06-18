package it.intesys.codylab.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.intesys.codylab.deserializer.SlotDateTimeDeserializer;
import it.intesys.codylab.serializer.SlotDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SlotDTO {

    private Long id;
    private Long taskId;
    @JsonSerialize(using = SlotDateTimeSerializer.class)
    @JsonDeserialize(using = SlotDateTimeDeserializer.class)
    private LocalDateTime dataInizio;

    @JsonSerialize(using = SlotDateTimeSerializer.class)
    @JsonDeserialize(using = SlotDateTimeDeserializer.class)
    private LocalDateTime dataFine;
    private Integer durata;

    public SlotDTO() {
        // Default constructor
    }

    public SlotDTO(Long id,Long taskId ,LocalDateTime dataInizio, LocalDateTime dataFine, Integer durata) {
        this.id = id;
        this.taskId = taskId;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.durata = durata;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public Integer getDurata() {
        return durata;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }
    @Override
    public String toString() {
        return "SlotDTO{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", durata=" + durata +
                '}';
    }
}