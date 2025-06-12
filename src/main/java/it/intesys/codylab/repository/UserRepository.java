package it.intesys.codylab.repository;

import it.intesys.codylab.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findById(Long userId) {

        String sql = "SELECT * FROM users WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, (rs, rowNum) ->
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

}
