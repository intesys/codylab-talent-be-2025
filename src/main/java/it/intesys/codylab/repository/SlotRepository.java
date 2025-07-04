package it.intesys.codylab.repository;

import it.intesys.codylab.model.Slot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    @Query("SELECT s FROM Slot s JOIN s.task t WHERE s.id IN :ids AND t.id = :taskId")
    Page<Slot> findByIdsAndTaskId(@Param("ids") List<Long> ids, @Param("taskId") Long taskId, Pageable pageable);

    @Query("SELECT s FROM Slot s JOIN s.task t WHERE t.id = :taskId")
    Page<Slot> findByTaskId(@Param("taskId") Long taskId, Pageable pageable);

    @Query("SELECT s FROM Slot s WHERE s.id IN :ids")
    Page<Slot> findByIds(@Param("ids") List<Long> ids, Pageable pageable);
}