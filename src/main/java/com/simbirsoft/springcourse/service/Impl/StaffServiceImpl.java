package com.simbirsoft.springcourse.service.Impl;

import com.simbirsoft.springcourse.dto.StaffDto;
import com.simbirsoft.springcourse.model.Staff;
import com.simbirsoft.springcourse.repository.StaffRepository;
import com.simbirsoft.springcourse.service.EmployeeService;
import com.simbirsoft.springcourse.service.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Staff> getById(Long id) {
        if (isEmpty(id)){
            return ResponseEntity.badRequest().build();
        }
        Staff staff = staffRepository.findById(id).orElse(null);
        if (isEmpty(staff)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(staff);
    }

    @Override
    public ResponseEntity<String> save(StaffDto staffDto) {
        if (isEmpty(staffDto)){
            return ResponseEntity.badRequest().build();
        }
        Staff staff = new Staff();
        staff.setEmployee(employeeService.findById(staffDto.getEmployeeId()));
        staff.setPosition(staffDto.getPosition());
        staff.setSalary(staffDto.getSalary());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public void delete(Long id) {
        staffRepository.deleteById(id);
    }
}
