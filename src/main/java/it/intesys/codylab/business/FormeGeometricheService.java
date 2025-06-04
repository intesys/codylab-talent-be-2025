package it.intesys.codylab.business;

import it.intesys.codylab.model.FormaGeometrica;
import it.intesys.codylab.repository.FormaGeometricaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FormeGeometricheService {

    private final FormaGeometricaRepository formaGeometricaRepository;
    private static final Logger logger = LoggerFactory.getLogger(FormeGeometricheService.class);

    public FormeGeometricheService(FormaGeometricaRepository formaGeometricaRepository) {
        this.formaGeometricaRepository = formaGeometricaRepository;
    }

    public void stampaFormeGeometriche() {
        formaGeometricaRepository.findAll().forEach(this::stampaPerimetroArea);
    }

    public void findById(int id) {
        formaGeometricaRepository.findById(id);
    }

    public void findByString(String nome) {
        formaGeometricaRepository.findByString(nome);
    }

    private void stampaPerimetroArea(FormaGeometrica formaGeometrica) {
        logger.info("Forma geometrica è {}", formaGeometrica.toString());
        logger.info("perimetro:{} ", formaGeometrica.perimetro());
        logger.info("area: {}", formaGeometrica.area());
    }

    public void save(String tipo, Double lato1, Double lato2) {
        formaGeometricaRepository.save(tipo, lato1, lato2);
    }

    public void deleteById(int id) {
        formaGeometricaRepository.deleteById(id);
    }

    public void update(int id, Double lato1, Double lato2) {
        formaGeometricaRepository.update(id, lato1, lato2);
    }
}
