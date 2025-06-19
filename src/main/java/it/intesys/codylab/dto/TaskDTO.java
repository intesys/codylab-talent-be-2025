package it.intesys.codylab.dto;

import java.time.LocalDate;
import java.util.List;

public class TaskDTO {
    private Long id;
    private String codice;
    private String nome;
    private String descrizione;
    private LocalDate dataInizio;
    private Integer durata;
    private Long projectId;
    private List<SlotDTO> slots;
    private List<Long> userIds;

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
    public List<Long> getUserIds() { return userIds; }
    public void setUserIds(List<Long> userIds) { this.userIds = userIds; }
}
