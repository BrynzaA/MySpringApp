package com.simbirsoft.springcourse.service.Impl;

import com.simbirsoft.springcourse.dto.EmployeeDto;
import com.simbirsoft.springcourse.exception.ResourceNotFoundException;
import com.simbirsoft.springcourse.exception.ValidationException;
import com.simbirsoft.springcourse.mapper.EmployeeMapper;
import com.simbirsoft.springcourse.model.Employee;
import com.simbirsoft.springcourse.repository.EmployeeRepository;
import com.simbirsoft.springcourse.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.util.StringUtils.isEmpty;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;


    @Override
    public Employee getById(Long id) {
        if (isEmpty(id)) {
            throw new ValidationException("Id should be valid");
        }
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (isEmpty(employee)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        return employee;
    }

    @Override
    public void save(EmployeeDto employeeDto) {
        if (isEmpty(employeeDto)) {
            throw new ResourceNotFoundException("Employee should be not null");
        }
        employeeRepository.save(employeeMapper.toEmployee(employeeDto));
    }

    @Override
    public void delete(Long id) {
        employeeRepository.delete(getById(id));
    }
}
