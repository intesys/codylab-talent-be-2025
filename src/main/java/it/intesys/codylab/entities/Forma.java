package it.intesys.codylab.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "formageometrica")
public class Forma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String tipo;

    @Column
    private Double lato1;

    @Column
    private Double lato2;

    public Forma() {}

    public Forma(Long id, String tipo, Double lato1, Double lato2) {
        this.id = id;
        this.tipo = tipo;
        this.lato1 = lato1;
        this.lato2 = lato2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getLato1() {
        return lato1;
    }

    public void setLato1(Double lato1) {
        this.lato1 = lato1;
    }

    public Double getLato2() {
        return lato2;
    }

    public void setLato2(Double lato2) {
        this.lato2 = lato2;
    }
}
