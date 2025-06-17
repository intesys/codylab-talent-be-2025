package it.intesys.codylab.repository;

import it.intesys.codylab.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

}
