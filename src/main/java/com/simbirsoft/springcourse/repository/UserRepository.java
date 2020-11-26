package com.simbirsoft.springcourse.repository;

import com.simbirsoft.springcourse.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String name);
}
