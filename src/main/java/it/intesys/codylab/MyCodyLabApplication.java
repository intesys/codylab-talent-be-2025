package it.intesys.codylab;

import it.intesys.codylab.business.FormeGeometricheService;
import it.intesys.codylab.repository.DataSourceFactory;
import it.intesys.codylab.repository.SqlFormaGeometricaRepository;

public class MyCodyLabApplication {

    public static void main(String[] args) {
        FormeGeometricheService formeGeometricheService = new MyCodyLabApplication().initStampaFormeGeometricheUseCase();

        // Salva una nuova forma geometrica
        formeGeometricheService.save("rettangolo", 5.0, 3.0);
        formeGeometricheService.save("quadrato", 4.0, 4.0);
        formeGeometricheService.save("cerchio", 2.0, null); // lato2 null se inutile

        // Trova per ID
        formeGeometricheService.findById(1);

        // Trova per nome
        formeGeometricheService.findByNome("quadrato");

        // Stampa tutte le forme geometriche (con perimetro e area)
        formeGeometricheService.stampaFormeGeometriche();

        // Elimina una forma geometrica per nome
        formeGeometricheService.deleteByNome("cerchio");

        //Aggiorna una forma geometrica
        formeGeometricheService.update(1, 2, 1);
    }

    private FormeGeometricheService initStampaFormeGeometricheUseCase() {
        return new FormeGeometricheService(new SqlFormaGeometricaRepository(DataSourceFactory.makeDataSource()));
    }
}
