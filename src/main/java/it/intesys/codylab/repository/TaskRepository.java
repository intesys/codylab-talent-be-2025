package it.intesys.codylab.repository;

import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Query("SELECT t FROM Task t LEFT JOIN t.users u WHERE u.id = :userid AND t.id IN :taskIds")
    List<Task> findByUserIdAndTaskIds(@Param("userid") Long userid, @Param("taskIds") List<Long> taskIds);

    @Query("SELECT t FROM Task t WHERE t.id IN :taskIds")
    List<Task> findByIds(@Param("taskIds") List<Long> taskIds);

    @Query("SELECT t FROM Task t LEFT JOIN t.users u WHERE u.id = :userid")
    List<Task> findByUserId(@Param("userid") Long userid);

    @Query("SELECT t FROM Task t")
    List<Task> findAllTasks();
}