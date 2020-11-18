package com.simbirsoft.springcourse.service.Impl;

import com.simbirsoft.springcourse.dto.EmployeeDto;
import com.simbirsoft.springcourse.model.Employee;
import com.simbirsoft.springcourse.repository.EmployeeRepository;
import com.simbirsoft.springcourse.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<Employee> getById(Long id) {
        if (isEmpty(id)){
            return ResponseEntity.badRequest().build();
        }
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (isEmpty(employee)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @Override
    public ResponseEntity<String> save(EmployeeDto employeeDto) {
        if (isEmpty(employeeDto)){
            return ResponseEntity.badRequest().build();
        }
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setSurName(employeeDto.getSurname());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setDateOfEmployment(employeeDto.getDateOfEmployment());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
