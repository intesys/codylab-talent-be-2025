package it.intesys.codylab.model;

import java.time.LocalDate;

public class Task {
    private Long id;

    private Long progetto_id;

    private String codice;

    private String nome;

    private String descrizione;

    private LocalDate data_inizio;

    private Integer durata;

    public Task(Long id, Long progetto_id, String codice, String nome, String descrizione, LocalDate data_inizio, Integer durata) {
        this.id = id;
        this.progetto_id = progetto_id;
        this.codice = codice;
        this.nome = nome;
        this.descrizione = descrizione;
        this.data_inizio = data_inizio;
        this.durata = durata;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProgettoId() {
        return progetto_id;
    }

    public void setProgettoId(Long progetto_id) {
        this.progetto_id = progetto_id;
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

    public LocalDate getDataInizio() {
        return data_inizio;
    }

    public void setDataInizio(LocalDate data_inizio) {
        this.data_inizio = data_inizio;
    }

    public Integer getDurata() {
        return durata;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", progetto_id=" + progetto_id +
                ", codice='" + codice + '\'' +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", data_inizio=" + data_inizio +
                ", durata=" + durata +
                '}';
    }
}
