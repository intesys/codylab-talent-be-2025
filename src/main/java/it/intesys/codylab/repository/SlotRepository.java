package it.intesys.codylab.repository;
import it.intesys.codylab.model.Slot;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SlotRepository {
    private JdbcTemplate jdbcTemplate;
    public SlotRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Slot findById(Long slotId) {
        String sql = "SELECT * FROM slots WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{slotId}, (rs, rowNum) ->
            new Slot(
                rs.getLong("id"), rs.getLong("task_id"),
                rs.getTimestamp("start_time").toLocalDateTime(),
                rs.getTimestamp("end_time").toLocalDateTime()
            )
        );
    }
    public void save(Slot slot) {
        String sql = "INSERT INTO slots (task_id, start_time, end_time) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, slot.getTaskId(), slot.getStartTime(), slot.getEndTime());
    }
}
