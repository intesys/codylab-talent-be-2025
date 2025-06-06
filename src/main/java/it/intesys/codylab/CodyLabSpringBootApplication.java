package it.intesys.codylab;
import it.intesys.codylab.business.FormeGeometricheService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodyLabSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodyLabSpringBootApplication.class, args);
        MyCodyLabApplication.main(new String[0]);
    }

}

