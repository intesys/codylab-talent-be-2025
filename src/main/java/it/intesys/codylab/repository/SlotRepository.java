package it.intesys.codylab.repository;

import it.intesys.codylab.model.Slot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {


    Page<Slot> findByIdIn(List<Long> ids, Pageable pageable);

}