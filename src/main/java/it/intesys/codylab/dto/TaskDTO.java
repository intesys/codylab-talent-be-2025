package it.intesys.codylab.dto;

import java.time.LocalDate;
import java.util.List;

public class TaskDTO {

    private List<UserDTO> users;
    private Long id;
    private String codice;
    private String nome;
    private String descrizione;
    private LocalDate dataInizio;
    private Integer durata;

    private Long projectId;


    private List<SlotDTO> slots;


    public TaskDTO() {}

    public TaskDTO(Long id, String codice, String nome, String descrizione, LocalDate dataInizio, Integer durata, Long projectId, List<SlotDTO> slots, List<UserDTO> users) {
        this.id = id;
        this.codice = codice;
        this.nome = nome;
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
        this.durata = durata;
        this.projectId = projectId;
        this.slots = slots;
        this.users = users;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodice() { return codice; }
    public void setCodice(String codice) { this.codice = codice; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public LocalDate getDataInizio() { return dataInizio; }
    public void setDataInizio(LocalDate dataInizio) { this.dataInizio = dataInizio; }

    public Integer getDurata() { return durata; }
    public void setDurata(Integer durata) { this.durata = durata; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public List<SlotDTO> getSlots() { return slots; }
    public void setSlots(List<SlotDTO> slots) { this.slots = slots; }
}
