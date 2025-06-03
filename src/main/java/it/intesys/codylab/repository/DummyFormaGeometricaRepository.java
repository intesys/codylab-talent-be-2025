package it.intesys.codylab.repository;

import it.intesys.codylab.model.Cerchio;
import it.intesys.codylab.model.FormaGeometrica;
import it.intesys.codylab.model.Quadrato;
import it.intesys.codylab.model.Rettangolo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DummyFormaGeometricaRepository implements FormaGeometricaRepository {

    private static final Logger log = LoggerFactory.getLogger(DummyFormaGeometricaRepository.class);

    public List<FormaGeometrica> findAll() {
        return  List.of(new Rettangolo(5, 10),
                new Quadrato(7),
                new Quadrato(15),
                new Cerchio(10));
    }

    public List<FormaGeometrica> findByNome(String nome) {
        return  List.of(new Rettangolo(5, 10),
                new Quadrato(7),
                new Quadrato(15),
                new Cerchio(10));
    }

    public FormaGeometrica save(String tipo, double lato1, double lato2) {
        return new Quadrato(5);
    }

    @Override
    public FormaGeometrica findById(int id) {
        return new Quadrato(5);
    }



    @Override
    public void deleteById(int id) {
        log.info("Forma geometrica con id {} eliminata", id);
    }

    @Override
    public void update(int id, double lato1, double lato2) {
        log.info("Forma geometrica con id {} aggiornata con lati {} e {}", id, lato1, lato2);
    }

}
