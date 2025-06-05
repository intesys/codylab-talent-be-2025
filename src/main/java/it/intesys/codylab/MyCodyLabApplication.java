package it.intesys.codylab;

import it.intesys.codylab.business.FormeGeometricheService;
import it.intesys.codylab.repository.DataSourceFactory;
import it.intesys.codylab.repository.SqlFormaGeometricaRepository;

public class MyCodyLabApplication {

    public static void main(String[] args) {
        FormeGeometricheService formeGeometricheService = new MyCodyLabApplication().initStampaFormeGeometricheUseCase();

        // Salva una nuova forma geometrica
        formeGeometricheService.save("rettangolo", 5.0, 3.0);
        formeGeometricheService.save("quadrato", 4.0,  null);
        formeGeometricheService.save("cerchio", 2.0, null);

        // Trova per ID
        formeGeometricheService.findById(1);

        // Trova per nome
        formeGeometricheService.findByNome("quadrato");

        // Stampa tutte le forme geometriche (con perimetro e area)
        formeGeometricheService.stampaFormeGeometriche();

        //Aggiorna una forma geometrica
        formeGeometricheService.update(1, 2, 1);
    }

    private FormeGeometricheService initStampaFormeGeometricheUseCase() {
        return new FormeGeometricheService(new SqlFormaGeometricaRepository(DataSourceFactory.makeDataSource()));
    }
}
