package it.intesys.codylab.repository;

import it.intesys.codylab.model.Cerchio;
import it.intesys.codylab.model.FormaGeometrica;
import it.intesys.codylab.model.Quadrato;
import it.intesys.codylab.model.Rettangolo;

import java.util.List;

public class DummyFormaGeometricaRepository implements FormaGeometricaRepository {

    public List<FormaGeometrica> findAll() {
        return  List.of(new Rettangolo(5, 10),
                new Quadrato(7),
                new Quadrato(15),
                new Cerchio(10));
    }

    @Override
    public FormaGeometrica findById(int id) {
        return new Quadrato(5);
    }

}
