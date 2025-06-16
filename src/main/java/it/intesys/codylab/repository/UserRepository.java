package it.intesys.codylab.repository;

import it.intesys.codylab.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findById(Long id){
        String sql = "SELECT * FROM users WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new User(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("mail"),
                        rs.getString("profilo"),
                        rs.getDouble("orario_giornaliero")
                )
        );
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM users";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new User(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("mail"),
                        rs.getString("profilo"),
                        rs.getDouble("orario_giornaliero")
                )
        );
    }

    public void save(User user) {
        String sql = "INSERT INTO users (nome, cognome, mail, profilo, orario_giornaliero) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                user.getNome(),
                user.getCognome(),
                user.getMail(),
                user.getProfilo(),
                user.getOrarioGiornaliero()
        );
    }

//  update
    public void update(Long id, User user) {
        String sql = "UPDATE users set nome = ?, cognome = ?, mail = ?, profilo = ?, orario_giornaliero = ? WHERE id = ?";

        jdbcTemplate.update(sql,
                user.getNome(),
                user.getCognome(),
                user.getMail(),
                user.getProfilo(),
                user.getOrarioGiornaliero(),
                id
        );
    }

//  updateProfilo
    public void updateUserProfile(Long id, User user) {
        String sql = "UPDATE users set profilo = ? WHERE id = ?";

        jdbcTemplate.update(sql,
                user.getProfilo(),
                id
        );
    }
//  delete


}
