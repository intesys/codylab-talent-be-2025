package it.intesys.codylab.dto;

public class UserDTO {

    private Long id;
    private String nome;
    private String cognome;
    private String mail;
    private String profilo;
    private Double orarioGiornaliero;

    public UserDTO() {
        // Default constructor
    }

    public UserDTO(Long id, String nome, String cognome, String mail, String profilo, Double orarioGiornaliero) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.profilo = profilo;
        this.orarioGiornaliero = orarioGiornaliero;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", mail='" + mail + '\'' +
                ", profilo='" + profilo + '\'' +
                ", orarioGiornaliero=" + orarioGiornaliero +
                '}';
    }
}