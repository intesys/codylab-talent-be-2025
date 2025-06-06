package it.intesys.codylab.config;

import it.intesys.codylab.business.FormeGeometricheService;
import it.intesys.codylab.repository.DataSourceFactory;
import it.intesys.codylab.repository.FormaGeometricaRepository;
import it.intesys.codylab.repository.SqlFormaGeometricaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CodyLabAppConfiguration {

    @Bean
    public FormaGeometricaRepository createFormaGeometricaRepository() {
        System.out.println("Create Forma Geometrica Repository");
        return new SqlFormaGeometricaRepository(DataSourceFactory.makeDataSource());
    }

    //@Bean
    public FormeGeometricheService getFormeGeometricheService(FormaGeometricaRepository formaGeometricaRepository) {
        System.out.println("Get Forme Geometrice Service");
        return new FormeGeometricheService(formaGeometricaRepository);
    };
}
