package com.simbirsoft.springcourse.controller;

import com.simbirsoft.springcourse.dto.EmployeeDto;
import com.simbirsoft.springcourse.model.Employee;
import com.simbirsoft.springcourse.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.StringUtils.isEmpty;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id") Long id) {
        if (isEmpty(id)){
            return ResponseEntity.badRequest().build();
        }

        Employee employee = employeeService.getById(id);

        if (isEmpty(employee)){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(employee);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeDto employeeDto){
        if (isEmpty(employeeDto)){
            return ResponseEntity.badRequest().build();
        }

        employeeService.save(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Employee employee = employeeService.getById(id);

        if(isEmpty(employee)){
            return ResponseEntity.notFound().build();
        }

        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
