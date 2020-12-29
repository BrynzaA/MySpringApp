package com.simbirsoft.springcourse.service.Impl;

import com.simbirsoft.springcourse.dto.EmployeeDto;
import com.simbirsoft.springcourse.exception.ResourceNotFoundException;
import com.simbirsoft.springcourse.exception.ValidationException;
import com.simbirsoft.springcourse.mapper.EmployeeMapper;
import com.simbirsoft.springcourse.model.Employee;
import com.simbirsoft.springcourse.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    EmployeeMapper employeeMapper;


    @Test
    void getById_ShouldReturn_Employee() {

        Employee employee = new Employee();
        employee.setId(1001L);
        employee.setName("John");
        employee.setSurname("Doe");
        employee.setDateOfBirth(LocalDate.of(1980, 11, 30));
        employee.setDateOfEmployment(LocalDate.of(2010, 5, 11));

        Mockito.when(employeeRepository.findById(employee.getId())).thenReturn(java.util.Optional.of(employee));

        Employee resultEmployee = employeeService.getById(1001L);

        Assertions.assertNotNull(resultEmployee);
    }

    @Test
    void getById_ShouldFail_ByBlankUser() {

        Mockito.when(employeeRepository.findById(1001L)).thenReturn(java.util.Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {employeeService.getById(1001L);
        });
    }

    @Test
    void getById_ShouldFail_ByBlankId() {

        Assertions.assertThrows(ValidationException.class, () -> {employeeService.getById(null);
        });
    }

    @Test
    void save_ShouldSave_Employee() {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("John");
        employeeDto.setSurname("Doe");
        employeeDto.setDateOfBirth(LocalDate.of(1980, 11, 30));
        employeeDto.setDateOfEmployment(LocalDate.of(2010, 5, 11));

        Mockito.when(employeeRepository.save(employeeMapper.toEmployee(employeeDto))).thenAnswer(invocationOnMock
                -> invocationOnMock.getArguments()[0]);

        Assertions.assertDoesNotThrow(()->{employeeService.save(employeeDto);
        });
    }

    @Test
    void save_ShouldFail_BlankEmployee() {

        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {employeeService.save(null);
        });
    }

    @Test
    void delete_ShouldDelete_Employee(){
        Employee employee = new Employee();
        employee.setId(1001L);

        Mockito.when(employeeRepository.save(employee)).thenAnswer(invocationOnMock
                -> invocationOnMock.getArguments()[0]);

        employeeRepository.deleteById(1001L);
        verify(employeeRepository, times(1)).deleteById(1001L);
    }

    @Test
    void delete_ShouldFail_BlankEmployee() {
        employeeRepository.deleteById(1001L);
        verify(employeeRepository, times(1)).deleteById(1001L);
    }
}