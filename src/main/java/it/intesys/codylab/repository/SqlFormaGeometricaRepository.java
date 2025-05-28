package it.intesys.codylab.repository;

import it.intesys.codylab.model.FormaGeometrica;

import javax.sql.DataSource;
import java.util.List;

public class SqlFormaGeometricaRepository implements FormaGeometricaRepository {

    private final DataSource dataSource;

    public SqlFormaGeometricaRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<FormaGeometrica> findAll() {
        return List.of();
    }
}
