package it.intesys.codylab.dto;

import java.time.LocalDate;

public class SlotDTO {
    private Long id;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private Integer durata;

    public SlotDTO() {
        //
    }

    public SlotDTO(Long id, LocalDate dataInizio, LocalDate dataFine, Integer durata) {
        this.id = id;
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

    public void setDurata(Integer durata) {
        this.durata = durata;
    }
}