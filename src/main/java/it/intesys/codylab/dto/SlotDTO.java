package it.intesys.codylab.dto;

public class SlotDTO {

    private Long id;
    private String dataInizio;
    private String dataFine;
    private Integer durata;

    public SlotDTO() {
        // Default constructor
    }

    public SlotDTO(Long id, String dataInizio, String dataFine, Integer durata) {
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

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    public Integer getDurata() {
        return durata;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }
}