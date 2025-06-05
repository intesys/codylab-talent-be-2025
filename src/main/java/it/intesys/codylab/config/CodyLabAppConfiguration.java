package it.intesys.codylab.config;

import it.intesys.codylab.business.FormeGeometricheService;
import it.intesys.codylab.repository.DataSourceFactory;
import it.intesys.codylab.repository.DummyFormaGeometricaRepository;
import it.intesys.codylab.repository.FormaGeometricaRepository;
import it.intesys.codylab.repository.SqlFormaGeometricaRepository;
import jdk.jfr.Name;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CodyLabAppConfiguration {

    @Bean
    @Qualifier("sqlRepository")
    public FormaGeometricaRepository createFormaGeometricaRepository() {
        System.out.println("Creating SqlFormaGeometricaRepository");
        return new SqlFormaGeometricaRepository(DataSourceFactory.makeDataSource());
    }
    @Bean
    @Qualifier("dummyRepository")
    public FormaGeometricaRepository createDummyFormaGeometricaRepository() {
        System.out.println("Creating DummyFormaGeometricaRepository");
        return new DummyFormaGeometricaRepository();
    }

    @Bean
    public FormeGeometricheService getFormeGeometriceService(@Qualifier("sqlRepository")
                                                                 FormaGeometricaRepository formaGeometricaRepository) {
        System.out.println("getFormeGeometriceService con repo: " + formaGeometricaRepository);
        return new FormeGeometricheService(formaGeometricaRepository);
    }
}
