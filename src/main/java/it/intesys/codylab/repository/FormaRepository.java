package it.intesys.codylab.repository;

import it.intesys.codylab.entities.Forma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaRepository extends JpaRepository<Forma, Long> {
}
