package it.intesys.codylab.repository;

import it.intesys.codylab.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {

}