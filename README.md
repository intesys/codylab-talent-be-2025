# CodyLab Talent 2025 - Progetto Java e Spring Boot
Progetto studio java e Spring Boot per CodyLab Talent 2025

## Parte 1 - Java Intro
Eseguire il check-out del progetto https://github.com/intesys/codylab-talent-be-2025.git

Per poter lavorare da IDE con progetto agganciato in https vi occorre il token.
Sulla pagina di github, sul vostro profilo, andate su `Settings > Developer Settings`
e generate un personal access token (classic) dando i permessi `repo` e `workflow`.

### Argomenti
* Java e Javac (JDK e JRE)
* Programma Java => metodo main (`public static void main(String[] args)`)
* In Java tutto è un Oggetto
* Concetto di Classe, cosa definisce
* Interfacce, classi astratte e classi concrete
* private, protected, public
* variabili e costanti (costruttore, setter e getter)
* final
* metodi statici
* Ereditarietà da Object (e java.lang)
* toString
* Principi della programmazione ad oggetti:
  * _Incapsulamento_
    Raggruppa dati e comportamenti all’interno di un oggetto.
    Nasconde i dettagli interni e espone solo ciò che serve tramite interfacce pubbliche.
  * _Astrazione_
    Espone solo gli aspetti rilevanti di un oggetto, nascondendo i dettagli complessi.
    Permette di lavorare con concetti generali, non implementazioni specifiche.
  * _Ereditarietà_
    Una classe può ereditare attributi e metodi da un'altra.
    Favorisce il riuso del codice e la specializzazione.
  * _Polimorfismo_
    Un'unica interfaccia può rappresentare comportamenti diversi.
    Permette di usare oggetti di classi diverse in modo uniforme, purché condividano un'interfaccia comune.
* Introduzione principi `SOLID`
  * _S – Single Responsibility Principle (SRP)_
    Ogni classe dovrebbe avere una sola responsabilità, cioè una sola ragione di cambiare.
  * _O – Open/Closed Principle (OCP)_
    Il software dovrebbe essere aperto all'estensione ma chiuso alla modifica.
    Puoi aggiungere nuove funzionalità senza modificare il codice esistente.
  * _L – Liskov Substitution Principle (LSP)_
    Le classi derivate devono poter sostituire le classi base senza alterare il funzionamento del programma.
    In pratica, un oggetto figlio deve comportarsi come il padre.
  * _I – Interface Segregation Principle (ISP)_
    Meglio avere tante interfacce specifiche che una sola generica.
    I client non dovrebbero dipendere da metodi che non usano.
  * _D – Dependency Inversion Principle (DIP)_
    Dipendi da astrazioni, non da classi concrete.
    I moduli di alto livello non dovrebbero dipendere da quelli di basso livello: entrambi dovrebbero dipendere da astrazioni.
* Liste, stream, interfacce funzionali

**Esercizi**
Scaricare il branch `1-java-intro` e verificare quanto fatto

Ogni studente lavora sul proprio branch:
* `1-java-jacopo`
* `1-java-pietro`
* `1-java-gaetano`

Esercizi da completare:
* Creare una classe MyCodyLabApplication che implementa il metodo `main` che stampa a console "Hello CodyLab Talent 2025"
* Creare una classe MyCodyLab che implementa il metodo `messaggio` che restituisce "Ciao"
* In MyCodyLabApplication, instanziare un oggetto MyCodyLab e chiamare il metodo `messaggio` per stampare a console il messaggio
* Creare l'interfaccia `FormaGeometrica` che definisce i metodi `perimetro` e `area`
* Creare la classe astratta `Quadrilatero`, che implementa `FormaGeometrica`,
  con un costruttore che riceve la misura dei 4 lati e implementa il metodo `perimetro` (l'aerea non è implementata)
* Creare la classe `Rettangolo` che estende `Quadrilatero`, sovrascrive il costruttore ricevendo i dati base e altezza,
  e implementa il metodo `area` (base * altezza); Sovrascrivere il metodo toString perché scriva "Sono un rettangolo con base X e altezza Y"
  (dove X e Y sono i valori dei lati)
* Creare la classe `Quadrato` che estende `Quadrilatero`, sovrascrive il costruttore ricevendo il lato, e implementa il metodo `area` (lato * lato);
  Sovrascrivere il metodo toString perché scriva "Sono un quadrato con lato X" (dove X è il valore del lato)
* Creare la classe Cerchio che implementa FormaGeometrica,
  con un costruttore che riceve il raggio e implementa i metodi `perimetro` (2 * Math.PI * raggio) e `area` (Math.PI * raggio * raggio);
  Sovrascrivere il metodo toString perché scriva "Sono un cerchio con raggio X" (dove X è il valore del raggio)
* Nel `main` di MyCodyLabApplication creare un metodo `stampaForme` che riceve una lista di `FormaGeometrica`
  e stampa a console quale è l'oggetto corrente (toString), il perimetro e l'area di ogni forma; richiamare questo metodo
  con una lista di forme geometriche che contiene un rettangolo, un quadrato e un cerchio da main.

## Parte 2 - Maven + Layer
Scaricare il branch `2-java-maven`
### Argomenti
* Progetto maven
  * pom.xml
  * struttura cartelle
  * dipendenze
* Repository Maven
  * Maven Central
  * Repository locali e remoti
* Ciclo di vita di un progetto
  * clean
  * compile
  * test
  * package
  * deploy
* I test automatizzati
  * JUnit
  * Mockito
* Basic pom.xml:
```xml
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>it.intesys.codylab</groupId>
  <artifactId>codylab-talent-2025</artifactId>
  <version>1.0-SNAPSHOT</version>
</project>
```
* Esecuzione di un eseguibile da linea di comando
  * java.exe -cp codylab-talent-2025-1.0-SNAPSHOT.jar it.intesys.codylab.MyCodyLabApplication
* Layer architetturali
* Repository (introduzione concetto)

**Esercizi**
Scaricare il branch `2-java-maven` e verificare quanto fatto.

Ogni studente lavora sul proprio branch:
* `2-java-maven-jacopo`
* `2-java-maven-pietro`
* `2-java-maven-gaetano`

Esercizi da completare:
* Trasformare il progetto in un progetto maven (pom.xml + struttura cartelle).
  Dire all'IDE che il progetto è un progetto maven:
  ![addMavenProject.png](addMavenProject.png)
* Creare un test JUnit per Quadrato che verifica il calcolo di perimetro ed area:
``` java
package it.intesys.codylab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuadratoTest {

    @Test
    void testArea() {
        Quadrato quadrato = new Quadrato(5);
        assertEquals(25, quadrato.area(), "L'area del quadrato con lato 5 dovrebbe essere 25");
    }

    @Test
    void testNumberEquals() {
        Long l1 = new Long("123455667");
        Long l2 = new Long("123455667");
//        Long l1 = 3L;
//        Long l2 = 3L;
assertTrue(l1.equals(l2));
}

}
```
* Eseguire una compilazione con maven
* Eseguire il  main dal jar
* Spostare le classi in package diversi
* Creare il package `repository`, creare l'interfaccia `FormaGeometricaRepository` che
  restituisce `java.util.List` di `FormaGeometrica`
* Installare Docker Desktop sul proprio pc
* Attivare AI Assistant su IDE
* ~~Avviare l'applicazione avviando anche un database h2~~ -> prossima volta
* ~~Modificare l'applicazione per fare in modo di leggere le forme geometriche da una tabella del database~~
  -> prossima volta

## Parte 3 - Database relazionali e SQL
### Argomenti
* Layer architetturali
* Repository
* Il database (h2) + collegamento a repository da IDE
* JDBC e Datasources
* Repository che legge da una tabella
* Database relazionali
* SQL
  * DDL (Data Definition Language)
    * CREATE, ALTER, DROP
  * DML (Data Manipulation Language)
    * INSERT, UPDATE, DELETE
  * DQL (Data Query Language)
    * SELECT
* Update e Delete da Java
* Punto di attenzione sulle sql injection

Esercizi da completare:
* Rivedere quanto fatto nel branch 3-java-sql
* Completare gli esercizi:
  * findByNome(String nome)
  * save(String tipo, double lato1, double lato2)

In aggiunta:
* Stesso codice per database diversi: avvio di Postgres su docker-compose e test applicazione

## Parte 4 - Introduzione a Spring Boot
### Argomenti
* Introduzione a Docker Desktop e docker compose
* Postgres su Docker Desktop tramite docker compose
* Applicazione running usando Postgres in locale
* Limiti del main
* Le applicazioni web
  * Server Side Rendering
  * Client Side Rendering
* Configurazioni
* Dependency Injection e Inversione of Control
* Maven: il parent e lo starter
* Web App Spring Boot
  * Configurazione con https://start.spring.io/
  * spring-boot-maven-plugin e flat jar
  * Come cambia il main
  * La configurazione
  * Controller, Service, Repository
  * Come cambia la lettura della configurazione
  * Inizializzazione dei Bean (Singleton)

**Esercizi**
Scaricare il branch `4-intro-spring-boot` e verificare quanto fatto.

Ogni studente lavora sul proprio branch:
* `4-intro-spring-boot-jacopo`
* `4-intro-spring-boot-pietro`
* `4-intro-spring-boot-gaetano`

Esercizi da completare
* Avviare Docker Desktop
* Avviare servizi tramite docker-compose:  `docker-compose -f .\docker-compose.yml up -d --build`
* Collegare l'ide al database postgresql e creare le tabelle
* Testare il main MyCodyLabApplion collegato alle tabelle postgresql su Docker
* Introdurre parent e Spring Boot 3.5.0 e dipendenza da spring-starter -> ricordarsi file logback-spring.xml per log
* Introdurre plugin per fat jar
* Creare un RestController come sul branch principale
* Collegare RestController a Service e Repository da configurazione Spring
* Introdurre i profili e la configurazione (application.yml)

## Parte 5 - Microservizi ed API
### Argomenti
* Revisione concetto profili di spring (es.: per h2 e postgres)
* Differenza tra Properties e Value per leggere la configurazione (validazione)
* Introduzione ai Microservizi (differenza con monolite e problemi scalabilità e integrazione)
* Il protocollo REST
* Comunicazione sincrona e codici HTTP (vedi https://openapi.it/blog/rest-api-status-codes)
* Le risorse
* Creare API Rest con Spring Boot
* GET/POST/PUT/PATCH/DELETE (esempio con repo in memoria)
* idempotenza
* OpenAPI (API Last, API First)
* Generazione swagger -> specifica OpenAPI

**Esercizi**
Scaricare il branch `5-intro-microsrv` e verificare quanto fatto.

Lavorare sul branch che preferite (crearne anche in autonomia)

Esericizi da completare:
* creare 2 profili: uno per h2 e uno per postgres
* Inserire la validazione nelle properties per la connessione al database (url, username, driver, NotNull)
* Creare il dto User con i campi id, nome, cognome, email, profilo, orario giornaliero (https://loop.cloud.microsoft/p/eyJ3Ijp7InUiOiJodHRwczovL2ludGVzeXMzNjUuc2hhcmVwb2ludC5jb20vP25hdj1jejBsTWtZbVpEMWlJVEUzZWtoVVJTMXhTMVV5V1d4MVNtUkRRVEpEZWxocWMxTk1SVzVNYVU1RmJuQnlSRWhaY1c5bVlUWm1UbDlKT1dwVmRHaFVTMDlhVVVreVVtSnNSRE1tWmowd01UZEZWMVZCV0VwSVNFUlBOa0ZETjFkSFRrRmFSVXhSVkVoYVREVlJWMFpWSm1NOUptWnNkV2xrUFRFJTNEIiwiciI6ZmFsc2V9LCJwIjp7InUiOiJodHRwczovL2ludGVzeXMzNjUuc2hhcmVwb2ludC5jb20vOmZsOi9yL2NvbnRlbnRzdG9yYWdlL0NTUF80Y2M3YmNkNy1hYTRmLTRkMjktOTg5Ni1lMjVkMDgwZDgyY2QvUmFjY29sdGElMjBkb2N1bWVudGkvTG9vcEFwcERhdGEvU2VuemElMjB0aXRvbG8ubG9vcD9kPXc2YTBiY2EyZTZlMWM0MjhlODI3ODFlZjE5MGFjMjdiNSZjc2Y9MSZ3ZWI9MSZuYXY9Y3owbE1rWmpiMjUwWlc1MGMzUnZjbUZuWlNVeVJrTlRVRjgwWTJNM1ltTmtOeTFoWVRSbUxUUmtNamt0T1RnNU5pMWxNalZrTURnd1pEZ3lZMlFtWkQxaUlURTNla2hVUlMxeFMxVXlXV3gxU21SRFFUSkRlbGhxYzFOTVJXNU1hVTVGYm5CeVJFaFpjVzltWVRabVRsOUpPV3BWZEdoVVMwOWFVVWt5VW1Kc1JETW1aajB3TVRkRlYxVkJXRXBQV2tsR1YxVklSRTlTV2tKSlJUWkJOalpIU1V0WlNqVldKbU05SlRKR0ptWnNkV2xrUFRFbVlUMU1iMjl3UVhCd0puQTlKVFF3Wm14MWFXUjRKVEpHYkc5dmNDMXdZV2RsTFdOdmJuUmhhVzVsY2laNFBTVTNRaVV5TW5jbE1qSWxNMEVsTWpKVU1GSlVWVWg0Y0dKdVVteGpNMng2VFhwWk1VeHVUbTlaV0Vwc1kwYzVjR0p1VVhWWk1qbDBaa2RKYUUxVVpEWlRSbEpHVEZoR1RGWlVTbHBpU0ZaTFdrVk9RazFyVGpaWFIzQjZWVEI0Um1KcmVIQlVhMVoxWTBoS1JWTkdiSGhpTWxwb1RtMWFUMWd3YXpWaGJGWXdZVVpTVEZReGNGSlRWRXBUV1cxNFJVMHpkM2ROVkdSR1ZqRldRbGRGY0VsVFJWSlFUbXRHUkU0eFpFaFVhMFpoVWxWNFVsWkZhR0ZVUkZaU1ZqQmFWaVV5TWlVeVF5VXlNbWtsTWpJbE0wRWxNakkxT0RWa1pEbGlOaTFsTjJVd0xUUTVZVEl0T0RNd015MWtNVGt5T1RGaU1qWmxZallsTWpJbE4wUSUzRCIsInIiOmZhbHNlfSwiaSI6eyJpIjoiNTg1ZGQ5YjYtZTdlMC00OWEyLTgzMDMtZDE5MjkxYjI2ZWI2In19)
* Creare repository e service per gestire le CRUD (create, read, update, delete) di User: a piacimento se su db sql o 
db in memoria
* Creare un controller REST per gestire la risorsa `User`
  * GET /users -> restutuisce una lista di utenti
  * GET /users/{id} -> restituisce un utente per id. {id} è un Path Variable
  * POST /users -> crea un nuovo utente
  * PUT /users/{id} -> aggiorna un utente esistente
  * DELETE /users/{id} -> elimina un utente per id
  * PATCH /users/{id}/workingHours -> aggiorna orario utente
  * PATCH /users/{id}/profile -> aggiorna profilo utente
  * Provare GET /users passando dei parametri di ricerca (es.: profilo), query parameters
* Installare postman e creare una richiesta per ciascun metodo rest implementato
* Agganciare la dipendenza openapi e generare swagger (http://localhost:8090/swagger-ui.html) 
e specifica openapi http://localhost:8090/v3/api-docs
* La specifica POST openapi rispetta le regole RESTful?
* Provare a trasformare la POST di User in modo da specificare lo status code 201 Created e il body della risposta
con l'utente creato (invece di 200 OK e body vuoto). Se possibile popolare anche l'header `Location` 
con l'url dell'utente creato (provare usando ResponseEntity come response del metodo nel Controller)

## Parte 6 - Spring MVC e Accesso ai dati
### Argomenti
* Pagina web con Spring MVC
* Spring JDBC Template
* Hibernate e Spring JPA
* CRUD, DTO e Mapper
* Pagina web con dati dinamici

## Parte 7 - API First
### Argomenti
* Leggere e scrivere la specifica OpenAPI
* Scrittura specifica OpenAPI
* Generazione del controller REST dalla specifica OpenAPI

## Parte 8 - Testing
### Argomenti
* Unit Test

## Parte 9 - Test di integrazione
### Argomenti
* Integration test

## Parte 10 - Sicurezza
### Argomenti
* Autenticazione e Autorizzazione
* Ruoli e permessi
* Json Web Token (JWT)
* Spring Security

## Parte 11 - DevOps
### Argomenti
* Docker
* Dockerfile
* Docker Compose
* Kubernetes su Docker

## Parte 12 - Comunicazioni Asincrone
### Argomenti
* Thread e Task
* Comunicazioni asincrone tra microservizi
* Eventi
