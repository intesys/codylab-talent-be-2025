package it.intesys.codylab;

import it.intesys.codylab.business.FormeGeometricheService;
import it.intesys.codylab.repository.DataSourceFactory;
import it.intesys.codylab.repository.SqlFormaGeometricaRepository;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCodyLabApplication {
    static Scanner s = new Scanner(System.in);
    private static final Logger log = LoggerFactory.getLogger(SqlFormaGeometricaRepository.class);

    public static void main(String[] args) {
        FormeGeometricheService formeGeometricheService = new MyCodyLabApplication().initStampaFormeGeometricheUseCase();
        log.info("Inserisci l'operazione che vuoi eseguire:");
        String menu = ("\n 1) stampa forme geometriche\n 2) findById\n 3) findByString\n 4) save\n 5) deleteById\n 6) Salva\n-1) exit");
        log.info(menu);
        int scelta = s.nextInt();
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

                    case 3: log.info("Inserisci il nome della forma geometrica da trovare");
                        String nome = s.next();
                        formeGeometricheService.findByString(nome);
                        log.info(menu);
                        scelta = s.nextInt();
                        break;

                    case 4: log.info("Inserisci il tipo della forma geometrica da salvare");
                        String tipo = s.next();
                        log.info("Inserisci il lato 1 della forma geometrica da salvare");
                        Double lato1 = s.nextDouble();
                        log.info("Inserisci il lato 2 della forma geometrica da salvare");
                        Double lato2 = s.nextDouble();
                        formeGeometricheService.save(tipo, lato1, lato2);
                        log.info(menu);
                        scelta = s.nextInt();
                        break;

                    case 5: log.info("Inserisci l'id della forma geometrica da eliminare");
                        id = s.nextInt();
                        formeGeometricheService.deleteById(id);
                        log.info(menu);
                        scelta = s.nextInt();
                        break;

                    case 6: log.info("Inserisci l'id della forma geometrica da aggiornare");
                        id = s.nextInt();
                        log.info("Inserisci il lato 1 della forma geometrica da aggiornare");
                        lato1 = s.nextDouble();
                        log.info("Inserisci il lato 2 della forma geometrica da aggiornare");
                        lato2 = s.nextDouble();
                        formeGeometricheService.update(id, lato1, lato2);
                        log.info(menu);
                        scelta = s.nextInt();
                        break;

                    case -1:
                        log.info("Programma terminato");
                        break;

                    default:
                        log.info("Scelta non valida");
                        log.info(menu);
                        scelta = s.nextInt();
                        break;
            }

        }

    }

    private FormeGeometricheService initStampaFormeGeometricheUseCase() {
        return new FormeGeometricheService(new SqlFormaGeometricaRepository(DataSourceFactory.makeDataSource()));
        //return new StampaFormeGeometricheUseCase(new DummyFormaGeometricaRepository());
    }

}
