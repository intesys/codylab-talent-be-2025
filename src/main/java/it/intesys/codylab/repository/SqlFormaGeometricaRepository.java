package it.intesys.codylab.repository;

import it.intesys.codylab.model.Cerchio;
import it.intesys.codylab.model.FormaGeometrica;
import it.intesys.codylab.model.Quadrato;
import it.intesys.codylab.model.Rettangolo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlFormaGeometricaRepository implements FormaGeometricaRepository {

    private final DataSource dataSource;
    private static final Logger log = LoggerFactory.getLogger(SqlFormaGeometricaRepository.class);

    public SqlFormaGeometricaRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<FormaGeometrica> findAll() {
        try {
            return executeFindAll();
        } catch (SQLException e) {
            log.error("Errore caricando le forma geometriche", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public FormaGeometrica findById(String id) {
        try {
            return executeFindById(id);
        } catch (SQLException e) {
            log.error("Errore caricando la forma geometrica con id: {}", id, e);
            throw new RuntimeException(e);
        }
    }

    public FormaGeometrica executeFindById(String id) throws SQLException {

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select id, tipo, lato1, lato2 from formageometrica where id = ?")) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    FormaGeometrica formaGeometrica = null;
                    while (resultSet.next()) {
                        String pk = resultSet.getString("id");
                        String tipo = resultSet.getString("tipo");
                        double lato1 = resultSet.getDouble("lato1");
                        double lato2 = resultSet.getDouble("lato2");

                        log.info("Forma geometrica trovata: id={}, tipo={}, lato1={}, lato2={}", pk, tipo, lato1, lato2);
                        if ("quadrato".equalsIgnoreCase(tipo)) {
                            formaGeometrica = mapResultSetToQuadrato(resultSet);
                        } else if ("rettangolo".equalsIgnoreCase(tipo)) {
                            formaGeometrica = mapResultSetToRettangolo(resultSet);
                        } else if ("cerchio".equalsIgnoreCase(tipo)) {
                            formaGeometrica = mapResultSetToCerchio(resultSet);
                        } else {
                            log.warn("Tipo di forma geometrica sconosciuto: {}", tipo);
                            throw new IllegalArgumentException("Tipo di forma geometrica sconosciuto");
                        }

                        return formaGeometrica;
                    }
                    log.warn("Nessuna forma geometrica trovata con id: {}", id);
                    throw new IllegalArgumentException("Forma geometrica non esistente con id: " + id);
                }
            }
        }
    }

    private List<FormaGeometrica> executeFindAll() throws SQLException {
        List<FormaGeometrica> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("select id, tipo, lato1, lato2 from formageometrica")){
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    FormaGeometrica formaGeometrica = null;
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String tipo = resultSet.getString("tipo");
                        double lato1 = resultSet.getDouble("lato1");
                        double lato2 = resultSet.getDouble("lato2");

                        log.info("Forma geometrica trovata: id={}, tipo={}, lato1={}, lato2={}", id, tipo, lato1, lato2);
                        if ("quadrato".equalsIgnoreCase(tipo)) {
                            formaGeometrica = mapResultSetToQuadrato(resultSet);
                        } else if ("rettangolo".equalsIgnoreCase(tipo)) {
                            formaGeometrica = mapResultSetToRettangolo(resultSet);
                        } else if ("cerchio".equalsIgnoreCase(tipo)) {
                            formaGeometrica = mapResultSetToCerchio(resultSet);
                        } else {
                            log.warn("Tipo di forma geometrica sconosciuto: {}", tipo);
                            throw new IllegalArgumentException("Tipo di forma geometrica sconosciuto");
                        }
                        result.add(formaGeometrica);
                    }
                }
            }
        }
        return result;
    }

    private Quadrato mapResultSetToQuadrato(ResultSet resultSet) throws SQLException {
        double lato1 = resultSet.getDouble("lato1");
        return new Quadrato(lato1);
    }
    private Cerchio mapResultSetToCerchio(ResultSet resultSet) throws SQLException {
        double lato1 = resultSet.getDouble("lato1");
        return new Cerchio(lato1);
    }
    private Rettangolo mapResultSetToRettangolo(ResultSet resultSet) throws SQLException {
        double lato1 = resultSet.getDouble("lato1");
        double lato2 =  resultSet.getDouble("lato2");
        return new Rettangolo(lato1, lato2);
    }
}
