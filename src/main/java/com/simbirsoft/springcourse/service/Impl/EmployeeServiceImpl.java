package com.simbirsoft.springcourse.service.Impl;

import com.simbirsoft.springcourse.dto.EmployeeDto;
import com.simbirsoft.springcourse.model.Employee;
import com.simbirsoft.springcourse.repository.EmployeeRepository;
import com.simbirsoft.springcourse.service.EmployeeService;
import com.simbirsoft.springcourse.service.ValidationUtils;
import org.springframework.stereotype.Service;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getById(Long id) {
        ValidationUtils.idIsNotNull(id);
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (isEmpty(employee)){
            throw new NullPointerException("Employee not found");
        }
        return employee;
    }

    @Override
    public Employee save(EmployeeDto employeeDto) {
        if (isEmpty(employeeDto)){
            throw new NullPointerException("Employee should be not null");
        }
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setSurName(employeeDto.getSurname());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setDateOfEmployment(employeeDto.getDateOfEmployment());

        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.delete(getById(id));
    }
}
