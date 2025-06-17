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
    private List<UserDTO> users;

    public TaskDTO() {
        //
    }

    public TaskDTO(Long id, String codice, String nome, String descrizione, String dataInizio, Integer durata, List<SlotDTO> slots, List<UserDTO> users) {
        this.id = id;
        this.codice = codice;
        this.nome = nome;
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
        this.durata = durata;
        this.slots = slots;
        this.users = users;
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

    public List<SlotDTO> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotDTO> slots) {
        this.slots = slots;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
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
                ", users=" + users +
                '}';
    }
}
