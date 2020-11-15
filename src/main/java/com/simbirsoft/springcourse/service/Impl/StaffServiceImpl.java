package com.simbirsoft.springcourse.service.Impl;

import com.simbirsoft.springcourse.dto.StaffDto;
import com.simbirsoft.springcourse.model.Staff;
import com.simbirsoft.springcourse.repository.StaffRepository;
import com.simbirsoft.springcourse.service.EmployeeService;
import com.simbirsoft.springcourse.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Staff getById(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    @Override
    public void save(StaffDto staffDto) {

        Staff staff = new Staff();
        staff.setEmployee(employeeService.getById(staffDto.getEmployeeId()));
        staff.setPosition(staffDto.getPosition());
        staff.setSalary(staffDto.getSalary());

        staffRepository.save(staff);
    }

    @Override
    public void delete(Long id) {

    }
}
