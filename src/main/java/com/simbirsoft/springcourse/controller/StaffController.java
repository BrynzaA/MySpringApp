package com.simbirsoft.springcourse.controller;

import com.simbirsoft.springcourse.dto.StaffDto;
import com.simbirsoft.springcourse.model.Staff;
import com.simbirsoft.springcourse.service.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Staff> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(staffService.getById(id));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<String> addStaff(@RequestBody StaffDto staffDto){
        staffService.save(staffDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        staffService.delete(id);
        return ResponseEntity.ok().build();
    }
}
