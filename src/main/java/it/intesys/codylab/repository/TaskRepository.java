package it.intesys.codylab.repository;

import it.intesys.codylab.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Query(value = "DELETE FROM tasks WHERE id = :taskId", nativeQuery = true)
    int deleteByIdCustom(@Param("taskId") Long taskId);
}
