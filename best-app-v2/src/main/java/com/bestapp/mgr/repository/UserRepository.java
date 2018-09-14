package com.bestapp.mgr.repository;

import com.bestapp.mgr.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
