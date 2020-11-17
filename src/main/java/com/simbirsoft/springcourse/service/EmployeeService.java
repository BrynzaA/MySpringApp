package com.simbirsoft.springcourse.service;

import com.simbirsoft.springcourse.dto.EmployeeDto;
import com.simbirsoft.springcourse.model.Employee;

public interface EmployeeService {

    Employee getById(Long id);
    Employee save(EmployeeDto employeeDto);
    void delete(Long id);

}
