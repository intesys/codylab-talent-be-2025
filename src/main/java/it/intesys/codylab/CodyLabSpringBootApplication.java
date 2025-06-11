package it.intesys.codylab;

import it.intesys.codylab.business.FormeGeometricheService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CodyLabSpringBootApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CodyLabSpringBootApplication.class, args);


        FormeGeometricheService service = context.getBean(FormeGeometricheService.class);


        service.stampaFormeGeometriche();
    }
}
