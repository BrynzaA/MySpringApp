package com.simbirsoft.springcourse.repository;

import com.simbirsoft.springcourse.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByUsername(String name);
}
