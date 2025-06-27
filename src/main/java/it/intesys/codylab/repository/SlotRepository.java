package it.intesys.codylab.repository;

import it.intesys.codylab.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {


    List<Slot> findByIdIn(List<Long> idList);
}