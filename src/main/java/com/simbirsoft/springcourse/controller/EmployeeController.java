package com.simbirsoft.springcourse.controller;

import com.simbirsoft.springcourse.dto.EmployeeDto;
import com.simbirsoft.springcourse.model.Employee;
import com.simbirsoft.springcourse.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PreAuthorize("hasAnyAuthority()")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @PreAuthorize("hasAuthority(AuthorityType.ROLE_ADMIN.name())")
    @PostMapping("/create")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeDto employeeDto){
        employeeService.save(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority(AuthorityType.ROLE_ADMIN.name())")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
