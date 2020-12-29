package com.simbirsoft.springcourse.service.Impl;

import com.simbirsoft.springcourse.dto.StaffDto;
import com.simbirsoft.springcourse.exception.ResourceNotFoundException;
import com.simbirsoft.springcourse.exception.ValidationException;
import com.simbirsoft.springcourse.mapper.StaffMapper;
import com.simbirsoft.springcourse.model.Employee;
import com.simbirsoft.springcourse.model.Staff;
import com.simbirsoft.springcourse.repository.StaffRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class StaffServiceImplTest {

    @Mock
    StaffRepository staffRepository;

    @InjectMocks
    StaffServiceImpl staffService;

    @Mock
    StaffMapper staffMapper;

    @Mock
    EmployeeServiceImpl employeeService;

    @Test
    void getById_ShouldReturn_Employee() {

        Employee employee = new Employee();
        employee.setId(1001L);
        employee.setName("John");
        employee.setSurname("Doe");
        employee.setDateOfBirth(LocalDate.of(1980, 11, 30));
        employee.setDateOfEmployment(LocalDate.of(2010, 5, 11));

        Staff staff = new Staff();
        staff.setId(1001L);
        staff.setEmployee(employee);
        staff.setPosition("manager");
        staff.setSalary(1000F);

        Mockito.when(staffRepository.findById(staff.getId())).thenReturn(java.util.Optional.of(staff));

        Staff resultStaff = staffService.getById(1001L);

        Assertions.assertNotNull(resultStaff);
    }

    @Test
    void getById_ShouldFail_ByBlankUser() {

        Mockito.when(staffRepository.findById(1001L)).thenReturn(java.util.Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {staffService.getById(1001L);
        });
    }

    @Test
    void getById_ShouldFail_ByBlankId() {

        Assertions.assertThrows(ValidationException.class, () -> {staffService.getById(null);
        });
    }

    @Test
    void save_ShouldSave_Staff(){
        Employee employee = new Employee();
        employee.setId(1001L);
        employee.setName("John");
        employee.setSurname("Doe");
        employee.setDateOfBirth(LocalDate.of(1980, 11, 30));
        employee.setDateOfEmployment(LocalDate.of(2010, 5, 11));


        StaffDto staffDto = new StaffDto();
        staffDto.setEmployeeId(employee.getId());
        staffDto.setPosition("manager");
        staffDto.setSalary(1000F);

        Staff staff = new Staff();
        when(staffMapper.toStaff(any())).thenReturn(staff);

        Mockito.when(staffRepository.save(staffMapper.toStaff(staffDto))).thenAnswer(invocationOnMock
                -> invocationOnMock.getArguments()[0]);

        Assertions.assertDoesNotThrow(()->{staffService.save(staffDto);
        });
    }

    @Test
    void save_ShouldFail_ByBlank_Staff(){

        Mockito.when(staffRepository.save(Mockito.any(Staff.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {staffService.save(null);
        });
    }

    @Test
    void delete_ShouldDelete_Staff(){
        Employee employee = new Employee();
        employee.setId(1001L);
        employee.setName("John");
        employee.setSurname("Doe");
        employee.setDateOfBirth(LocalDate.of(1980, 11, 30));
        employee.setDateOfEmployment(LocalDate.of(2010, 5, 11));

        Staff staff = new Staff();
        staff.setId(1001L);
        staff.setEmployee(employee);
        staff.setPosition("manager");
        staff.setSalary(1000F);

        Mockito.when(staffRepository.save(staff)).thenAnswer(invocationOnMock
                -> invocationOnMock.getArguments()[0]);

        staffRepository.deleteById(1001L);
        verify(staffRepository, times(1)).deleteById(1001L);
    }

    @Test
    void delete_ShouldFail_BlankEmployee() {
        staffRepository.deleteById(1001L);
        verify(staffRepository, times(1)).deleteById(1001L);
    }
}