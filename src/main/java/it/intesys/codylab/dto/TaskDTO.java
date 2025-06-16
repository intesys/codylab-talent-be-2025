package it.intesys.codylab.dto;

import java.util.List;

public class TaskDTO {

    private Long id;
    private String codice;
    private String nome;
    private String descrizione;
    private String dataInizio;
    private Integer durata;
    private List<SlotDTO> slots;

    public TaskDTO() {
        // Default constructor
    }

    public TaskDTO(Long id, String codice, String nome, String descrizione, String dataInizio, Integer durata, List<SlotDTO> slots) {
        this.id = id;
        this.codice = codice;
        this.nome = nome;
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
        this.durata = durata;
        this.slots = slots;
    }

    public List<SlotDTO> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotDTO> slots) {
        this.slots = slots;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Integer getDurata() {
        return durata;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", codice='" + codice + '\'' +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", dataInizio='" + dataInizio + '\'' +
                ", durata=" + durata +
                ", slots=" + slots +
                '}';
    }
}