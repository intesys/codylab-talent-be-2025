package it.intesys.codylab;

import it.intesys.codylab.business.FormeGeometricheService;
import it.intesys.codylab.repository.DataSourceFactory;
import it.intesys.codylab.repository.SqlFormaGeometricaRepository;

public class MyCodyLabApplication {

    public static void main(String[] args) {
        FormeGeometricheService formeGeometricheService = new MyCodyLabApplication().initStampaFormeGeometricheUseCase();
        //formeGeometricheService.stampaFormeGeometriche();
//        formeGeometricheService.findById(3);
        formeGeometricheService.findByNome("quadrato");
    }

    private FormeGeometricheService initStampaFormeGeometricheUseCase() {
        return new FormeGeometricheService(new SqlFormaGeometricaRepository(DataSourceFactory.makeDataSource()));
        //return new StampaFormeGeometricheUseCase(new DummyFormaGeometricaRepository());
    }
//creato nuovo branch
}
