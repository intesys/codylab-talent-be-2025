package it.intesys.codylab.repository;

import it.intesys.codylab.model.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {
    private JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Task findById(Long id){
        String sql = "SELECT * FROM tasks WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Task(
                    rs.getLong("id"),
                    rs.getLong("progetto_id"),
                        rs.getString("codice"),
                        rs.getString("nome"),
                        rs.getString("descrizione"),
                        rs.getDate("data_inizio").toLocalDate(),
                        rs.getInt("durata")

                )
        );
    }
}
