package com.simbirsoft.springcourse.service;

import com.simbirsoft.springcourse.dto.StaffDto;
import com.simbirsoft.springcourse.model.Staff;

public interface StaffService {

    Staff getById(Long id);
    Staff save(StaffDto employeeDto);
    void delete(Long id);
}
