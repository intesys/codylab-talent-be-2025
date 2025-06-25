package it.intesys.codylab.repository;

import it.intesys.codylab.model.Slot;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface SlotRepository extends JpaRepository<Slot, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM slots WHERE id = :slotId", nativeQuery = true)
    int deleteByIdCustom(@Param("slotId") Long slotId);

}
