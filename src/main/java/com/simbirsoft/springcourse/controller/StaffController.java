package com.simbirsoft.springcourse.controller;

import com.simbirsoft.springcourse.dto.StaffDto;
import com.simbirsoft.springcourse.exception.ResourceNotFoundException;
import com.simbirsoft.springcourse.model.Staff;
import com.simbirsoft.springcourse.service.StaffService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/staff")
@AllArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PreAuthorize("hasAuthority('STAFF_READ')")
    @GetMapping("/{id}")
    @ApiOperation(value = "Find Staff by id",
            notes = "Provide an id to look up specific staff in database",
            response = Contact.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class),
            @ApiResponse(code = 404, message = "Not found", responseContainer = "Staff not found")
    })
    public ResponseEntity<Staff> findById(@ApiParam( value = "ID value for the staff you need to find" ,
            required = true)
                                              @PathVariable("id") Long id){
        return ResponseEntity.ok(staffService.getById(id));
    }

    @PreAuthorize("hasAuthority('STAFF_EDIT')")
    @PostMapping("/create")
    @ApiOperation(value = "Create a new Staff in database",
            notes = "Provide an id of existing employee, salary and position of a new staff",
            response = Contact.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found", response = ResourceNotFoundException.class)
    })
    public ResponseEntity<String> addStaff(@ApiParam( value = "Id of existing employee, salary and position",
            required = true)
                                               @RequestBody StaffDto staffDto){
        staffService.save(staffDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('STAFF_EDIT')")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Staff by id",
            notes = "Provide an id to delete specific staff from database",
            response = Contact.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found", response = ResourceNotFoundException.class)
    })
    public ResponseEntity<String> delete(@ApiParam( value = "ID value for the staff you need delete" , required = true)
                                             @PathVariable Long id){
        staffService.delete(id);
        return ResponseEntity.ok().build();
    }
}
