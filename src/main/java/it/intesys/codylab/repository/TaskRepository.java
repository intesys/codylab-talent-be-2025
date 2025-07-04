package it.intesys.codylab.repository;

import it.intesys.codylab.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t JOIN t.users u WHERE u.id = :userId AND t.id IN :ids")
    Page<Task> findByIdsAndUserId(@Param("userId")Long userId, @Param("ids")List<Long> ids, Pageable pageable);

    @Query("SELECT t FROM Task t JOIN t.users u WHERE u.id = :userId")
    Page<Task> findByUserId(@Param("userId")Long userId, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.id IN :ids")
    Page<Task> findByIds(@Param("ids")List<Long> ids, Pageable pageable);

}