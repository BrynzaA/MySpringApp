package com.simbirsoft.springcourse.service;

import com.simbirsoft.springcourse.dto.StaffDto;
import com.simbirsoft.springcourse.model.Staff;
import org.springframework.http.ResponseEntity;

public interface StaffService {

    ResponseEntity<Staff> getById(Long id);
    ResponseEntity<String> save(StaffDto employeeDto);
    void delete(Long id);
}
