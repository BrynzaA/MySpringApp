package com.simbirsoft.springcourse.controller;

import com.simbirsoft.springcourse.dto.StaffDto;
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
        return staffService.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addStaff(@RequestBody StaffDto staffDto){
        return staffService.save(staffDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        staffService.delete(id);
        return ResponseEntity.ok().build();
    }
}
