package it.intesys.codylab.repository;

import it.intesys.codylab.model.FormaGeometrica;

import java.util.List;

public interface FormaGeometricaRepository {

    List<FormaGeometrica> findAll();

    FormaGeometrica findById(int id);

    void deleteById(int id);

    void update(int id, double lato1, double lato2);

    //FormaGeometrica save(FormaGeometrica formaGeometrica); //possibile esercizio

    //List<FormaGeometrica> findByNome(String nome); //possibile esercizio
}
