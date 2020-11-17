package com.simbirsoft.springcourse.service.Impl;

import com.simbirsoft.springcourse.dto.StaffDto;
import com.simbirsoft.springcourse.model.Staff;
import com.simbirsoft.springcourse.repository.StaffRepository;
import com.simbirsoft.springcourse.service.EmployeeService;
import com.simbirsoft.springcourse.service.StaffService;
import com.simbirsoft.springcourse.service.ValidationUtils;
import org.springframework.stereotype.Service;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final EmployeeService employeeService;

    public StaffServiceImpl(StaffRepository staffRepository, EmployeeService employeeService) {
        this.staffRepository = staffRepository;
        this.employeeService = employeeService;
    }

    @Override
    public Staff getById(Long id) {
        ValidationUtils.idIsNotNull(id);
        Staff staff = staffRepository.findById(id).orElse(null);
        if (isEmpty(staff)){
            throw new NullPointerException("Staff not found");
        }
        return staff;
    }

    @Override
    public Staff save(StaffDto staffDto) {
        if (isEmpty(staffDto)){
            throw new NullPointerException("Employee should be not null");
        }
        Staff staff = new Staff();
        staff.setEmployee(employeeService.getById(staffDto.getEmployeeId()));
        staff.setPosition(staffDto.getPosition());
        staff.setSalary(staffDto.getSalary());

        return staffRepository.save(staff);
    }

    @Override
    public void delete(Long id) {
        staffRepository.delete(getById(id));
    }
}
