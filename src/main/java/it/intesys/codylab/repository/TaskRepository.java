package it.intesys.codylab.repository;

import it.intesys.codylab.model.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {
    private JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//  findById
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

//  findAll
    public List<Task> findAll(){
        String sql = "SELECT * FROM tasks";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
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

//  addTask
    public void save(Task task){
        String sql = "INSERT INTO tasks (progetto_id, codice, nome, descrizione, data_inizio, durata) VALUES (?, ?, ?, ?, ?, ? )";

        jdbcTemplate.update(sql,
                task.getProgettoId(),
                task.getCodice(),
                task.getNome(),
                task.getDescrizione(),
                task.getDataInizio(),
                task.getDurata()
        );
    }

//  updateTask
    public void update(Long id, Task task){
        String sql = "UPDATE tasks set progetto_id = ?, codice = ?, nome = ?, descrizione = ?, data_inizio = ?, durata = ? WHERE id = ?";

        jdbcTemplate.update(sql,
                task.getProgettoId(),
                task.getCodice(),
                task.getNome(),
                task.getDescrizione(),
                task.getDataInizio(),
                task.getDurata(),
                id
        );
    }

//  deleteTask
    public void delete(Long id){
        String sql = "DELETE FROM tasks WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
