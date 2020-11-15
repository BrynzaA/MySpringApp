package com.simbirsoft.springcourse.controller;

import com.simbirsoft.springcourse.model.Staff;
import com.simbirsoft.springcourse.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.StringUtils.isEmpty;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/{id}")
    public ResponseEntity<Staff> findById(@PathVariable("id") Long id){
        if(isEmpty(id)){
            return ResponseEntity.badRequest().build();
        }

        Staff staff = staffService.getById(id);

        if(isEmpty(id)){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(staff);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Staff staff = staffService.getById(id);

        if(isEmpty(staff)){
            return ResponseEntity.notFound().build();
        }

        staffService.delete(id);
        return ResponseEntity.ok().build();
    }
}
