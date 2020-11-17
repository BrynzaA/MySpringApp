package com.simbirsoft.springcourse.controller;

import com.simbirsoft.springcourse.model.Staff;
import com.simbirsoft.springcourse.service.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(staffService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        staffService.delete(id);
    }
}
