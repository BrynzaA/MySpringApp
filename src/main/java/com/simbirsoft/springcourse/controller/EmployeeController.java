package com.simbirsoft.springcourse.controller;

import com.simbirsoft.springcourse.dto.EmployeeDto;
import com.simbirsoft.springcourse.exception.ResourceNotFoundException;
import com.simbirsoft.springcourse.model.Employee;
import com.simbirsoft.springcourse.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.*;
import io.swagger.annotations.Contact;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PreAuthorize("hasAnyAuthority('EMPLOYEE_READ')")
    @GetMapping("/{id}")
    @ApiOperation(value = "Find Employee by id",
            notes = "Provide an id to look up specific employee in database",
            response = Contact.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", responseContainer="List"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found", response = ResourceNotFoundException.class)
    })
    public ResponseEntity<Employee> findById(@ApiParam( value = "ID value for the employee you need to find" ,
            required = true)
                                                 @PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @PreAuthorize("hasAuthority('EMPLOYEE_EDIT')")
    @PostMapping("/create")
    @ApiOperation(value = "Create a new Employee in database",
            notes = "Provide name, surname, date of birth and a date of employment of a new worker",
            response = Contact.class)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
    })
    public ResponseEntity<String> addEmployee(@ApiParam( value = "Name, surname, date of birth and a date of employment",
            required = true)
                                                  @RequestBody EmployeeDto employeeDto){
        employeeService.save(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('EMPLOYEE_EDIT')")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Employee by id",
            notes = "Provide an id to delete specific employee from database",
            response = Contact.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found", response = ResourceNotFoundException.class)
    })
    public ResponseEntity<String> delete(@ApiParam( value = "ID value for the employee you need delete" ,
            required = true)
    @PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
