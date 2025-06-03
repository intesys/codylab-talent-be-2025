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
    public FormaGeometrica findById(int id) {
        try {
            return executeFindById(id);
        } catch (SQLException e) {
            log.error("Errore caricando la forma geometrica con id: {}", id, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public FormaGeometrica findByString(String nome) {
        try {
            return executeFindByString(nome);
        } catch (SQLException e) {
            log.error("Errore caricando la forma geometrica con nome: {}", nome, e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteById(int id) {
        try {
            executeDeleteById(id);
        } catch (SQLException e) {
            log.info("Errore eliminando la forma geometrica con id: {}", id, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, double lato1, double lato2) {
        try {
            executeUpdate(id, lato1, lato2);
        } catch (SQLException e) {
            log.error("Errore aggiornando la forma geometrica con id: {}", id, e);
            throw new RuntimeException(e);
        }
    }

    private void executeUpdate(int id, double lato1, double lato2) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("update formageometrica set lato1 = ?, lato2 = ? where id = ?")) {
                statement.setDouble(1, lato1);
                statement.setDouble(2, lato2);
                statement.setInt(3, id);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    log.info("Forma geometrica con id {} aggiornata con lati {}, {}", id, lato1, lato2);
                } else {
                    log.warn("Nessuna forma geometrica trovata con id: {}", id);
                    throw new IllegalArgumentException("Forma geometrica non esistente con id: " + id);
                }
            }
        }
    }
    private void executeDeleteById(int id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("delete from formageometrica where id = ?")) {
                statement.setInt(1, id);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    log.info("Forma geometrica con id {} eliminata", id);
                } else {
                    log.warn("Nessuna forma geometrica trovata con id: {}", id);
                    throw new IllegalArgumentException("Forma geometrica non esistente con id: " + id);
                }
            }
        }
    }

    public FormaGeometrica executeFindById(int id) throws SQLException {

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select id, tipo, lato1, lato2 from formageometrica where id = ?")) {
                statement.setInt(1, id);
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

    public FormaGeometrica executeFindByString(String nome) throws SQLException {

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select id, tipo, lato1, lato2 from formageometrica where tipo = ?");
            ) {
                statement.setString(1, nome);
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
                    log.warn("Nessuna forma geometrica trovata con nome: {}", nome);
                    throw new IllegalArgumentException("Forma geometrica non esistente con nome: " + nome);
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
