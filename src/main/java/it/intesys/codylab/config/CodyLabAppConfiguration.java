package it.intesys.codylab.config;

import it.intesys.codylab.business.FormeGeometricheService;
import it.intesys.codylab.business.UserService;
import it.intesys.codylab.repository.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CodyLabAppConfiguration {

    @Bean
    //@Qualifier("sqlRepository")
    public FormaGeometricaRepository createFormaGeometricaRepository(CodyLabDatasourceProperties properties) {
        System.out.println("Creating SqlFormaGeometricaRepository " + properties);
        return new SqlFormaGeometricaRepository(DataSourceFactory.makeDataSource(properties));
    }
    //@Bean
    //@Qualifier("dummyRepository")
    public FormaGeometricaRepository createDummyFormaGeometricaRepository() {
        System.out.println("Creating DummyFormaGeometricaRepository");
        return new DummyFormaGeometricaRepository();
    }

    @Bean
    public FormeGeometricheService getFormeGeometriceService(FormaGeometricaRepository formaGeometricaRepository) {
        System.out.println("getFormeGeometriceService con repo: " + formaGeometricaRepository);
        return new FormeGeometricheService(formaGeometricaRepository);
    }

    @Bean
    public UserService userService() {
        return new UserService(new MemoryUserRepository());
    }
}