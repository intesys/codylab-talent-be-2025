package it.intesys.codylab.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cognome;

    private String username;

    private String mail;

    private String profilo;

    private Double orarioGiornaliero;

    @ManyToMany
    @JoinTable(
            name = "users_tasks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Task> tasks;


    @OneToMany(mappedBy = "responsabile", fetch = FetchType.LAZY)
    private List<Project> projects;

    public User() {
        // Default constructor
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getProfilo() {
        return profilo;
    }

    public void setProfilo(String profilo) {
        this.profilo = profilo;
    }

    public Double getOrarioGiornaliero() {
        return orarioGiornaliero;
    }

    public void setOrarioGiornaliero(Double orarioGiornaliero) {
        this.orarioGiornaliero = orarioGiornaliero;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }



}