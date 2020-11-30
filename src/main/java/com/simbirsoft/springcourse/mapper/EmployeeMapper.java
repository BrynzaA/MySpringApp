package com.simbirsoft.springcourse.mapper;

import com.simbirsoft.springcourse.dto.EmployeeDto;
import com.simbirsoft.springcourse.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEmployee(EmployeeDto employeeDto);
}
