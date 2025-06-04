package it.intesys.codylab;

import it.intesys.codylab.business.FormeGeometricheService;
import it.intesys.codylab.repository.DataSourceFactory;
import it.intesys.codylab.repository.SqlFormaGeometricaRepository;

public class MyCodyLabApplication {

    public static void main(String[] args) {
        FormeGeometricheService formeGeometricheService = new MyCodyLabApplication().initStampaFormeGeometricheUseCase();
        //formeGeometricheService.stampaFormeGeometriche();
        formeGeometricheService.save("cerchio", 2.0, null);
    }

    private FormeGeometricheService initStampaFormeGeometricheUseCase() {
        return new FormeGeometricheService(new SqlFormaGeometricaRepository(DataSourceFactory.makeDataSource()));
    }

}
