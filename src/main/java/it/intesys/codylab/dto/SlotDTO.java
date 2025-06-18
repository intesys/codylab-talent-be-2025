package it.intesys.codylab.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class SlotDTO {

    private Long id;
    private Long taskId;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInizio;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFine;
    private Integer durata;

    public SlotDTO() {
        // Default constructor
    }

    public SlotDTO(Long id,Long taskId ,LocalDate dataInizio, LocalDate dataFine, Integer durata) {
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

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
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