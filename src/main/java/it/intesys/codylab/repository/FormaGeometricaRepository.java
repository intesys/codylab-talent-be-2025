package it.intesys.codylab.repository;

import it.intesys.codylab.model.FormaGeometrica;

import java.util.List;

public interface FormaGeometricaRepository {

    List<FormaGeometrica> findAll();

    FormaGeometrica findById(int id);

    void deleteById(int id);

    //FormaGeometrica update(FormaGeometrica formaGeometrica);

    //FormaGeometrica save(FormaGeometrica formaGeometrica); //possibile esercizio

    //List<FormaGeometrica> findByNome(String nome); //possibile esercizio
}
