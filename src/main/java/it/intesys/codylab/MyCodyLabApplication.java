package it.intesys.codylab;

import it.intesys.codylab.business.FormeGeometricheService;
import it.intesys.codylab.config.CodyLabDatasourceProperties;
import it.intesys.codylab.repository.DataSourceFactory;
import it.intesys.codylab.repository.SqlFormaGeometricaRepository;

public class MyCodyLabApplication {

    public static void main(String[] args) {
        FormeGeometricheService formeGeometricheService = new MyCodyLabApplication().initStampaFormeGeometricheUseCase();
        //formeGeometricheService.stampaFormeGeometriche();
        formeGeometricheService.findById(3);
    }

    private FormeGeometricheService initStampaFormeGeometricheUseCase() {
        return new FormeGeometricheService(new SqlFormaGeometricaRepository(DataSourceFactory.makeDataSource(initPostgresqlProperties())));
        //return new StampaFormeGeometricheUseCase(new DummyFormaGeometricaRepository());
    }

    private CodyLabDatasourceProperties initPostgresqlProperties() {
        CodyLabDatasourceProperties result = new CodyLabDatasourceProperties();
        result.setUrl("jdbc:postgresql://localhost:5432/formegeometriche");
        result.setUsername("codylab");
        result.setPassword("cody|_ab2025");
        result.setDriver("org.postgresql.Driver");
        return result;
    }

    private CodyLabDatasourceProperties initH2Properties() {
        CodyLabDatasourceProperties result = new CodyLabDatasourceProperties();
        result.setUrl("jdbc:h2:~/codylab-2025;AUTO_SERVER=TRUE");
        result.setUsername("sa");
        result.setPassword("password");
        result.setDriver("org.h2.Driver");
        return result;
    }

}
