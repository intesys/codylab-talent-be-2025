# CodyLab Talent 2025 - Progetto Java e Spring Boot
Progetto studio java e Spring Boot per CodyLab Talent 2025

## Parte 1 - Java Intro 
Eseguire il check-out del progetto https://github.com/intesys/codylab-talent-be-2025.git
Scaricare il branch `1-java-intro` e verificare quanto fatto

Ogni studente lavora sul proprio branch:
* `1-java-jacopo`
* `1-java-pietro`
* `1-java-gaetano`

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

## Parte 2 
Scaricare il branch `2-java-maven`
### Argomenti
* Progetto maven
  * pom.xml
  * struttura cartelle
  * dipendenze
* I test automatizzati
  * JUnit
  * Mockito
* Ciclo di vita di un progetto
  * clean
  * compile
  * test
  * package

**Esercizi**
* Trasformare il progetto in un progetto maven (pom.xml + struttura cartelle)
* Creare un test JUnit per Quadrato che verifica il calcolo di perimetro ed area
* Creare un test JUnit per Rettangolo che verifica il calcolo di perimetro ed area
* Creare un test JUnit per Cerchio che verifica il calcolo di perimetro ed area
* Eseguire una compilazione con maven