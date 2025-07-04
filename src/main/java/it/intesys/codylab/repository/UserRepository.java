package it.intesys.codylab.repository;

import it.intesys.codylab.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN u.projects p WHERE p.responsabile.id = :id")
    User findUserWithProgettiResponsabile(Long id);

    @Query("SELECT u FROM User u JOIN u.tasks t WHERE t.id = :taskId AND u.id IN :ids")
    Page<User> findByIdsAndTasksId(List<Long> ids, Long taskId, Pageable pageable);

    @Query("SELECT u FROM User u JOIN u.tasks t WHERE t.id = :taskId")
    Page<User> findByTasksId(Long taskId, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.id IN :ids")
    Page<User> findByIds(List<Long> ids, Pageable pageable);


}
