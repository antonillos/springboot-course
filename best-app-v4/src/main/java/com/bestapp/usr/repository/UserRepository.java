package com.bestapp.usr.repository;

import com.bestapp.usr.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserName(String username);

}
