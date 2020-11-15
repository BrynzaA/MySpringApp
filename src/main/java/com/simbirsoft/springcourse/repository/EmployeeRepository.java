package com.simbirsoft.springcourse.repository;

import com.simbirsoft.springcourse.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
