package it.intesys.codylab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private Long id;
    private String nome;
    private String cognome;
    private String mail;
    private String profilo;
    private Double orarioGiornaliero;
}
