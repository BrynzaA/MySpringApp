package com.simbirsoft.springcourse.service;

import com.simbirsoft.springcourse.dto.EmployeeDto;
import com.simbirsoft.springcourse.model.Employee;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {

    ResponseEntity<Employee> getById(Long id);
    ResponseEntity<String> save(EmployeeDto employeeDto);
    Employee findById(Long id);
    void delete(Long id);

}
