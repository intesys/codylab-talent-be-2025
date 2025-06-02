package it.intesys.codylab.repository;

import it.intesys.codylab.model.FormaGeometrica;

import java.sql.SQLException;
import java.util.List;

public interface FormaGeometricaRepository {

    List<FormaGeometrica> findAll();

    FormaGeometrica findById(String id);

    //FormaGeometrica deleteById(int id);

    //FormaGeometrica save(FormaGeometrica formaGeometrica);

    //FormaGeometrica update(FormaGeometrica formaGeometrica);

    //List<FormaGeometrica> findByNome(String nome); //possibile esercizio
}
