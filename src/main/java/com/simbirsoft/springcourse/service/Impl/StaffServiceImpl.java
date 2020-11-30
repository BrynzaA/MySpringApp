package com.simbirsoft.springcourse.service.Impl;

import com.simbirsoft.springcourse.dto.StaffDto;
import com.simbirsoft.springcourse.exception.ResourceNotFoundException;
import com.simbirsoft.springcourse.exception.ValidationException;
import com.simbirsoft.springcourse.mapper.StaffMapper;
import com.simbirsoft.springcourse.model.Staff;
import com.simbirsoft.springcourse.repository.StaffRepository;
import com.simbirsoft.springcourse.service.EmployeeService;
import com.simbirsoft.springcourse.service.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.util.StringUtils.isEmpty;

@Service
@AllArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final EmployeeService employeeService;
    private final StaffMapper staffMapper;



    @Override
    public Staff getById(Long id) {
        if (isEmpty(id)) {
            throw new ValidationException("Id should be valid");
        }
        Staff staff = staffRepository.findById(id).orElse(null);
        if (isEmpty(staff)) {
            throw new ResourceNotFoundException("Staff not found");
        }
        return staff;
    }

    @Override
    public void save(StaffDto staffDto) {
        if (isEmpty(staffDto)) {
            throw new ResourceNotFoundException("Staff should be not null");
        }
        Staff staff = staffMapper.toStaff(staffDto);
        staff.setEmployee(employeeService.getById(staffDto.getEmployeeId()));
        staffRepository.save(staff);
    }

    @Override
    public void delete(Long id) {
        staffRepository.delete(getById(id));
    }
}
