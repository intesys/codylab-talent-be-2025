package it.intesys.codylab;

import it.intesys.codylab.business.FormeGeometricheService;
import it.intesys.codylab.repository.DataSourceFactory;
import it.intesys.codylab.repository.SqlFormaGeometricaRepository;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static it.intesys.codylab.repository.DataSourceFactory.dataSource;

public class MyCodyLabApplication {
    static Scanner s = new Scanner(System.in);
    private static final Logger log = LoggerFactory.getLogger(SqlFormaGeometricaRepository.class);

    public static void main(String[] args) {
        FormeGeometricheService formeGeometricheService = new MyCodyLabApplication().initStampaFormeGeometricheUseCase();
        log.info("Benvenuto!");
        String menu = ("\n 1) stampa forme geometriche\n 2) Trova con ID\n 3) Trova con tipo\n 4) Elimina\n 5) Salva\n 6) Aggiorna\n 7) Elimina DB\n8) Cambia DB\n9) Aggiungi utente\n-1) Esci");
        int scelta = 0;
        Double lato1 = null;
        Double lato2 = null;
        while (scelta != -1) {
            switch (scelta) {
                case 1:
                    formeGeometricheService.stampaFormeGeometriche();
                    log.info(menu);
                    scelta = s.nextInt();
                    break;
                case 2:
                    log.info("Inserisci l'id della forma geometrica da trovare");
                    int id = s.nextInt();
                    formeGeometricheService.findById(id);
                    log.info(menu);
                    scelta = s.nextInt();
                    break;

                case 3:
                    log.info("Inserisci il nome della forma geometrica da trovare");
                    String nome = s.next();
                    formeGeometricheService.findByString(nome);
                    log.info(menu);
                    scelta = s.nextInt();
                    break;

                case 4:
                    log.info("Inserisci l'id della forma geometrica da eliminare");
                    id = s.nextInt();
                    formeGeometricheService.deleteById(id);
                    log.info(menu);
                    scelta = s.nextInt();
                    break;

                case 5:
                    log.error("ATTENZIONE: LE UNICHE FORME SUPPORTATE SONO CERCHIO, RETTANGOLO E QUADRATO.");
                    log.info("Inserisci il tipo della forma geometrica da salvare");
                    String tipo2 = s.next();
                    if (tipo2.equals("cerchio") || tipo2.equals("quadrato")) {
                        log.info("Inserisci il lato/raggio del {} da salvare", tipo2);
                        lato1 = s.nextDouble();
                        lato2 = null;
                        formeGeometricheService.save(tipo2, lato1, lato2);
                        log.info(menu);
                        scelta = s.nextInt();
                        break;
                    } else if (tipo2.equals("rettangolo")) {
                        log.info("Inserisci il lato 1 del rettangolo");
                        lato1 = s.nextDouble();
                        log.info("Inserisci il lato 2 del rettangolo");
                        lato2 = s.nextDouble();
                        formeGeometricheService.save(tipo2, lato1, lato2);
                        log.info(menu);
                        scelta = s.nextInt();
                        break;
                    } else {
                        log.error("TIPO NON SUPPORTATO, INSERISCI UNA NUOVA OPERAZIONE");
                        log.info(menu);
                        scelta = s.nextInt();
                        break;
                    }

                case 6:
                    log.info("Inserisci l'id della forma geometrica da aggiornare");
                    id = s.nextInt();
                    log.info("Inserisci il lato 1 della forma geometrica da aggiornare");
                    lato1 = s.nextDouble();
                    log.info("Inserisci il lato 2 della forma geometrica da aggiornare");
                    lato2 = s.nextDouble();
                    formeGeometricheService.update(id, lato1, lato2);
                    log.info(menu);
                    scelta = s.nextInt();
                    break;
                case 7:
                    log.info("Hai scelto di cancellare tutti i dati archiviati nel DB, sei sicuro di voler continuare?");
                    System.out.print("Inserisci 'y' per confermare, altrimenti inserisci 'n': ");
                    String risposta = s.next();
                    s.nextLine();
                    if (risposta.equalsIgnoreCase("y")) {
                        try {
                            formeGeometricheService.delDB();
                            System.out.println("Database eliminato e ricreato con successo!");
                            log.info(menu);
                            scelta = s.nextInt();
                            break;
                        } catch (RuntimeException e) {
                            System.err.println("Errore durante l'eliminazione/ricreazione del database: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Operazione annullata.");
                        log.info(menu);
                        scelta = s.nextInt();
                        break;
                    }

                case 8:
                    log.info("Inserisci il tipo del database da utilizzare: 1) H2, 2) PostgreSQL");
                    int db = s.nextInt();
                    formeGeometricheService = new FormeGeometricheService(
                            new SqlFormaGeometricaRepository(DataSourceFactory.makeDataSource(db))
                    );
                    log.info("Database cambiato correttamente.");
                    log.info(menu);
                    scelta = s.nextInt();
                    break;
                case 9:
                    s.nextLine();
                    log.info("Inserisci nome dell'utente");
                    String idUtente = s.nextLine();
                    log.info("Inserisci password dell'utente");
                    String password = s.nextLine();
                    formeGeometricheService.passCheck(idUtente, password);

                    log.info(menu);
                    scelta = s.nextInt();
                    break;

                case -1:
                    log.info("Programma terminato");
                    break;

                default:
                    log.info("Effettua una scelta dal menu");
                    log.info(menu);
                    scelta = s.nextInt();
                    break;
            }

        }

    }

    private FormeGeometricheService initStampaFormeGeometricheUseCase() {
        log.info("Inserisci il tipo del database da utilizzare: 1) H2, 2) PostgreSQL");
        int scelta = s.nextInt();
        return new FormeGeometricheService(new SqlFormaGeometricaRepository(DataSourceFactory.makeDataSource(scelta)));
        //return new StampaFormeGeometricheUseCase(new DummyFormaGeometricaRepository());
    }

}