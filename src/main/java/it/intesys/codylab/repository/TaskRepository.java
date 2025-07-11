package it.intesys.codylab.repository;

import it.intesys.codylab.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query("SELECT t FROM Task t LEFT JOIN t.users u WHERE u.id = :userId AND t.id IN :taskIds")
    List<Task> findByUserIdAndTaskIds(@Param("userId") Long userId, @Param("taskIds") List<Long> taskIds);

    @Query("SELECT t FROM Task t WHERE t.id IN :taskIds")
    List<Task> findByIds(@Param("taskIds") List<Long> taskIds);

    @Query("SELECT t FROM Task t LEFT JOIN t.users u WHERE u.id = :userId")
    List<Task> findByUserId(@Param("userId") Long userId);

    @Query("SELECT t FROM Task t")
    List<Task> findAllTasks();
}
