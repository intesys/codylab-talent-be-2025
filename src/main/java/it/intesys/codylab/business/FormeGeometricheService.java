package it.intesys.codylab.business;

import it.intesys.codylab.model.FormaGeometrica;
import it.intesys.codylab.repository.FormaGeometricaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
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

    public void findByNome(String nome) {
        formaGeometricaRepository.findByNome(nome);
    }


    private void stampaPerimetroArea(FormaGeometrica formaGeometrica) {
        logger.info("Forma geometrica è {}", formaGeometrica.toString());
        logger.info("perimetro:{} ", formaGeometrica.perimetro());
        logger.info("area: {}", formaGeometrica.area());
    }

    public void save(String nome, Double lato1, Double lato2) {
        formaGeometricaRepository.save(nome, lato1, lato2);
    }

    public void deleteByNome(String nome){
        formaGeometricaRepository.deleteByNome(nome);
    }

    public void update(int id, double lato1, double lato2) {
        formaGeometricaRepository.update(id, lato1, lato2);
    }
}
