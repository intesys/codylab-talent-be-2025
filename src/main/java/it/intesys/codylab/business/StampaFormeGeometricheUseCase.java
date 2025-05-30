package it.intesys.codylab.business;

import it.intesys.codylab.model.FormaGeometrica;
import it.intesys.codylab.repository.FormaGeometricaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StampaFormeGeometricheUseCase {

    private final FormaGeometricaRepository formaGeometricaRepository;
    private static final Logger logger = LoggerFactory.getLogger(StampaFormeGeometricheUseCase.class);

    public StampaFormeGeometricheUseCase(FormaGeometricaRepository formaGeometricaRepository) {
        this.formaGeometricaRepository = formaGeometricaRepository;
    }

    public void stampaFormeGeometriche() {
        formaGeometricaRepository.findAll().forEach(this::stampaPerimetroArea);
    }

    private void stampaPerimetroArea (FormaGeometrica formaGeometrica) {
        logger.info("Forma geometrica è {}", formaGeometrica.toString());
        logger.info("perimetro: {}", formaGeometrica.perimetro());
        logger.info("area: {}", formaGeometrica.area());
    }
}
