package it.intesys.codylab.business;
import it.intesys.codylab.model.FormaGeometrica;
import it.intesys.codylab.repository.FormaGeometricaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormeGeometricheService {

    private final FormaGeometricaRepository formaGeometricaRepository;
    private static final Logger logger = LoggerFactory.getLogger(FormeGeometricheService.class);

    public FormeGeometricheService(FormaGeometricaRepository formaGeometricaRepository) {
        System.out.println("Creating FormeGeometriceService");
        this.formaGeometricaRepository = formaGeometricaRepository;
    }

    public void stampaFormeGeometriche() {
        formaGeometricaRepository.findAll().forEach(this::stampaPerimetroArea);
    }

    public void findById(int id) {
        formaGeometricaRepository.findById(id);
    }

    private void stampaPerimetroArea(FormaGeometrica formaGeometrica) {
        logger.info("Forma geometrica è {}", formaGeometrica.toString());
        logger.info("perimetro:{} ", formaGeometrica.perimetro());
        logger.info("area: {}", formaGeometrica.area());
    }
}