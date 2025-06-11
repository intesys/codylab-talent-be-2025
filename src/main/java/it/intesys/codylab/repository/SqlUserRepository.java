package it.intesys.codylab.repository;

import it.intesys.codylab.dto.User;
import it.intesys.codylab.dto.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlUserRepository implements UserRepository {

    private final DataSource dataSource;
    private static final Logger log = LoggerFactory.getLogger(SqlUserRepository.class);

    public SqlUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        try{
            return executeFindById(id);
        } catch (SQLException e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try{
            return executeFindAll();
        } catch (SQLException e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user) {
        try{
            executeSave(user);
        } catch (SQLException e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            executeDeleteById(id);
        } catch (SQLException e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserProfile(Long userId, UserProfile userProfile){
        try{
            executeUpdateUserProfile(userId, userProfile);
        } catch (SQLException e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private User executeFindById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")){
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()){
                    User user = null;
                    while (resultSet.next()){
                        user = new User();
                        user.setId(resultSet.getLong("id"));
                        user.setFirstName(resultSet.getString("firstName"));
                        user.setLastName(resultSet.getString("lastName"));
                        user.setEmail(resultSet.getString("email"));
                        user.setProfile(UserProfile.valueOf(resultSet.getString("profile")));
//                        user.setWorkingHours(WorkingHours.of(Duration.parse(resultSet.getString("workingHours"))));
                    }

                    return user;
                }
            }
        }
    }

    private List<User> executeFindAll() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users")){
                try (ResultSet resultSet = statement.executeQuery()){
                    User user = null;
                    while (resultSet.next()){
                        user = new User();
                        user.setId(resultSet.getLong("id"));
                        user.setFirstName(resultSet.getString("firstName"));
                        user.setLastName(resultSet.getString("lastName"));
                        user.setEmail(resultSet.getString("email"));
                        user.setProfile(UserProfile.valueOf(resultSet.getString("profile")));
//                        user.setWorkingHours(WorkingHours.of(Duration.parse(resultSet.getString("workingHours"))));
                    }
                    users.add(user);
                }
            }
        }
        return users;
    }

    private void executeSave(User user) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement("insert into users (id, firstName, lastName, email, profile) values (?,?,?,?,?)")){
                statement.setLong(1, user.getId());
                statement.setString(2, user.getFirstName());
                statement.setString(3, user.getLastName());
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getProfile().toString());
//                statement.setString(6, user.getWorkingHours().toString());
                int rows = statement.executeUpdate();
                if (rows > 0){
                    log.info("Utente aggiunto");
                } else {
                    log.warn("Errore");
                    throw new IllegalArgumentException("Errore");
                }
            }
        }
    }

    private void executeDeleteById(Long id) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement("delete from users where id = ?")){
                statement.setLong(1, id);
                int rows = statement.executeUpdate();
                if (rows > 0){
                    log.info("Utente eliminato");
                }else {
                    log.warn("Errore");
                    throw new IllegalArgumentException("Errore");
                }
            }
        }
    }

    private void executeUpdateUserProfile(Long id, UserProfile userProfile) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement("update users set profile = ? where id = ?")){
                statement.setString(1, userProfile.toString());
                statement.setLong(2, id);
                int rows = statement.executeUpdate();
                if (rows > 0){
                    log.info("Profilo aggiornato");
                }else{
                    log.warn("Errore");
                    throw new IllegalArgumentException("Errore");
                }
            }
        }
    }
}